package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeviceKind {

    BLINDS("blinds"),
    LIGHT("light"),
    OPENING_SENSOR("opening sensor"),
    MOTION_SENSOR("motion sensor"),
    PRESENCE_SENSOR("presence sensor"),
    WEATHER_STATION("weather station"),
    BTHOME_GATEWAY("bthome gateway");

    private String value;

    DeviceKind(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static DeviceKind fromValue(String value) {
        for (DeviceKind b : DeviceKind.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
