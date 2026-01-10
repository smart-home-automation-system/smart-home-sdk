package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({EatonConfigurationResponse.JSON_PROPERTY_POINT, EatonConfigurationResponse.JSON_PROPERTY_TYPE,
        EatonConfigurationResponse.JSON_PROPERTY_ROOM})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EatonConfigurationResponse {

    public static final String JSON_PROPERTY_POINT = "point";
    private Integer point;
    public static final String JSON_PROPERTY_TYPE = "type";
    private SmartDeviceType type;
    public static final String JSON_PROPERTY_ROOM = "room";
    private RoomName room;

    public EatonConfigurationResponse point(Integer point) {
        this.point = point;
        return this;
    }

    @Nullable
    @Min(1)
    @Max(99)
    @JsonProperty(JSON_PROPERTY_POINT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getPoint() {
        return point;
    }

    @JsonProperty(JSON_PROPERTY_POINT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPoint(Integer point) {
        this.point = point;
    }

    public EatonConfigurationResponse type(SmartDeviceType type) {
        this.type = type;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public SmartDeviceType getType() {
        return type;
    }

    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setType(SmartDeviceType type) {
        this.type = type;
    }

    public EatonConfigurationResponse room(RoomName room) {
        this.room = room;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_ROOM)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public RoomName getRoom() {
        return room;
    }

    @JsonProperty(JSON_PROPERTY_ROOM)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRoom(RoomName room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EatonConfigurationResponse eatonConfigurationResponse = (EatonConfigurationResponse) o;
        return Objects.equals(this.point, eatonConfigurationResponse.point)
                && Objects.equals(this.type, eatonConfigurationResponse.type)
                && Objects.equals(this.room, eatonConfigurationResponse.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, type, room);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EatonConfigurationResponse {\n");
        sb.append("    point: ").append(toIndentedString(point)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    room: ").append(toIndentedString(room)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
