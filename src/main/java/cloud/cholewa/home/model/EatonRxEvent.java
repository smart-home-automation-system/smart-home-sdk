package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EatonRxEvent {

    SWITCH_ON("switch on"),
    SWITCH_OFF("switch off"),
    PUSH_BUTTON_ON("push button on"),
    PUSH_BUTTON_OFF("push button off"),
    UP_PRESSED("up pressed"),
    UP_RELEASED("up released"),
    DOWN_PRESSED("down pressed"),
    DOWN_RELEASED("down released"),
    ANALOG_VALUE("analog value"),
    STATUS("status");

    private String value;

    EatonRxEvent(String value) {
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
    public static EatonRxEvent fromValue(String value) {
        for (EatonRxEvent b : EatonRxEvent.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
