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

@JsonPropertyOrder({GreeBinding.JSON_PROPERTY_ADDRESS, GreeBinding.JSON_PROPERTY_MAC})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class GreeBinding {

    public static final String JSON_PROPERTY_ADDRESS = "address";
    private String address;
    public static final String JSON_PROPERTY_MAC = "mac";
    private String mac;

    public GreeBinding address(String address) {
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

    public GreeBinding mac(String mac) {
        this.mac = mac;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^[0-9a-f]{12}$")
    @Size(min = 12, max = 12)
    @JsonProperty(JSON_PROPERTY_MAC)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getMac() {
        return mac;
    }

    @JsonProperty(JSON_PROPERTY_MAC)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GreeBinding greeBinding = (GreeBinding) o;
        return Objects.equals(this.address, greeBinding.address) && Objects.equals(this.mac, greeBinding.mac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, mac);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GreeBinding {\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
        sb.append("    mac: ").append(toIndentedString(mac)).append("\n");
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
