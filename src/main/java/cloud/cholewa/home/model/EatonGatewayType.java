package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IotVendor {
    SHELLY("Shelly"), EATON("Eaton"), OTHER("Other");
    private String value;
    IotVendor(String value) {
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
    public static IotVendor fromValue(String value) {
        for (IotVendor b : IotVendor.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
