package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomName {

    LOFT("loft"),
    WARDROBE("wardrobe"),
    BEDROOM("bedroom"),
    LIVIA("livia"),
    TOBI("tobi"),
    OFFICE("office"),
    BATHROOM_UP("bathroom_up"),
    HALL_UP("hall_up"),
    STAIRS("stairs"),
    KITCHEN("kitchen"),
    LIVING_ROOM("living_room"),
    CINEMA("cinema"),
    HALL_DOWN("hall_down"),
    BATHROOM_DOWN("bathroom_down"),
    ENTRANCE("entrance"),
    GARAGE("garage"),
    BOILER("boiler"),
    GARDEN("garden"),
    SAUNA("sauna"),
    SANCTUM("sanctum");

    private String value;

    RoomName(String value) {
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
    public static RoomName fromValue(String value) {
        for (RoomName b : RoomName.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
