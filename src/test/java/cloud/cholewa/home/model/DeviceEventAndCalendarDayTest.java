package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * The event amx-service publishes for a device of the AMX system, and a day of the calendar
 * of the house. Generated from {@code swagger/device-events.yaml} and {@code calendar.yaml};
 * these tests pin the JSON names, the types and what is mandatory.
 */
class DeviceEventAndCalendarDayTest {

    @Test
    void shouldPublishTheseJsonPropertiesOfAnEventAndNoOther() {
        assertEquals(Set.of("at", "vendor", "gateway", "point", "event", "value"), JsonProperties.of(DeviceEvent.class));
    }

    // Everything but the value: only an analog value and a status carry a number.
    @Test
    void shouldRequireEverythingOfAnEventButItsValue() throws NoSuchMethodException {
        for (String getter : new String[] {"getAt", "getVendor", "getGateway", "getPoint", "getEvent"}) {
            assertNotNull(DeviceEvent.class.getMethod(getter).getAnnotation(NotNull.class), getter);
        }
        assertNull(DeviceEvent.class.getMethod("getValue").getAnnotation(NotNull.class));
        assertNull(new DeviceEvent().getValue());
    }

    // The time is a local date-time without an offset, like the date of TemperatureMessage:
    // the publisher and the consumers run on the clock of the house.
    @Test
    void shouldTypeAnEventLikeTheOtherMessageOfTheBroker() throws NoSuchMethodException {
        assertEquals(LocalDateTime.class, DeviceEvent.class.getMethod("getAt").getReturnType());
        assertEquals(
            TemperatureMessage.class.getMethod("getDate").getReturnType(),
            DeviceEvent.class.getMethod("getAt").getReturnType()
        );
        assertEquals(SmartVendor.class, DeviceEvent.class.getMethod("getVendor").getReturnType());
        assertEquals(EatonGatewayType.class, DeviceEvent.class.getMethod("getGateway").getReturnType());
        assertEquals(EatonRxEvent.class, DeviceEvent.class.getMethod("getEvent").getReturnType());
        assertEquals(Integer.class, DeviceEvent.class.getMethod("getValue").getReturnType());
        assertEquals(1, DeviceEvent.class.getMethod("getPoint").getAnnotation(Min.class).value());
        assertEquals(99, DeviceEvent.class.getMethod("getPoint").getAnnotation(Max.class).value());
    }

    @Test
    void shouldPublishTheseJsonPropertiesOfADayAndNoOther() {
        assertEquals(Set.of("date", "type", "reason"), JsonProperties.of(CalendarDay.class));
    }

    // A day is a date of the house, not a moment; the reason is there only for a day somebody
    // or some holiday explains.
    @Test
    void shouldRequireTheDateAndTheTypeOfADay() throws NoSuchMethodException {
        assertEquals(LocalDate.class, CalendarDay.class.getMethod("getDate").getReturnType());
        assertNotNull(CalendarDay.class.getMethod("getDate").getAnnotation(NotNull.class));
        assertNotNull(CalendarDay.class.getMethod("getType").getAnnotation(NotNull.class));
        assertNull(CalendarDay.class.getMethod("getReason").getAnnotation(NotNull.class));
        assertEquals(100, CalendarDay.class.getMethod("getReason").getAnnotation(Size.class).max());
        assertNull(new CalendarDay().getType());
    }
}
