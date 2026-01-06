package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({DeviceStatusUpdate.JSON_PROPERTY_ROOM_NAME, DeviceStatusUpdate.JSON_PROPERTY_DEVICE_TYPE,
        DeviceStatusUpdate.JSON_PROPERTY_VALUE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class DeviceStatusUpdate {
    public static final String JSON_PROPERTY_ROOM_NAME = "roomName";
    private RoomName roomName;
    public static final String JSON_PROPERTY_DEVICE_TYPE = "deviceType";
    private DeviceType deviceType;
    public static final String JSON_PROPERTY_VALUE = "value";
    private String value;
    public DeviceStatusUpdate roomName(RoomName roomName) {
        this.roomName = roomName;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_ROOM_NAME)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public RoomName getRoomName() {
        return roomName;
    }

    @JsonProperty(JSON_PROPERTY_ROOM_NAME)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setRoomName(RoomName roomName) {
        this.roomName = roomName;
    }

    public DeviceStatusUpdate deviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_DEVICE_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public DeviceType getDeviceType() {
        return deviceType;
    }

    @JsonProperty(JSON_PROPERTY_DEVICE_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public DeviceStatusUpdate value(String value) {
        this.value = value;
        return this;
    }

    @Nonnull
    @NotNull
    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getValue() {
        return value;
    }

    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceStatusUpdate deviceStatusUpdate = (DeviceStatusUpdate) o;
        return Objects.equals(this.roomName, deviceStatusUpdate.roomName)
                && Objects.equals(this.deviceType, deviceStatusUpdate.deviceType)
                && Objects.equals(this.value, deviceStatusUpdate.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, deviceType, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceStatusUpdate {\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    deviceType: ").append(toIndentedString(deviceType)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
