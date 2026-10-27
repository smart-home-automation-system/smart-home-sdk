package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
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
                "placement", "positioning", "openingSensors", "appliance", "inverter"
            ),
            JsonProperties.of(SmartDevice.class)
        );
        assertEquals(
            Set.of("brand", "serialNumber", "address", "registrationNumber", "loggerSerial", "batterySerialNumber"),
            JsonProperties.of(InverterBinding.class)
        );
        assertEquals(
            Set.of(
                "runningAboveWatts", "idleBelowWatts", "minRunMinutes", "finishedAfterMinutes",
                "reminderEveryMinutes", "maxReminders", "quietFrom", "quietTo"
            ),
            JsonProperties.of(ApplianceSettings.class)
        );
        assertEquals(Set.of("address", "generation", "deviceId", "channel"), JsonProperties.of(ShellyBinding.class));
        assertEquals(Set.of("gateway", "point"), JsonProperties.of(EatonBinding.class));
        //the address alone: a sensor is not bound to a gateway, any registered one may deliver it
        assertEquals(Set.of("address"), JsonProperties.of(BthomeBinding.class));
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
        assertNull(device.getAppliance());
        assertNull(device.getInverter());

        InverterBinding inverter = new InverterBinding();
        assertNull(inverter.getBrand());
        assertNull(inverter.getSerialNumber());
        assertNull(inverter.getAddress());
        assertNull(inverter.getRegistrationNumber());
        assertNull(inverter.getLoggerSerial());
        assertNull(inverter.getBatterySerialNumber());

        ApplianceSettings appliance = new ApplianceSettings();
        assertNull(appliance.getRunningAboveWatts());
        assertNull(appliance.getIdleBelowWatts());
        assertNull(appliance.getMinRunMinutes());
        assertNull(appliance.getFinishedAfterMinutes());
        assertNull(appliance.getReminderEveryMinutes());
        assertNull(appliance.getMaxReminders());
        assertNull(appliance.getQuietFrom());
        assertNull(appliance.getQuietTo());

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
                "getPlacement", "getPositioning", "getOpeningSensors", "getAppliance", "getInverter"}) {
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
    // generation are what a client cannot do without. The generation is the number the device
    // reports, 1 to 4 - a registry that took 1 and 2 only would refuse a Gen3 as it names itself.
    @Test
    void shouldBoundTheShellyBinding() throws NoSuchMethodException {
        assertNotNull(ShellyBinding.class.getMethod("getAddress").getAnnotation(NotNull.class));
        assertNotNull(ShellyBinding.class.getMethod("getGeneration").getAnnotation(NotNull.class));
        assertNull(ShellyBinding.class.getMethod("getDeviceId").getAnnotation(NotNull.class));
        assertNull(ShellyBinding.class.getMethod("getChannel").getAnnotation(NotNull.class));

        assertEquals(1, ShellyBinding.class.getMethod("getGeneration").getAnnotation(Min.class).value());
        assertEquals(4, ShellyBinding.class.getMethod("getGeneration").getAnnotation(Max.class).value());
        assertEquals(0, ShellyBinding.class.getMethod("getChannel").getAnnotation(Min.class).value());
        assertEquals(299, ShellyBinding.class.getMethod("getChannel").getAnnotation(Max.class).value());
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

    // The address is all a Bluetooth sensor is known by - there is no gateway to tell two
    // bindings apart - so it is the one property and it is required.
    @Test
    void shouldKnowABluetoothSensorByItsAddressAlone() throws NoSuchMethodException {
        assertEquals(Set.of("address"), JsonProperties.of(BthomeBinding.class));
        assertNotNull(BthomeBinding.class.getMethod("getAddress").getAnnotation(NotNull.class));
        assertThrows(NoSuchMethodException.class, () -> BthomeBinding.class.getMethod("getGatewayDeviceId"));
    }

    // The opening sensors of a blind (HAS-290): a list of registry ids in the order they were
    // given - a window with two sashes has two - and none for a window without a sensor. Like
    // every array it starts empty from the constructor and is null from the builder, so a reader
    // treats both as "none". Each id is a registry id, never 0 or below.
    @Test
    void shouldLinkTheBlindsToTheOpeningSensorsOfItsWindowByRegistryIds() throws NoSuchMethodException {
        assertEquals(List.of(), new SmartDevice().getOpeningSensors());
        assertNull(SmartDevice.builder().build().getOpeningSensors());
        assertEquals(
            List.of(31L, 30L),
            new SmartDevice().addOpeningSensorsItem(31L).addOpeningSensorsItem(30L).getOpeningSensors()
        );

        AnnotatedParameterizedType type = (AnnotatedParameterizedType) SmartDevice.class
            .getMethod("getOpeningSensors").getAnnotatedReturnType();
        assertEquals(List.class, ((ParameterizedType) type.getType()).getRawType());
        assertEquals(1L, type.getAnnotatedActualTypeArguments()[0].getAnnotation(Min.class).value());
    }

    // The settings of an appliance (HAS-271): the registry stores all of them or none, so every
    // one is required inside the part, and the part itself is validated with the device.
    @Test
    void shouldRequireAndBoundEverySettingOfAnAppliance() throws NoSuchMethodException {
        assertNotNull(SmartDevice.class.getMethod("getAppliance").getAnnotation(Valid.class));
        assertEquals(ApplianceSettings.class, SmartDevice.class.getMethod("getAppliance").getReturnType());

        assertBounds("getRunningAboveWatts", 5, 3000);
        assertBounds("getIdleBelowWatts", 0, 100);
        assertBounds("getMinRunMinutes", 1, 180);
        assertBounds("getFinishedAfterMinutes", 1, 60);
        assertBounds("getReminderEveryMinutes", 5, 180);
        assertBounds("getMaxReminders", 0, 10);
        for (String getter : new String[] {"getQuietFrom", "getQuietTo"}) {
            assertNotNull(ApplianceSettings.class.getMethod(getter).getAnnotation(NotNull.class), getter);
            assertEquals(String.class, ApplianceSettings.class.getMethod(getter).getReturnType(), getter);
        }
    }

    // A local time of the house as HH:mm - text, since the library has no databind to read a time.
    @Test
    void shouldTakeAQuietTimeAsHoursAndMinutesOnly() throws NoSuchMethodException {
        String pattern = ApplianceSettings.class.getMethod("getQuietFrom").getAnnotation(Pattern.class).regexp();
        assertEquals(pattern, ApplianceSettings.class.getMethod("getQuietTo").getAnnotation(Pattern.class).regexp());

        assertTrue("00:00".matches(pattern));
        assertTrue("07:00".matches(pattern));
        assertTrue("23:59".matches(pattern));
        assertFalse("24:00".matches(pattern));
        assertFalse("7:00".matches(pattern));
        assertFalse("07:60".matches(pattern));
        assertFalse("07:00:00".matches(pattern));
        assertFalse("".matches(pattern));
    }

    // The inverter part (HAS-326): the brand and the serial number are what every inverter has; the
    // fields of one brand are optional here and required or refused by the registry. Validated with
    // the device.
    @Test
    void shouldRequireTheBrandAndTheSerialNumberOfAnInverterAndNothingElse() throws NoSuchMethodException {
        assertNotNull(SmartDevice.class.getMethod("getInverter").getAnnotation(Valid.class));
        assertEquals(InverterBinding.class, SmartDevice.class.getMethod("getInverter").getReturnType());
        assertEquals(InverterBrand.class, InverterBinding.class.getMethod("getBrand").getReturnType());

        for (String getter : new String[] {"getBrand", "getSerialNumber"}) {
            assertNotNull(InverterBinding.class.getMethod(getter).getAnnotation(NotNull.class), getter);
        }
        for (String getter : new String[] {"getAddress", "getRegistrationNumber", "getLoggerSerial",
                "getBatterySerialNumber"}) {
            assertNull(InverterBinding.class.getMethod(getter).getAnnotation(NotNull.class), getter);
        }
    }

    // Serial and registration numbers as the plates print them: capitals and digits, nothing else.
    @Test
    void shouldTakeTheNumbersOfAnInverterInCapitalsAndDigitsOnly() throws NoSuchMethodException {
        for (String getter : new String[] {"getSerialNumber", "getRegistrationNumber", "getBatterySerialNumber"}) {
            String pattern = InverterBinding.class.getMethod(getter).getAnnotation(Pattern.class).regexp();

            assertTrue("H1234567890ABC".matches(pattern), getter);
            assertFalse("h1234567890abc".matches(pattern), getter);
            assertFalse("H123-4567".matches(pattern), getter);
            assertFalse("H123 4567".matches(pattern), getter);
        }
        String address = InverterBinding.class.getMethod("getAddress").getAnnotation(Pattern.class).regexp();
        assertEquals(ShellyBinding.class.getMethod("getAddress").getAnnotation(Pattern.class).regexp(), address);
    }

    // The Solarman protocol carries the logger serial as four bytes in every frame.
    @Test
    void shouldBoundTheLoggerSerialToFourBytes() throws NoSuchMethodException {
        var method = InverterBinding.class.getMethod("getLoggerSerial");

        assertEquals(Long.class, method.getReturnType());
        assertEquals(1L, method.getAnnotation(Min.class).value());
        assertEquals(4294967295L, method.getAnnotation(Max.class).value());
    }

    @Test
    void shouldBoundTheEatonPointLikeTheEatonConfiguration() throws NoSuchMethodException {
        assertNotNull(EatonBinding.class.getMethod("getGateway").getAnnotation(NotNull.class));
        assertNotNull(EatonBinding.class.getMethod("getPoint").getAnnotation(NotNull.class));
        assertEquals(1, EatonBinding.class.getMethod("getPoint").getAnnotation(Min.class).value());
        assertEquals(99, EatonBinding.class.getMethod("getPoint").getAnnotation(Max.class).value());
        assertEquals(EatonGatewayType.class, EatonBinding.class.getMethod("getGateway").getReturnType());
    }

    private static void assertBounds(String getter, long min, long max) throws NoSuchMethodException {
        var method = ApplianceSettings.class.getMethod(getter);
        assertNotNull(method.getAnnotation(NotNull.class), getter);
        assertEquals(min, method.getAnnotation(Min.class).value(), getter);
        assertEquals(max, method.getAnnotation(Max.class).value(), getter);
    }
}
