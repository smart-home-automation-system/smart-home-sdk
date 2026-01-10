package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SmartDeviceType {

    TEMPERATURE_SENSOR("temperature sensor"),
    BLINDS("blinds"),
    LIGHT("light"),
    DIMMER("dimmer"),
    OTHER("other");

    private String value;

    SmartDeviceType(String value) {
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
    public static SmartDeviceType fromValue(String value) {
        for (SmartDeviceType b : SmartDeviceType.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
