package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

/**
 * The enums of the device registry, of the calendar and of the device events. They are
 * generated from {@code swagger/device-registry.yaml}, {@code calendar.yaml} and
 * {@code device-events.yaml}; these tests pin what the consumers rely on: the constant names,
 * which the registry stores, and the values as they travel in JSON.
 */
class DeviceRegistryEnumsTest {

    // The names are what database-service stores and the values what the dashboard and the
    // services read: renaming either needs a migration there, and a release of every reader.
    @Test
    void shouldKnowExactlyTheseDeviceKinds() {
        assertEquals(
            ordered(
                "BLINDS", "blinds",
                "LIGHT", "light",
                "OPENING_SENSOR", "opening sensor",
                "MOTION_SENSOR", "motion sensor",
                "PRESENCE_SENSOR", "presence sensor",
                "WEATHER_STATION", "weather station",
                "BTHOME_GATEWAY", "bthome gateway",
                "TEMPERATURE_SENSOR", "temperature sensor"
            ),
            namesAndValues(DeviceKind.values(), DeviceKind::getValue)
        );
    }

    @Test
    void shouldKnowExactlyTheseLightKinds() {
        assertEquals(
            ordered("RELAY", "relay", "DIMMER", "dimmer", "RGBW", "rgbw"),
            namesAndValues(LightKind.values(), LightKind::getValue)
        );
    }

    @Test
    void shouldKnowExactlyThesePlacementsOfBlinds() {
        assertEquals(
            ordered("EXTERNAL", "external", "INTERNAL", "internal"),
            namesAndValues(BlindsPlacement.values(), BlindsPlacement::getValue)
        );
    }

    @Test
    void shouldKnowExactlyTheseDayTypes() {
        assertEquals(
            ordered("WORKDAY", "workday", "DAY_OFF", "day off"),
            namesAndValues(DayType.values(), DayType::getValue)
        );
    }

    // The same ten names as RxEventType of amx-service, which reads the event from the
    // datagram - so that service maps one enum to the other by name. Never by position: the
    // order here is the one of the task, not the one of that enum.
    @Test
    void shouldKnowExactlyTheseEatonEvents() {
        assertEquals(
            ordered(
                "SWITCH_ON", "switch on",
                "SWITCH_OFF", "switch off",
                "PUSH_BUTTON_ON", "push button on",
                "PUSH_BUTTON_OFF", "push button off",
                "UP_PRESSED", "up pressed",
                "UP_RELEASED", "up released",
                "DOWN_PRESSED", "down pressed",
                "DOWN_RELEASED", "down released",
                "ANALOG_VALUE", "analog value",
                "STATUS", "status"
            ),
            namesAndValues(EatonRxEvent.values(), EatonRxEvent::getValue)
        );
    }

    // A value with a space in it is read back by that value, in any case - and not by the name
    // of its constant: "OPENING_SENSOR" is not something a client may send.
    @Test
    void shouldReadAValueWithASpaceAndRefuseTheConstantName() {
        assertEquals(DeviceKind.OPENING_SENSOR, DeviceKind.fromValue("opening sensor"));
        assertEquals(DeviceKind.OPENING_SENSOR, DeviceKind.fromValue("Opening Sensor"));
        assertEquals(DayType.DAY_OFF, DayType.fromValue("day off"));
        assertEquals(EatonRxEvent.PUSH_BUTTON_ON, EatonRxEvent.fromValue("push button on"));

        IllegalArgumentException refused =
            assertThrows(IllegalArgumentException.class, () -> DeviceKind.fromValue("OPENING_SENSOR"));
        assertEquals("Unexpected value 'OPENING_SENSOR'", refused.getMessage());
    }

    // Inside Jackson this is a payload that does not deserialize; turning it into a 400 with a
    // readable message is up to the consuming service, as for a role or a room.
    @Test
    void shouldRefuseAValueItDoesNotKnow() {
        assertThrows(IllegalArgumentException.class, () -> DeviceKind.fromValue("thermostat"));
        assertThrows(IllegalArgumentException.class, () -> LightKind.fromValue("neon"));
        assertThrows(IllegalArgumentException.class, () -> BlindsPlacement.fromValue("roof"));
        assertThrows(IllegalArgumentException.class, () -> DayType.fromValue("holiday"));
        assertThrows(IllegalArgumentException.class, () -> EatonRxEvent.fromValue("long press"));
    }

    private static <E extends Enum<E>> Map<String, String> namesAndValues(E[] constants, Function<E, String> value) {
        Map<String, String> result = new LinkedHashMap<>();
        Arrays.stream(constants).forEach(constant -> result.put(constant.name(), value.apply(constant)));
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
