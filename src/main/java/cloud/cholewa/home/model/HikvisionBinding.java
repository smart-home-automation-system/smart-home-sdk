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

@JsonPropertyOrder({HikvisionBinding.JSON_PROPERTY_ADDRESS, HikvisionBinding.JSON_PROPERTY_SERIAL_NUMBER})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class HikvisionBinding {

    public static final String JSON_PROPERTY_ADDRESS = "address";
    private String address;
    public static final String JSON_PROPERTY_SERIAL_NUMBER = "serialNumber";
    private String serialNumber;

    public HikvisionBinding address(String address) {
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

    public HikvisionBinding serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9][A-Za-z0-9()-]*$")
    @Size(min = 9, max = 64)
    @JsonProperty(JSON_PROPERTY_SERIAL_NUMBER)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty(JSON_PROPERTY_SERIAL_NUMBER)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HikvisionBinding hikvisionBinding = (HikvisionBinding) o;
        return Objects.equals(this.address, hikvisionBinding.address)
                && Objects.equals(this.serialNumber, hikvisionBinding.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, serialNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HikvisionBinding {\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
        sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
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
