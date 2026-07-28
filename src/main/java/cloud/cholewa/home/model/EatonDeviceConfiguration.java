package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({EatonDeviceConfiguration.JSON_PROPERTY_POINT, EatonDeviceConfiguration.JSON_PROPERTY_TYPE,
        EatonDeviceConfiguration.JSON_PROPERTY_GATEWAY, EatonDeviceConfiguration.JSON_PROPERTY_ROOM})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EatonDeviceConfiguration {

    public static final String JSON_PROPERTY_POINT = "point";
    private Integer point;
    public static final String JSON_PROPERTY_TYPE = "type";
    private SmartDeviceType type;
    public static final String JSON_PROPERTY_GATEWAY = "gateway";
    private EatonGatewayType gateway;
    public static final String JSON_PROPERTY_ROOM = "room";
    private RoomName room;

    public EatonDeviceConfiguration point(Integer point) {
        this.point = point;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(1)
    @Max(99)
    @JsonProperty(JSON_PROPERTY_POINT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getPoint() {
        return point;
    }

    @JsonProperty(JSON_PROPERTY_POINT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setPoint(Integer point) {
        this.point = point;
    }

    public EatonDeviceConfiguration type(SmartDeviceType type) {
        this.type = type;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public SmartDeviceType getType() {
        return type;
    }

    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setType(SmartDeviceType type) {
        this.type = type;
    }

    public EatonDeviceConfiguration gateway(EatonGatewayType gateway) {
        this.gateway = gateway;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public EatonGatewayType getGateway() {
        return gateway;
    }

    @JsonProperty(JSON_PROPERTY_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setGateway(EatonGatewayType gateway) {
        this.gateway = gateway;
    }

    public EatonDeviceConfiguration room(RoomName room) {
        this.room = room;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_ROOM)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public RoomName getRoom() {
        return room;
    }

    @JsonProperty(JSON_PROPERTY_ROOM)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
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
        EatonDeviceConfiguration eatonDeviceConfiguration = (EatonDeviceConfiguration) o;
        return Objects.equals(this.point, eatonDeviceConfiguration.point)
                && Objects.equals(this.type, eatonDeviceConfiguration.type)
                && Objects.equals(this.gateway, eatonDeviceConfiguration.gateway)
                && Objects.equals(this.room, eatonDeviceConfiguration.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, type, gateway, room);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EatonDeviceConfiguration {\n");
        sb.append("    point: ").append(toIndentedString(point)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    gateway: ").append(toIndentedString(gateway)).append("\n");
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
