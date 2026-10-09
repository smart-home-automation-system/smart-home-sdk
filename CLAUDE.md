# smart-home-sdk

Shared domain and API models of the Smart Home Automation System: room names, device types,
Eaton configuration, RabbitMQ payloads, the household registry. A **model-only** library - no
Spring, no logic. Organization-wide rules (who writes code, branches, reviews, releases) are in
the org's `organization.md`; this file holds what is specific to this repository.

## How it is built

- **The source of truth is `swagger/`**, not `src/main/java`. One schema file per domain
  (`home.yaml`, `device-commons.yaml`, `eaton.yaml`, `rabbit.yaml`, `household.yaml`);
  `smart-home.yaml` aggregates them and is the only file the generator reads. **A schema that
  is not referenced from `smart-home.yaml` generates nothing** - `shelly.yaml` is such a file
  today, and so are single schemas inside the others (marked `# not used`).
- `mvn verify` runs the OpenAPI generator (models only, custom templates in
  `swagger/templates/`), then the formatter and the import sorter, and writes the classes into
  `src/main/java/cloud/cholewa/home/model`. **The generated classes are committed**: after a
  schema change run `mvn verify` and commit the result together with the schema. Never edit a
  generated class by hand - the next build overwrites it (`mvn clean` deletes the folder).
- A change to a template changes **every** generated class: check `git diff` on the models
  that were not meant to change.
- Tests (`src/test`) are plain JUnit on the generated classes - they pin what consumers rely
  on (defaults, enum values, JSON names). There is no Jackson on the test classpath.

## Rules that are easy to break

- **No `jackson-databind`.** The library depends on `jackson-annotations` only (HAS-119): the
  consumers run Jackson 3, whose databind lives in `tools.jackson`, and both major versions
  read the `com.fasterxml.jackson.annotation` annotations. The generator still knows places
  where it wants databind, and the build then fails on
  `package com.fasterxml.jackson.databind... does not exist`. Do not add the dependency -
  change the schema so the generator does not need it.
- **No `uniqueItems`** (HAS-191). It is the known such place: the generator turns the array
  into a `Set` and puts `@JsonDeserialize(as = LinkedHashSet.class)` on the setter. Without
  that annotation Jackson fills a plain `HashSet`, and the order of the items is lost - for
  enums it even differs between two runs of the JVM. A list that must not repeat an item
  stays a list here, and the service that owns the data refuses the repetition.
- **A default makes "not sent" invisible** (HAS-191, and `active` before it). A field with a
  default reads the same whether the caller left it out or sent the default, so an update
  that omits it resets it: `database-service` needed separate activate / deactivate
  operations because `active` defaults to `true`. Give a field a default only when no
  consumer updates it partially. An array is the exception that cannot be helped: the
  generator always starts it as an empty list.
- **`$ref` paths**: inside `swagger/` a sibling file is `./home.yaml#/components/schemas/X`.
  (`eaton.yaml` and `rabbit.yaml` use `../home.yaml`, which works only together with the
  trailing slash in the references of `smart-home.yaml` - do not copy that form.)
- **A default on an enum goes on the enum schema**, should one ever be wanted: a `default`
  written next to a `$ref` is ignored by OpenAPI 3.0.
- **Defaults reach `new X()` and deserialization, not the builder.** The classes carry
  Lombok's `@SuperBuilder` without `@Builder.Default`, so `X.builder().build()` leaves every
  defaulted field `null`.
- **A permission is a value of `MemberPermission`, never a boolean field** (HAS-202). What a
  member may do beyond their role is a list on `HouseholdMember` and `HouseholdProfile`: the
  next thing somebody is allowed is one more enum value, not one more field with a default
  that a partial update would reset. The constant names are what the registry stores - a
  rename needs a migration there, as for `RoomName` and `MemberRole`. **Adding a value is
  cheap here and not for the readers**: a consumer that has the field fails on a value it does
  not know, and `presence-service` reads the whole registry at once - so a new value is
  released, taken by every consumer that deserializes the household models, and only then
  granted to anybody.
- **Empty collections are omitted from JSON** (`@JsonInclude(NON_EMPTY)` on every class): a
  reader of the JSON treats a missing array as empty.
- **Bounds belong in the schema** (`required`, `minLength`, `pattern`, `enum`, `uniqueItems`):
  the generated model then carries them as Bean Validation annotations or types, and a
  service validates a payload with `@Valid` alone.

## Versioning and consumers

- The pom keeps `0.0.1-SNAPSHOT`; the version comes from the release tag (`package.yml`
  publishes to GitHub Packages of the organization on a GitHub release).
- Additive change -> minor; a tightened or removed field, or a renamed enum value -> think of
  every consumer first. The consumer list is in the org's `organization.md`; a review of a
  change here includes its impact on each of them, recorded on the PR.
- A new **optional** field is safe for a consumer still on the old version only because
  Jackson ignores unknown properties there - when in doubt, run the old jar against the new
  JSON instead of assuming.
- The Installation snippet of `readme.md` is moved to the coming version inside the PR that
  will be released as it (as every release so far) - so release right after the merge, and
  under that number.

## Public repository

No hosts, addresses, tokens or names of household members - examples use invented people and
numbers.
