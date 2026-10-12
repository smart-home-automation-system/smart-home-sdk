package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ShellyBinding.JSON_PROPERTY_ADDRESS, ShellyBinding.JSON_PROPERTY_GENERATION,
        ShellyBinding.JSON_PROPERTY_DEVICE_ID, ShellyBinding.JSON_PROPERTY_CHANNEL})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ShellyBinding {

    public static final String JSON_PROPERTY_ADDRESS = "address";
    private String address;
    public static final String JSON_PROPERTY_GENERATION = "generation";
    private Integer generation;
    public static final String JSON_PROPERTY_DEVICE_ID = "deviceId";
    private String deviceId;
    public static final String JSON_PROPERTY_CHANNEL = "channel";
    private Integer channel;

    public ShellyBinding address(String address) {
        this.address = address;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])$")
    @Size(max = 15)
    @JsonProperty(JSON_PROPERTY_ADDRESS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getAddress() {
        return address;
    }

    @JsonProperty(JSON_PROPERTY_ADDRESS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setAddress(String address) {
        this.address = address;
    }

    public ShellyBinding generation(Integer generation) {
        this.generation = generation;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(1)
    @Max(2)
    @JsonProperty(JSON_PROPERTY_GENERATION)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getGeneration() {
        return generation;
    }

    @JsonProperty(JSON_PROPERTY_GENERATION)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public ShellyBinding deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @Nullable
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    @Size(min = 3, max = 50)
    @JsonProperty(JSON_PROPERTY_DEVICE_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty(JSON_PROPERTY_DEVICE_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public ShellyBinding channel(Integer channel) {
        this.channel = channel;
        return this;
    }

    @Nullable
    @Min(0)
    @Max(3)
    @JsonProperty(JSON_PROPERTY_CHANNEL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getChannel() {
        return channel;
    }

    @JsonProperty(JSON_PROPERTY_CHANNEL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShellyBinding shellyBinding = (ShellyBinding) o;
        return Objects.equals(this.address, shellyBinding.address)
                && Objects.equals(this.generation, shellyBinding.generation)
                && Objects.equals(this.deviceId, shellyBinding.deviceId)
                && Objects.equals(this.channel, shellyBinding.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, generation, deviceId, channel);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ShellyBinding {\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
        sb.append("    generation: ").append(toIndentedString(generation)).append("\n");
        sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
        sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
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
