package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum HouseholdEvent {

    APPLIANCE_FINISHED("appliance finished"),
    APPLIANCE_REMINDER("appliance reminder"),
    APPLIANCE_ACKNOWLEDGED("appliance acknowledged"),
    SMOG_WARNING("smog warning"),
    SMOG_OVER("smog over"),
    DOORBELL_RINGING("doorbell ringing"),
    GATE_OPENED("gate opened"),
    GARAGE_GATE_OPEN_COLD("garage gate open cold"),
    GARAGE_GATE_OPEN_EMPTY_HOUSE("garage gate open empty house"),
    GARAGE_GATE_OPEN_LONG("garage gate open long"),
    GARAGE_GATE_CLOSED("garage gate closed");

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
