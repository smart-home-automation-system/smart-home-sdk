package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum HouseholdEvent {

    APPLIANCE_FINISHED("appliance finished"),
    APPLIANCE_REMINDER("appliance reminder"),
    APPLIANCE_ACKNOWLEDGED("appliance acknowledged");

    private String value;

    HouseholdEvent(String value) {
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
    public static HouseholdEvent fromValue(String value) {
        for (HouseholdEvent b : HouseholdEvent.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
