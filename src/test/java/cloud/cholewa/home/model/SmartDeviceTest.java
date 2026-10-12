package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * A device of the registry and the three ways it is reached. The classes are generated from
 * {@code swagger/device-registry.yaml}; these tests pin what the consumers rely on: the JSON
 * names, what is mandatory, the bounds - and that nothing has a default.
 */
class SmartDeviceTest {

    @Test
    void shouldPublishTheseJsonPropertiesAndNoOther() {
        assertEquals(
            Set.of(
                "id", "name", "room", "kind", "enabled", "vendor", "shelly", "eaton", "bthome", "lightKind",
                "placement", "positioning"
            ),
            jsonPropertiesOf(SmartDevice.class)
        );
        assertEquals(Set.of("address", "generation", "deviceId", "channel"), jsonPropertiesOf(ShellyBinding.class));
        assertEquals(Set.of("gateway", "point"), jsonPropertiesOf(EatonBinding.class));
        assertEquals(Set.of("gatewayDeviceId", "address"), jsonPropertiesOf(BthomeBinding.class));
    }

    // The registry is updated partially: a field with a default would read the same whether
    // the caller left it out or sent it, and an update of the name alone would switch a device
    // on, or tell blinds that cannot be positioned that they can.
    @Test
    void shouldHaveNoDefaultAnywhere() {
        SmartDevice device = new SmartDevice();

        assertNull(device.getId());
        assertNull(device.getName());
        assertNull(device.getRoom());
        assertNull(device.getKind());
        assertNull(device.getEnabled());
        assertNull(device.getVendor());
        assertNull(device.getShelly());
        assertNull(device.getEaton());
        assertNull(device.getBthome());
        assertNull(device.getLightKind());
        assertNull(device.getPlacement());
        assertNull(device.getPositioning());

        ShellyBinding shelly = new ShellyBinding();
        assertNull(shelly.getGeneration());
        assertNull(shelly.getChannel());
        assertNull(shelly.getDeviceId());
    }

    @Test
    void shouldRequireWhatEveryDeviceHasAndNothingOfOneKindOnly() throws NoSuchMethodException {
        for (String getter : new String[] {"getName", "getRoom", "getKind", "getEnabled", "getVendor"}) {
            assertNotNull(SmartDevice.class.getMethod(getter).getAnnotation(NotNull.class), getter);
        }
        for (String getter : new String[] {"getId", "getShelly", "getEaton", "getBthome", "getLightKind",
                "getPlacement", "getPositioning"}) {
            assertNull(SmartDevice.class.getMethod(getter).getAnnotation(NotNull.class), getter);
        }
    }

    // The id is the registry's to give, so the model has no setter for it - the registry builds
    // its answer with the builder. That does not keep it out of a payload: Jackson reads an id
    // through the constructor, which is how a reader of the registry gets it, and a client that
    // sends one has it in the model too. Ignoring it on a write is the registry's to do.
    @Test
    void shouldCarryTheIdThroughTheBuilderAndTheConstructorAndHaveNoSetter() throws NoSuchMethodException {
        assertEquals(42L, SmartDevice.builder().id(42L).build().getId());
        assertEquals(42L, new SmartDevice(42L).getId());
        assertThrows(NoSuchMethodException.class, () -> SmartDevice.class.getMethod("setId", Long.class));
        assertNotNull(SmartDevice.class.getConstructor(Long.class).getAnnotation(JsonCreator.class));
    }

    // A Gen1 device has no MQTT id and many devices have no channel; the address and the
    // generation are what a client cannot do without.
    @Test
    void shouldBoundTheShellyBinding() throws NoSuchMethodException {
        assertNotNull(ShellyBinding.class.getMethod("getAddress").getAnnotation(NotNull.class));
        assertNotNull(ShellyBinding.class.getMethod("getGeneration").getAnnotation(NotNull.class));
        assertNull(ShellyBinding.class.getMethod("getDeviceId").getAnnotation(NotNull.class));
        assertNull(ShellyBinding.class.getMethod("getChannel").getAnnotation(NotNull.class));

        assertEquals(1, ShellyBinding.class.getMethod("getGeneration").getAnnotation(Min.class).value());
        assertEquals(2, ShellyBinding.class.getMethod("getGeneration").getAnnotation(Max.class).value());
        assertEquals(0, ShellyBinding.class.getMethod("getChannel").getAnnotation(Min.class).value());
        assertEquals(3, ShellyBinding.class.getMethod("getChannel").getAnnotation(Max.class).value());
    }

    // The id is the first part of the routing key of everything the device publishes: a dot or
    // a slash in it would split the key. Not every id starts with "shelly", and the case is the
    // device's own.
    @Test
    void shouldTakeTheMqttIdsDevicesReallyHaveAndNoDotOrSlash() throws NoSuchMethodException {
        String pattern = ShellyBinding.class.getMethod("getDeviceId").getAnnotation(Pattern.class).regexp();

        assertTrue("shellyplus2pm-a1b2c3d4e5f6".matches(pattern));
        assertTrue("ShellyWallDisplay-A1B2C3D4E5F6".matches(pattern));
        assertTrue("neowatervalve-a1b2c3d4e5f6".matches(pattern));
        assertFalse("shelly.plus2pm".matches(pattern));
        assertFalse("shelly/plus2pm".matches(pattern));
        assertFalse("shelly plus2pm".matches(pattern));
        assertFalse("".matches(pattern));

        assertEquals(
            pattern,
            BthomeBinding.class.getMethod("getGatewayDeviceId").getAnnotation(Pattern.class).regexp()
        );
    }

    @Test
    void shouldTakeAnIpv4AddressOnly() throws NoSuchMethodException {
        String pattern = ShellyBinding.class.getMethod("getAddress").getAnnotation(Pattern.class).regexp();

        assertTrue("192.0.2.17".matches(pattern));
        assertTrue("0.0.0.0".matches(pattern));
        assertTrue("255.255.255.255".matches(pattern));
        assertFalse("192.0.2".matches(pattern));
        assertFalse("192.0.2.256".matches(pattern));
        assertFalse("192.0.2.17:80".matches(pattern));
        assertFalse("http://192.0.2.17".matches(pattern));
        assertFalse("device.example".matches(pattern));
    }

    // Lowercase and colon separated, like the MAC address of a member's phone.
    @Test
    void shouldTakeABluetoothAddressInOneSpellingOnly() throws NoSuchMethodException {
        String pattern = BthomeBinding.class.getMethod("getAddress").getAnnotation(Pattern.class).regexp();

        assertTrue("7c:c6:b6:00:00:01".matches(pattern));
        assertFalse("7C:C6:B6:00:00:01".matches(pattern));
        assertFalse("7c-c6-b6-00-00-01".matches(pattern));
        assertFalse("7cc6b6000001".matches(pattern));
    }

    @Test
    void shouldBoundTheEatonPointLikeTheEatonConfiguration() throws NoSuchMethodException {
        assertNotNull(EatonBinding.class.getMethod("getGateway").getAnnotation(NotNull.class));
        assertNotNull(EatonBinding.class.getMethod("getPoint").getAnnotation(NotNull.class));
        assertEquals(1, EatonBinding.class.getMethod("getPoint").getAnnotation(Min.class).value());
        assertEquals(99, EatonBinding.class.getMethod("getPoint").getAnnotation(Max.class).value());
        assertEquals(EatonGatewayType.class, EatonBinding.class.getMethod("getGateway").getReturnType());
    }

    private static Set<String> jsonPropertiesOf(Class<?> model) {
        return Arrays.stream(model.getMethods())
            .filter(method -> method.getName().startsWith("get"))
            .map(method -> method.getAnnotation(JsonProperty.class))
            .filter(Objects::nonNull)
            .map(JsonProperty::value)
            .collect(Collectors.toSet());
    }
}
