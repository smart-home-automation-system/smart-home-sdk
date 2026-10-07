package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * The profile of a household member as the web dashboard receives it. The class is generated
 * from {@code swagger/household.yaml}; these tests pin what the consumers rely on - above all
 * what the model does not carry.
 */
class HouseholdProfileTest {

    // The point of the model: it travels to every browser in the house, so it holds what a
    // profile is and nothing of the rest of the registry. A phone number or a device added
    // here - by copying a field from HouseholdMember - has to fail a test, not pass a review.
    @Test
    void shouldCarryTheNameTheRoleAndTheRoomsAndNothingElse() {
        Set<String> fields = Arrays.stream(HouseholdProfile.class.getDeclaredFields())
            .filter(field -> !Modifier.isStatic(field.getModifiers()))
            .map(Field::getName)
            .collect(Collectors.toSet());

        assertEquals(Set.of("name", "role", "rooms"), fields);
        // a field inherited from a shared parent would be serialized just the same, and the
        // check above would not see it
        assertEquals(Object.class, HouseholdProfile.class.getSuperclass());
    }

    // The same from the side Jackson looks at: the properties the getters publish.
    @Test
    void shouldPublishNoOtherJsonProperty() {
        Set<String> properties = Arrays.stream(HouseholdProfile.class.getMethods())
            .filter(method -> method.getName().startsWith("get"))
            .map(method -> method.getAnnotation(JsonProperty.class))
            .filter(Objects::nonNull)
            .map(JsonProperty::value)
            .collect(Collectors.toSet());

        assertEquals(Set.of("name", "role", "rooms"), properties);
    }

    // What a reader of the JSON relies on, and what no test here can see in the JSON itself
    // (there is no Jackson on the test classpath): the name and the role are always written,
    // and an empty list of rooms is left out - a missing "rooms" means none.
    @Test
    void shouldAlwaysWriteNameAndRoleAndLeaveOutEmptyRooms() throws NoSuchMethodException {
        assertEquals(JsonInclude.Include.NON_EMPTY, HouseholdProfile.class.getAnnotation(JsonInclude.class).value());
        assertEquals(JsonInclude.Include.ALWAYS, includeOf("getName"));
        assertEquals(JsonInclude.Include.ALWAYS, includeOf("getRole"));
        assertEquals(JsonInclude.Include.USE_DEFAULTS, includeOf("getRooms"));
    }

    private static JsonInclude.Include includeOf(String getter) throws NoSuchMethodException {
        return HouseholdProfile.class.getMethod(getter).getAnnotation(JsonInclude.class).value();
    }

    @Test
    void shouldExposeItsFieldsUnderTheirJsonNames() {
        assertEquals("name", HouseholdProfile.JSON_PROPERTY_NAME);
        assertEquals("role", HouseholdProfile.JSON_PROPERTY_ROLE);
        assertEquals("rooms", HouseholdProfile.JSON_PROPERTY_ROOMS);
    }

    @Test
    void shouldKeepSeveralRoomsOfOneMemberInTheirOrder() {
        HouseholdProfile profile = new HouseholdProfile()
            .name("Anna")
            .role(MemberRole.ADMIN)
            .addRoomsItem(RoomName.SANCTUM)
            .addRoomsItem(RoomName.OFFICE);

        assertEquals("Anna", profile.getName());
        assertEquals(MemberRole.ADMIN, profile.getRole());
        assertEquals(List.of(RoomName.SANCTUM, RoomName.OFFICE), profile.getRooms());
    }

    // An empty list is left out of the JSON (NON_EMPTY on the class), so a reader takes a
    // missing "rooms" as none.
    @Test
    void shouldHaveNoRoomsWhenNoneIsSet() {
        HouseholdProfile profile = new HouseholdProfile();

        assertTrue(profile.getRooms().isEmpty());
    }

    // No default, as in HouseholdMember: the registry always sets the role, and a profile
    // without one must not pass for a resident by itself.
    @Test
    void shouldHaveNoRoleWhenNoneIsSet() {
        HouseholdProfile profile = new HouseholdProfile();

        assertNull(profile.getRole());
    }

    // Lombok's builder does not apply the defaults of the fields.
    @Test
    void shouldLeaveRoomsUnsetWhenBuiltWithoutThem() {
        HouseholdProfile profile = HouseholdProfile.builder().name("Anna").role(MemberRole.RESIDENT).build();

        assertNull(profile.getRooms());
    }
}
