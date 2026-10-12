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

@JsonPropertyOrder({BthomeBinding.JSON_PROPERTY_GATEWAY_DEVICE_ID, BthomeBinding.JSON_PROPERTY_ADDRESS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class BthomeBinding {

    public static final String JSON_PROPERTY_GATEWAY_DEVICE_ID = "gatewayDeviceId";
    private String gatewayDeviceId;
    public static final String JSON_PROPERTY_ADDRESS = "address";
    private String address;

    public BthomeBinding gatewayDeviceId(String gatewayDeviceId) {
        this.gatewayDeviceId = gatewayDeviceId;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    @Size(min = 3, max = 50)
    @JsonProperty(JSON_PROPERTY_GATEWAY_DEVICE_ID)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getGatewayDeviceId() {
        return gatewayDeviceId;
    }

    @JsonProperty(JSON_PROPERTY_GATEWAY_DEVICE_ID)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setGatewayDeviceId(String gatewayDeviceId) {
        this.gatewayDeviceId = gatewayDeviceId;
    }

    public BthomeBinding address(String address) {
        this.address = address;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^([0-9a-f]{2}:){5}[0-9a-f]{2}$")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BthomeBinding bthomeBinding = (BthomeBinding) o;
        return Objects.equals(this.gatewayDeviceId, bthomeBinding.gatewayDeviceId)
                && Objects.equals(this.address, bthomeBinding.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gatewayDeviceId, address);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BthomeBinding {\n");
        sb.append("    gatewayDeviceId: ").append(toIndentedString(gatewayDeviceId)).append("\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
