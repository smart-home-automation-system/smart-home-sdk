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
    <version>1.4.0</version>
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
| `role` | `MemberRole` - `admin` or `resident` | optional; a member read without one is a `resident` |
| `rooms` | `Set<RoomName>` | optional; a member can have several rooms or none, and a room listed twice is kept once |

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

Three things a consumer has to know:

- **An empty `rooms` is left out of the JSON** (the model omits empty collections, like
  `devices`). A reader treats a missing `rooms` as "no rooms".
- **An unknown role or room does not deserialize**: `MemberRole.fromValue` and
  `RoomName.fromValue` throw `IllegalArgumentException("Unexpected value '...'")`, which a
  service answers as a bad request.
- **`HouseholdMember.builder()` applies no defaults** - a member built that way has a `null`
  role and `null` rooms (and a `null` `active`) unless they are set. `new HouseholdMember()`
  and deserialization do apply them.

To add or change a model, edit the relevant schema in `swagger/` and reference it from
`swagger/smart-home.yaml`; `mvn verify` regenerates the sources in
`src/main/java/cloud/cholewa/home/model`.
