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
RabbitMQ, household, device-registry, calendar and device-events schemas) and cover room names, device vendors/types, Eaton gateway
configuration and datagram replies, RabbitMQ message payloads such as `TemperatureMessage`,
and the household registry (`HouseholdMember` with its Wi-Fi devices, `MemberPhoneDetails`,
and with its role, rooms and permissions - `MemberRole`, `MemberPermission`), and - since
1.7.0 - the device registry (`SmartDevice` and its bindings), the calendar of workdays and
days off (`CalendarDay`) and the event of an AMX device (`DeviceEvent`).

Current consumers: `amx-service`, `blinds-service`, `boiler-service`, `database-service`, `heating-service`,
`presence-service`, `shelly-cloud-service`, `water-service`, `weather-service`.

## Installation

The artifact is published to GitHub Packages by the [`package.yml`](.github/workflows/package.yml)
workflow on release.

```xml
<dependency>
    <groupId>cloud.cholewa</groupId>
    <artifactId>smart-home-sdk</artifactId>
    <version>1.11.0</version>
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

A room of two words is written with a space (`living room`, `hall down`). `HALL_UP` was the
one exception until 1.10.0 (`hall_up`); it is `hall up` now, and `RoomName.fromValue` still
reads `hall_up` as `HALL_UP` - a transition, so that a service on 1.10.0 understands one still
on 1.9.0. A service on 1.9.0 or older does **not** read `hall up`: no room may be set to it
before every reader has moved.

### Household member: role and rooms

Since 1.4.0 a `HouseholdMember` carries what the web dashboard shows that person:

| Field | Type | In JSON |
|---|---|---|
| `role` | `MemberRole` - `admin` or `resident` | optional, **no default**: left out, it reads as `null` - "not sent" |
| `rooms` | `List<RoomName>` | optional; a member can have several rooms or none, in the order they are to be shown |
| `permissions` | `List<MemberPermission>` (since 1.6.0) | optional; what the member may do beyond their role, none for most |

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
- **`permissions` (since 1.6.0) behave like the rooms**: a list of `MemberPermission`, empty
  unless something was granted, left out of the JSON while empty, and not told apart from "not
  sent" - so a registry has to give them an operation of their own instead of honouring them in
  a partial update (`database-service` does from the release that takes 1.6.0; an older one
  ignores the field). The one permission so far is `heating_switch`: the member may switch the
  heating of the whole house from their own page of the dashboard. It decides what the
  interface offers; nothing behind the gateway checks it.
- **A new value of `MemberPermission` is not additive for a reader.** A consumer whose model
  has the field fails on a value it does not know (below), and `presence-service` reads the
  whole registry in one call: one stored value it cannot read stops its detection. Every
  consumer that deserializes `HouseholdMember` or `HouseholdProfile` moves to the release with
  the new value **before** the registry stores it for anybody.
- **The model does not check the rooms for repetitions or for `null` items** - it is a plain
  list, which keeps the order. Refusing `["office","office"]` is the registry's job.
- **An unknown role, room or permission does not deserialize**: `MemberRole.fromValue`,
  `RoomName.fromValue` and `MemberPermission.fromValue` throw `IllegalArgumentException("Unexpected value '...'")` inside
  Jackson. Turning that into a 400 with a readable message is up to the consuming service.
- **`HouseholdMember.builder()` applies no defaults** - a member built that way has `null`
  rooms (and a `null` `active`) unless they are set. `new HouseholdMember()` and
  deserialization start with an empty list.

### Household profile: what the dashboard may know

Since 1.5.0 `HouseholdProfile` is a household member as the web dashboard needs them - the
name, the role, the rooms and (since 1.6.0) the permissions, and **nothing else**: no phone number, no devices. It is the body
of a read that every browser in the house makes (`GET /home/household/profiles` of
`database-service`), which is why it is a model of its own and not a `HouseholdMember` with
fields left empty - there is no field to fill by mistake.

| Field | Type | In JSON |
|---|---|---|
| `name` | `String`, 3-50 | always |
| `role` | `MemberRole` | always |
| `rooms` | `List<RoomName>` | left out when empty: a missing `rooms` means none |
| `permissions` | `List<MemberPermission>` | left out when empty: a missing `permissions` means none |

```json
{"name":"Anna","role":"resident","rooms":["sanctum","office"],"permissions":["heating_switch"]}
```

- **There is no `active`.** A profile exists for an active member only; whoever answers with
  profiles leaves the others out. The model cannot say it - it is the producer's rule.
- **The model does not make the role present**: `role` is required in the schema, but a
  producer that leaves it unset writes `"role":null`. The registry never does (the column is
  not null); a client still reads anything but `admin` as the role that reaches the least.
- The builder applies no defaults here either: `HouseholdProfile.builder()` without
  `.rooms(...)` has `null` rooms.

### Device registry: a device of the house

Since 1.7.0 `SmartDevice` is one device as the registry of `database-service` holds it - what
it is, where it is and how it is reached. The domain services (weather, blinds, light, heating)
and the dashboard read it.

| Field | Type | Notes |
|---|---|---|
| `id` | `Long` | given by the registry; left out of the JSON until it has one |
| `name` | `String`, 3-50 | required |
| `room` | `RoomName` | required |
| `kind` | `DeviceKind` | required: `blinds`, `light`, `opening sensor`, `motion sensor`, `presence sensor`, `weather station`, `bthome gateway`, `temperature sensor` (since 1.8.0), `push button` (since 1.9.0) |
| `enabled` | `Boolean` | required, **no default** |
| `vendor` | `SmartVendor` | required |
| `shelly` | `ShellyBinding` | `address` (IPv4) and `generation` (1-4) required; `deviceId`, `channel` (0-3) optional |
| `eaton` | `EatonBinding` | `gateway` (`EatonGatewayType`) and `point` (1-99) |
| `bthome` | `BthomeBinding` | `address` (Bluetooth MAC, lowercase, colons) - and no gateway, since 1.8.0 |
| `lightKind` | `LightKind` | lights only: `relay`, `dimmer`, `rgbw` |
| `placement` | `BlindsPlacement` | blinds only: `external`, `internal` |
| `positioning` | `Boolean` | blinds only, **no default** |
| `openingSensors` | `List<Long>` | blinds only (since 1.11.0): the registry ids of the `opening sensor`s of the window; none when the window has no sensor |

```json
{"id":17,"name":"Office window","room":"office","kind":"blinds","enabled":true,"vendor":"shelly",
 "shelly":{"address":"192.0.2.17","generation":2,"deviceId":"shellyplus2pm-a1b2c3d4e5f6","channel":0},
 "placement":"external","positioning":true}
```

What a consumer has to know:

- **The model does not check which binding belongs to a device.** One of `shelly`, `eaton` and
  `bthome` is expected, by the vendor and the kind; so is `lightKind` for a light only. `@Valid`
  checks each binding that is there and nothing between the fields - the rule is the
  registry's to keep when a device is stored.
- **Nothing has a default**, so a partial update can tell "not sent" from a value. `enabled` is
  required for that reason; the registry enables and disables a device with operations of its
  own.
- **The model reads an `id` that is sent.** There is no setter, but Jackson fills it through
  the constructor - which is how a reader of the registry gets it. Whoever stores a device
  ignores the id of the payload.
- **`generation` is the number the device reports about itself** (`gen`), 1 to 4. A client
  asks one thing of it: `1` is a Gen1 device (HTTP actions and polling), anything from `2`
  on - Plus, Pro, Gen3, Gen4 - speaks the same RPC and reports over MQTT. Compare with
  `>= 2`, never with `== 2`.
- **`deviceId` is the first part of the routing key** of everything the device publishes over
  MQTT (`<deviceId>.status.switch:0`). Letters, digits, `-` and `_` only - a dot or a slash
  would split the key - and the case is the device's own. A Gen1 device has none.
- **`openingSensors` link blinds to the sensors of their own window** (since 1.11.0), not to the
  room: one open window of a room must not change the other blinds there. Two ids for a window
  with two sashes, none for a window without a sensor - which a reader takes as a closed window.
  The list is empty from the constructor, `null` from the builder and left out of the JSON while
  empty: read all three as "none". The registry checks that each id is an opening sensor and that
  only blinds carry the list. **A whole-device write (`PUT`) that leaves the list out removes the
  link**, so a client that writes blinds moves to 1.11.0 before any link is stored; a reader on
  an older release only ignores the field (the mapper of Spring Boot ignores unknown properties;
  a mapper built by hand has to be told to).
- **A relay with four outputs is four devices** of the registry, told apart by `channel`.
- **A Bluetooth sensor is not bound to a gateway** (since 1.8.0; 1.7.0 had a `gatewayDeviceId`
  no release of a service ever used). A sensor broadcasts to whoever hears it: every device
  of the kind `bthome gateway` may deliver its readings, a second gateway in range is a second
  way to the same sensor. The address is the identity of the sensor - the registry holds an
  address once, so a sensor is one device of one kind - and a service that reads a gateway
  finds the device of a reading by it, whichever gateway brought it, expecting the same
  reading from two of them. **Not settled**: how a gateway names a sensor in what it
  publishes (the address, or a number of its own that its configuration maps to the address);
  that mapping belongs to the service that reads the gateway.
- **A kind says nothing of the binding.** `temperature sensor` (since 1.8.0) is today a room
  sensor of the AMX system, on an Eaton binding, and may tomorrow be a Bluetooth one: check
  which binding a device has before reading it.
- **`DeviceKind` is the vocabulary of the registry, `SmartDeviceType` the older one of the
  Eaton configuration** (`EatonDeviceConfiguration`, `EatonConfigurationResponse`), which
  `amx-service` still asks in. They share five names and are not interchangeable: a `dimmer`
  there is a `light` with `lightKind` `dimmer` here. A new reader of devices uses the
  registry models. `push button` and `motion sensor` (in `SmartDeviceType` since 1.9.0) only
  report: an Eaton push button or PIR sensor is never sent anything.
- **Do not build on 1.7.0.** It was published a few hours before 1.8.0 with a
  `BthomeBinding.gatewayDeviceId` that is required there and gone here; no service was ever
  released on it.
- **A new `DeviceKind` is not additive for a reader**: a consumer fails on a value it does not
  know, so a new kind is released and taken by every reader of the registry before a device
  of that kind is stored. The constant names (`OPENING_SENSOR`) are what the registry stores.
  **The same holds for a new `SmartDeviceType`**, whose reader is `amx-service`: it has to run
  a version that knows `push button` and `motion sensor` before `database-service` answers
  either of them for a data point - on an older one every datagram of that point fails its
  lookup.

### Calendar: workdays and days off

Since 1.7.0 `CalendarDay` is one day of the calendar of the house: `date` (a `LocalDate`),
`type` (`DayType`: `workday` or `day off`) and an optional `reason` (up to 100 characters -
the name of the holiday, or what the household wrote).

```json
{"date":"2026-11-11","type":"day off","reason":"Independence Day"}
```

### Device event: what a device of the AMX system reported

Since 1.7.0 `DeviceEvent` is the RabbitMQ payload `amx-service` publishes for an Eaton
datagram on the virtual host of the devices.

| Field | Type | Notes |
|---|---|---|
| `at` | `LocalDateTime` | required; a local date-time of the house, no offset - like `TemperatureMessage.date` |
| `vendor` | `SmartVendor` | required |
| `gateway` | `EatonGatewayType` | required |
| `point` | `Integer`, 1-99 | required |
| `event` | `EatonRxEvent` | required: `switch on`, `switch off`, `push button on`, `push button off`, `up pressed`, `up released`, `down pressed`, `down released`, `analog value`, `status` |
| `value` | `Integer` | only for `analog value` and `status` |

```json
{"at":"2026-10-12T18:30:05","vendor":"eaton","gateway":"blinds","point":44,"event":"up pressed"}
```

The event names the device the way the datagram does - gateway and point - not by the
registry: the consumer looks the device up there and drops the event of a point it does not
know.

To add or change a model, edit the relevant schema in `swagger/` and reference it from
`swagger/smart-home.yaml`; `mvn verify` regenerates the sources in
`src/main/java/cloud/cholewa/home/model`.
