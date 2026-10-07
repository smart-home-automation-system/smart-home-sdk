package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * The role and the rooms of a household member as the generated model carries them. The
 * classes are generated from {@code swagger/household.yaml}; these tests pin what the
 * consumers rely on, so a change of the schema or of the templates that alters it fails here.
 */
class HouseholdMemberTest {

    @Test
    void shouldBeResidentWithoutRoomsWhenNothingIsSet() {
        HouseholdMember member = new HouseholdMember();

        assertEquals(MemberRole.RESIDENT, member.getRole());
        assertTrue(member.getRooms().isEmpty());
    }

    @Test
    void shouldKeepSeveralRoomsOfOneMember() {
        HouseholdMember member = new HouseholdMember()
            .role(MemberRole.ADMIN)
            .addRoomsItem(RoomName.OFFICE)
            .addRoomsItem(RoomName.SANCTUM);

        assertEquals(MemberRole.ADMIN, member.getRole());
        assertEquals(List.of(RoomName.OFFICE, RoomName.SANCTUM), List.copyOf(member.getRooms()));
    }

    @Test
    void shouldListRoomOnceWhenItIsAddedTwice() {
        HouseholdMember member = new HouseholdMember()
            .addRoomsItem(RoomName.OFFICE)
            .addRoomsItem(RoomName.OFFICE);

        assertEquals(Set.of(RoomName.OFFICE), member.getRooms());
    }

    // Lombok's builder does not apply the defaults of the fields: a consumer building a member
    // has to set the role and the rooms itself (as it already has to for `active`).
    @Test
    void shouldLeaveRoleAndRoomsUnsetWhenBuiltWithoutThem() {
        HouseholdMember member = HouseholdMember.builder().name("Anna").build();

        assertNull(member.getRole());
        assertNull(member.getRooms());
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
