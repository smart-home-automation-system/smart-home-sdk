package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * The permissions a household member can be granted. The enum is generated from
 * {@code swagger/household.yaml}; these tests pin what the consumers rely on: the values as
 * they travel in JSON, and the constant names the registry stores.
 */
class MemberPermissionTest {

    // The names of the constants are what database-service stores, the values what the
    // dashboard reads: renaming either needs a migration there and a release of the dashboard.
    @Test
    void shouldKnowExactlyThesePermissions() {
        assertEquals(List.of("HEATING_SWITCH"), Arrays.stream(MemberPermission.values()).map(Enum::name).toList());
        assertEquals("heating_switch", MemberPermission.HEATING_SWITCH.getValue());
        assertEquals("heating_switch", MemberPermission.HEATING_SWITCH.toString());
    }

    @Test
    void shouldReadAPermissionFromItsJsonValue() {
        assertEquals(MemberPermission.HEATING_SWITCH, MemberPermission.fromValue("heating_switch"));
        assertEquals(MemberPermission.HEATING_SWITCH, MemberPermission.fromValue("HEATING_SWITCH"));
    }

    // Inside Jackson this is a payload that does not deserialize; turning it into a 400 with a
    // readable message is up to the consuming service, as for a role or a room.
    @Test
    void shouldRefuseAPermissionItDoesNotKnow() {
        IllegalArgumentException refused =
            assertThrows(IllegalArgumentException.class, () -> MemberPermission.fromValue("everything"));

        assertEquals("Unexpected value 'everything'", refused.getMessage());
    }
}
