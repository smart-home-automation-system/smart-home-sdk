package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * The role and the rooms of a household member as the generated model carries them. The
 * classes are generated from {@code swagger/household.yaml}; these tests pin what the
 * consumers rely on, so a change of the schema or of the templates that alters it fails here.
 */
class HouseholdMemberTest {

    // No default on purpose: a missing role means "not sent". The registry makes a new member a
    // resident and keeps the role of an existing one - with a default here, an update of the
    // phone alone would turn an admin into a resident.
    @Test
    void shouldHaveNoRoleWhenNoneIsSet() {
        HouseholdMember member = new HouseholdMember();

        assertNull(member.getRole());
    }

    @Test
    void shouldHaveNoRoomsWhenNoneIsSet() {
        HouseholdMember member = new HouseholdMember();

        assertTrue(member.getRooms().isEmpty());
    }

    @Test
    void shouldKeepSeveralRoomsOfOneMemberInTheirOrder() {
        HouseholdMember member = new HouseholdMember()
            .role(MemberRole.ADMIN)
            .addRoomsItem(RoomName.SANCTUM)
            .addRoomsItem(RoomName.OFFICE);

        assertEquals(MemberRole.ADMIN, member.getRole());
        assertEquals(List.of(RoomName.SANCTUM, RoomName.OFFICE), member.getRooms());
    }

    // The model is a list, not a set: it does not drop a repeated room behind the caller's
    // back. Refusing such a list is the job of the registry, which can say so in its answer.
    @Test
    void shouldKeepRepeatedRoomForTheRegistryToRefuse() {
        HouseholdMember member = new HouseholdMember()
            .addRoomsItem(RoomName.OFFICE)
            .addRoomsItem(RoomName.OFFICE);

        assertEquals(List.of(RoomName.OFFICE, RoomName.OFFICE), member.getRooms());
    }

    // Lombok's builder does not apply the defaults of the fields: a consumer building a member
    // gets no list of rooms unless it sets one (as it already gets no `active`).
    @Test
    void shouldLeaveRoomsUnsetWhenBuiltWithoutThem() {
        HouseholdMember member = HouseholdMember.builder().name("Anna").build();

        assertNull(member.getRole());
        assertNull(member.getRooms());
        assertNull(member.getPermissions());
    }

    // Granted to one member at a time: a member nobody granted anything to has none, and an
    // empty list is left out of the JSON like the rooms.
    @Test
    void shouldHaveNoPermissionWhenNoneIsGranted() {
        HouseholdMember member = new HouseholdMember();

        assertTrue(member.getPermissions().isEmpty());
    }

    @Test
    void shouldCarryThePermissionsOfAMember() {
        HouseholdMember member = new HouseholdMember()
            .name("Anna")
            .role(MemberRole.RESIDENT)
            .addPermissionsItem(MemberPermission.HEATING_SWITCH);

        assertEquals(List.of(MemberPermission.HEATING_SWITCH), member.getPermissions());
        assertEquals("permissions", HouseholdMember.JSON_PROPERTY_PERMISSIONS);
    }

    // What no test here can see in the JSON itself (there is no Jackson on the test classpath):
    // an empty list of rooms or of permissions is left out, so a reader takes a missing one as
    // none.
    @Test
    void shouldLeaveEmptyRoomsAndPermissionsOutOfTheJson() throws NoSuchMethodException {
        assertEquals(JsonInclude.Include.NON_EMPTY, HouseholdMember.class.getAnnotation(JsonInclude.class).value());
        assertEquals(JsonInclude.Include.USE_DEFAULTS, includeOf("getRooms"));
        assertEquals(JsonInclude.Include.USE_DEFAULTS, includeOf("getPermissions"));
    }

    private static JsonInclude.Include includeOf(String getter) throws NoSuchMethodException {
        return HouseholdMember.class.getMethod(getter).getAnnotation(JsonInclude.class).value();
    }

    @Test
    void shouldExposeRoleAndRoomsUnderTheirJsonNames() {
        assertEquals("role", HouseholdMember.JSON_PROPERTY_ROLE);
        assertEquals("rooms", HouseholdMember.JSON_PROPERTY_ROOMS);
    }

    @ParameterizedTest
    @ValueSource(strings = {"admin", "ADMIN", "Admin"})
    void shouldReadRoleWhateverItsCase(String value) {
        assertEquals(MemberRole.ADMIN, MemberRole.fromValue(value));
    }

    @Test
    void shouldWriteRoleInLowerCase() {
        assertEquals("admin", MemberRole.ADMIN.getValue());
        assertEquals("resident", MemberRole.RESIDENT.getValue());
        assertEquals(2, MemberRole.values().length);
    }

    @ParameterizedTest
    @ValueSource(strings = {"owner", "", "resident "})
    void shouldRejectUnknownRole(String value) {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> MemberRole.fromValue(value));

        assertEquals("Unexpected value '" + value + "'", exception.getMessage());
    }
}
