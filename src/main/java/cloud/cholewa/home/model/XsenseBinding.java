package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({XsenseBinding.JSON_PROPERTY_STATION_SERIAL, XsenseBinding.JSON_PROPERTY_DEVICE_ID})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class XsenseBinding {

    public static final String JSON_PROPERTY_STATION_SERIAL = "stationSerial";
    private String stationSerial;
    public static final String JSON_PROPERTY_DEVICE_ID = "deviceId";
    private String deviceId;

    public XsenseBinding stationSerial(String stationSerial) {
        this.stationSerial = stationSerial;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^SBS50[0-9A-F]{8}$")
    @Size(min = 13, max = 13)
    @JsonProperty(JSON_PROPERTY_STATION_SERIAL)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getStationSerial() {
        return stationSerial;
    }

    @JsonProperty(JSON_PROPERTY_STATION_SERIAL)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setStationSerial(String stationSerial) {
        this.stationSerial = stationSerial;
    }

    public XsenseBinding deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^[0-9A-F]{8}$")
    @Size(min = 8, max = 8)
    @JsonProperty(JSON_PROPERTY_DEVICE_ID)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty(JSON_PROPERTY_DEVICE_ID)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XsenseBinding xsenseBinding = (XsenseBinding) o;
        return Objects.equals(this.stationSerial, xsenseBinding.stationSerial)
                && Objects.equals(this.deviceId, xsenseBinding.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationSerial, deviceId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class XsenseBinding {\n");
        sb.append("    stationSerial: ").append(toIndentedString(stationSerial)).append("\n");
        sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
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
