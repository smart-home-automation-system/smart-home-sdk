
package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ DeviceConfigurationRequest.JSON_PROPERTY_ROOM_NAME,
        DeviceConfigurationRequest.JSON_PROPERTY_IOT_VENDOR, DeviceConfigurationRequest.JSON_PROPERTY_DEVICE_TYPE,
        DeviceConfigurationRequest.JSON_PROPERTY_DEVICE_CONFIGURATION })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class DeviceConfigurationRequest {
    public static final String JSON_PROPERTY_ROOM_NAME = "roomName";
    @NotEmpty
    private String roomName;
    public static final String JSON_PROPERTY_IOT_VENDOR = "iotVendor";
    private String iotVendor;
    public static final String JSON_PROPERTY_DEVICE_TYPE = "deviceType";
    private String deviceType;
    public static final String JSON_PROPERTY_DEVICE_CONFIGURATION = "deviceConfiguration";
    private DeviceConfiguration deviceConfiguration;

    public DeviceConfigurationRequest roomName(String roomName) {

        this.roomName = roomName;
        return this;
    }

    @Nonnull

    @NotNull

    @Size(min = 3, max = 20)

    @JsonProperty(JSON_PROPERTY_ROOM_NAME)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getRoomName() {
        return roomName;
    }

    @JsonProperty(JSON_PROPERTY_ROOM_NAME)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public DeviceConfigurationRequest iotVendor(String iotVendor) {

        this.iotVendor = iotVendor;
        return this;
    }

    @Nonnull

    @NotNull

    @Size(min = 3, max = 20)

    @JsonProperty(JSON_PROPERTY_IOT_VENDOR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getIotVendor() {
        return iotVendor;
    }

    @JsonProperty(JSON_PROPERTY_IOT_VENDOR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setIotVendor(String iotVendor) {
        this.iotVendor = iotVendor;
    }

    public DeviceConfigurationRequest deviceType(String deviceType) {

        this.deviceType = deviceType;
        return this;
    }

    @Nullable

    @Size(min = 3, max = 20)

    @JsonProperty(JSON_PROPERTY_DEVICE_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getDeviceType() {
        return deviceType;
    }

    @JsonProperty(JSON_PROPERTY_DEVICE_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public DeviceConfigurationRequest deviceConfiguration(DeviceConfiguration deviceConfiguration) {

        this.deviceConfiguration = deviceConfiguration;
        return this;
    }

    @Nonnull

    @NotNull
    @Valid

    @JsonProperty(JSON_PROPERTY_DEVICE_CONFIGURATION)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public DeviceConfiguration getDeviceConfiguration() {
        return deviceConfiguration;
    }

    @JsonProperty(JSON_PROPERTY_DEVICE_CONFIGURATION)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setDeviceConfiguration(DeviceConfiguration deviceConfiguration) {
        this.deviceConfiguration = deviceConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceConfigurationRequest deviceConfigurationRequest = (DeviceConfigurationRequest) o;
        return Objects.equals(this.roomName, deviceConfigurationRequest.roomName)
                && Objects.equals(this.iotVendor, deviceConfigurationRequest.iotVendor)
                && Objects.equals(this.deviceType, deviceConfigurationRequest.deviceType)
                && Objects.equals(this.deviceConfiguration, deviceConfigurationRequest.deviceConfiguration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, iotVendor, deviceType, deviceConfiguration);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceConfigurationRequest {\n");
        sb.append("    roomName: ").append(toIndentedString(roomName)).append("\n");
        sb.append("    iotVendor: ").append(toIndentedString(iotVendor)).append("\n");
        sb.append("    deviceType: ").append(toIndentedString(deviceType)).append("\n");
        sb.append("    deviceConfiguration: ").append(toIndentedString(deviceConfiguration)).append("\n");
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
