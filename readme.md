# smart-home-sdk

[![CI](https://github.com/smart-home-automation-system/smart-home-sdk/actions/workflows/CI.yml/badge.svg)](https://github.com/smart-home-automation-system/smart-home-sdk/actions/workflows/CI.yml)
![GitHub Release Date - Published_At](https://img.shields.io/github/release-date/smart-home-automation-system/smart-home-sdk?style=plastic)
![GitHub Release](https://img.shields.io/github/v/release/smart-home-automation-system/smart-home-sdk?style=plastic)

![GitHub top language](https://img.shields.io/github/languages/top/smart-home-automation-system/smart-home-sdk?style=plastic)
![Java](https://img.shields.io/badge/java-21-yellow?style=plastic)

![GitHub issues](https://img.shields.io/github/issues/smart-home-automation-system/smart-home-sdk?style=plastic)
![GitHub contributors](https://img.shields.io/github/contributors/smart-home-automation-system/smart-home-sdk?style=plastic)
![GitHub pull requests](https://img.shields.io/github/issues-pr-raw/smart-home-automation-system/smart-home-sdk?style=plastic)

![GitHub last commit](https://img.shields.io/github/last-commit/smart-home-automation-system/smart-home-sdk?style=plastic)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/smart-home-automation-system/smart-home-sdk?style=plastic)

Shared domain and API models for the smart-home-automation-system services. The model
classes (`cloud.cholewa.home.model`) are generated at build time from the OpenAPI schemas
in [`swagger/`](swagger/) (`smart-home.yaml` aggregates the home, device-commons, Eaton,
RabbitMQ and household schemas) and cover room names, device vendors/types, Eaton gateway
configuration and datagram replies, RabbitMQ message payloads such as `TemperatureMessage`,
and the household registry (`HouseholdMember` with its Wi-Fi devices, `MemberPhoneDetails`,
and with its role and rooms, `MemberRole`).

Current consumers: `amx-service`, `boiler-service`, `database-service`, `heating-service`,
`presence-service`, `shelly-cloud-service`, `water-service`.

## Installation

The artifact is published to GitHub Packages by the [`package.yml`](.github/workflows/package.yml)
workflow on release.

```xml
<dependency>
    <groupId>cloud.cholewa</groupId>
    <artifactId>smart-home-sdk</artifactId>
    <version>1.5.0</version>
</dependency>
```

Add the organization's GitHub Packages repository (requires a GitHub token with
`read:packages` in your Maven `settings.xml`):

```xml
<repositories>
    <repository>
        <id>github-org-smart-home</id>
        <url>https://maven.pkg.github.com/smart-home-automation-system/*</url>
    </repository>
</repositories>
```

## Usage

All models are plain Jackson-annotated classes — enums with case-insensitive
`@JsonCreator` factories and DTOs with builders and fluent setters:

```java
TemperatureMessage message = TemperatureMessage.builder()
    .date(LocalDateTime.now())
    .room(RoomName.LIVING_ROOM)
    .temperature(21.5)
    .build();

RoomName room = RoomName.fromValue("living room");
```

### Household member: role and rooms

Since 1.4.0 a `HouseholdMember` carries what the web dashboard shows that person:

| Field | Type | In JSON |
|---|---|---|
| `role` | `MemberRole` - `admin` or `resident` | optional, **no default**: left out, it reads as `null` - "not sent" |
| `rooms` | `List<RoomName>` | optional; a member can have several rooms or none, in the order they are to be shown |

```java
HouseholdMember member = new HouseholdMember()
    .name("Anna")
    .phone("+48505602702")
    .role(MemberRole.ADMIN)
    .addRoomsItem(RoomName.OFFICE)
    .addRoomsItem(RoomName.SANCTUM);
```

```json
{"name":"Anna","phone":"+48505602702","active":true,"role":"admin","rooms":["office","sanctum"]}
```

What a consumer has to know:

- **A missing `role` is `null`, not `resident`.** The model has no default on purpose, so the
  registry (`database-service`) can tell "not sent" from "make this member a resident": it
  makes a new member a resident and leaves the role of an existing one alone when an update
  does not name it. The registry itself always answers with a role.
- **A missing `rooms` cannot be told from an empty one**: both read as an empty list, and an
  empty list is left out of the JSON on the way out (the model omits empty collections, like
  `devices`). A reader treats a missing `rooms` as "no rooms"; an update that must not touch
  the rooms has to send them again.
- **The model does not check the rooms for repetitions or for `null` items** - it is a plain
  list, which keeps the order. Refusing `["office","office"]` is the registry's job.
- **An unknown role or room does not deserialize**: `MemberRole.fromValue` and
  `RoomName.fromValue` throw `IllegalArgumentException("Unexpected value '...'")` inside
  Jackson. Turning that into a 400 with a readable message is up to the consuming service.
- **`HouseholdMember.builder()` applies no defaults** - a member built that way has `null`
  rooms (and a `null` `active`) unless they are set. `new HouseholdMember()` and
  deserialization start with an empty list.

### Household profile: what the dashboard may know

Since 1.5.0 `HouseholdProfile` is a household member as the web dashboard needs them - the
name, the role and the rooms, and **nothing else**: no phone number, no devices. It is the body
of a read that every browser in the house makes (`GET /home/household/profiles` of
`database-service`), which is why it is a model of its own and not a `HouseholdMember` with
fields left empty - there is no field to fill by mistake.

| Field | Type | In JSON |
|---|---|---|
| `name` | `String`, 3-50 | always |
| `role` | `MemberRole` | always |
| `rooms` | `List<RoomName>` | left out when empty: a missing `rooms` means none |

```json
{"name":"Anna","role":"admin","rooms":["sanctum","office"]}
```

- **There is no `active`.** A profile exists for an active member only; whoever answers with
  profiles leaves the others out. The model cannot say it - it is the producer's rule.
- **The model does not make the role present**: `role` is required in the schema, but a
  producer that leaves it unset writes `"role":null`. The registry never does (the column is
  not null); a client still reads anything but `admin` as the role that reaches the least.
- The builder applies no defaults here either: `HouseholdProfile.builder()` without
  `.rooms(...)` has `null` rooms.

To add or change a model, edit the relevant schema in `swagger/` and reference it from
`swagger/smart-home.yaml`; `mvn verify` regenerates the sources in
`src/main/java/cloud/cholewa/home/model`.
