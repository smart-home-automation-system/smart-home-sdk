
package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomName {

    LOFT("Loft"),

    WARDROBE("Wardrobe"),

    BEDROOM("Bedroom"),

    LIVIA("Livia"),

    TOBI("Tobi"),

    OFFICE("Office"),

    BATHROOM_UP("BathroomUp"),

    HALL_UP("HallUp"),

    STAIRS("Stairs"),

    KITCHEN("Kitchen"),

    LIVING_ROOM("LivingRoom"),

    CINEMA("Cinema"),

    HALL_DOWN("HallDown"),

    BATHROOM_DOWN("BathroomDown"),

    ENTRANCE("Entrance"),

    GARAGE("Garage"),

    BOILER("Boiler"),

    GARDEN("Garden"),

    SAUNA("Sauna"),

    SANCTUM("Sanctum");

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
