package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * The rooms of the house, generated from {@code swagger/home.yaml}. The registries of
 * database-service store the constant names, heating-service stores the values, and the
 * values travel in JSON and in the temperature messages: renaming either needs a migration
 * there and a release of every reader.
 */
class RoomNameTest {

    @Test
    void shouldKnowExactlyTheseRooms() {
        assertEquals(
            ordered(
                "LOFT", "loft",
                "WARDROBE", "wardrobe",
                "BEDROOM", "bedroom",
                "LIVIA", "livia",
                "TOBI", "tobi",
                "OFFICE", "office",
                "BATHROOM_UP", "bathroom up",
                "HALL_UP", "hall up",
                "STAIRS", "stairs",
                "KITCHEN", "kitchen",
                "LIVING_ROOM", "living room",
                "CINEMA", "cinema",
                "HALL_DOWN", "hall down",
                "BATHROOM_DOWN", "bathroom down",
                "ENTRANCE", "entrance",
                "GARAGE", "garage",
                "BOILER", "boiler",
                "GARDEN", "garden",
                "SAUNA", "sauna",
                "SANCTUM", "sanctum"
            ),
            namesAndValues()
        );
    }

    @Test
    void shouldWriteHallUpWithASpace() {
        assertEquals("hall up", RoomName.HALL_UP.getValue());
        assertEquals("hall up", RoomName.HALL_UP.toString());
    }

    // HAS-289: until 1.10.0 the value was hall_up. A reader takes both spellings for one
    // release, so a message or a row written by a service still on the old SDK is understood.
    @Test
    void shouldReadHallUpInBothSpellings() {
        assertEquals(RoomName.HALL_UP, RoomName.fromValue("hall up"));
        assertEquals(RoomName.HALL_UP, RoomName.fromValue("Hall Up"));
        assertEquals(RoomName.HALL_UP, RoomName.fromValue("hall_up"));
        assertEquals(RoomName.HALL_UP, RoomName.fromValue("HALL_UP"));
    }

    // The old spelling is an alias of hall up alone: the constant name of any other room is
    // still not something a client may send.
    @Test
    void shouldRefuseTheConstantNameOfAnyOtherRoom() {
        IllegalArgumentException refused =
            assertThrows(IllegalArgumentException.class, () -> RoomName.fromValue("HALL_DOWN"));
        assertEquals("Unexpected value 'HALL_DOWN'", refused.getMessage());
        assertThrows(IllegalArgumentException.class, () -> RoomName.fromValue("bathroom_up"));
        assertThrows(IllegalArgumentException.class, () -> RoomName.fromValue("attic"));
    }

    private static Map<String, String> namesAndValues() {
        Map<String, String> result = new LinkedHashMap<>();
        Arrays.stream(RoomName.values()).forEach(room -> result.put(room.name(), room.getValue()));
        return result;
    }

    private static Map<String, String> ordered(String... namesAndValues) {
        Map<String, String> result = new LinkedHashMap<>();
        for (int i = 0; i < namesAndValues.length; i += 2) {
            result.put(namesAndValues[i], namesAndValues[i + 1]);
        }
        return result;
    }
}
