
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

@JsonPropertyOrder({ EatonConfigurationResponse.JSON_PROPERTY_DATA_POINT,
        EatonConfigurationResponse.JSON_PROPERTY_DEVICE_TYPE, EatonConfigurationResponse.JSON_PROPERTY_ROOM_NAME })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EatonConfigurationResponse {
    public static final String JSON_PROPERTY_DATA_POINT = "dataPoint";
    private Integer dataPoint;
    public static final String JSON_PROPERTY_DEVICE_TYPE = "deviceType";
    private DeviceType deviceType;
    public static final String JSON_PROPERTY_ROOM_NAME = "roomName";
    private RoomName roomName;

    public EatonConfigurationResponse dataPoint(Integer dataPoint) {

        this.dataPoint = dataPoint;
        return this;
    }

    @Nullable

    @Min(1)
    @Max(99)

    @JsonProperty(JSON_PROPERTY_DATA_POINT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getDataPoint() {
        return dataPoint;
    }

    @JsonProperty(JSON_PROPERTY_DATA_POINT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDataPoint(Integer dataPoint) {
        this.dataPoint = dataPoint;
    }

    public EatonConfigurationResponse deviceType(DeviceType deviceType) {

        this.deviceType = deviceType;
        return this;
    }

    @Nullable
    @Valid

    @JsonProperty(JSON_PROPERTY_DEVICE_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public DeviceType getDeviceType() {
        return deviceType;
    }

    @JsonProperty(JSON_PROPERTY_DEVICE_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public EatonConfigurationResponse roomName(RoomName roomName) {

        this.roomName = roomName;
        return this;
    }

    @Nullable
    @Valid

    @JsonProperty(JSON_PROPERTY_ROOM_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public RoomName getRoomName() {
        return roomName;
    }

    @JsonProperty(JSON_PROPERTY_ROOM_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRoomName(RoomName roomName) {
        this.roomName = roomName;
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
        return Objects.equals(this.dataPoint, eatonConfigurationResponse.dataPoint)
                && Objects.equals(this.deviceType, eatonConfigurationResponse.deviceType)
                && Objects.equals(this.roomName, eatonConfigurationResponse.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataPoint, deviceType, roomName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EatonConfigurationResponse {\n");
        sb.append("    dataPoint: ").append(toIndentedString(dataPoint)).append("\n");
        sb.append("    deviceType: ").append(toIndentedString(deviceType)).append("\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
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
