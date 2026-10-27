package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({InverterBinding.JSON_PROPERTY_BRAND, InverterBinding.JSON_PROPERTY_SERIAL_NUMBER,
        InverterBinding.JSON_PROPERTY_ADDRESS, InverterBinding.JSON_PROPERTY_REGISTRATION_NUMBER,
        InverterBinding.JSON_PROPERTY_LOGGER_SERIAL, InverterBinding.JSON_PROPERTY_BATTERY_SERIAL_NUMBER})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class InverterBinding {

    public static final String JSON_PROPERTY_BRAND = "brand";
    private InverterBrand brand;
    public static final String JSON_PROPERTY_SERIAL_NUMBER = "serialNumber";
    private String serialNumber;
    public static final String JSON_PROPERTY_ADDRESS = "address";
    private String address;
    public static final String JSON_PROPERTY_REGISTRATION_NUMBER = "registrationNumber";
    private String registrationNumber;
    public static final String JSON_PROPERTY_LOGGER_SERIAL = "loggerSerial";
    private Long loggerSerial;
    public static final String JSON_PROPERTY_BATTERY_SERIAL_NUMBER = "batterySerialNumber";
    private String batterySerialNumber;

    public InverterBinding brand(InverterBrand brand) {
        this.brand = brand;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_BRAND)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public InverterBrand getBrand() {
        return brand;
    }

    @JsonProperty(JSON_PROPERTY_BRAND)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setBrand(InverterBrand brand) {
        this.brand = brand;
    }

    public InverterBinding serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^[A-Z0-9]+$")
    @Size(min = 5, max = 40)
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

    public InverterBinding address(String address) {
        this.address = address;
        return this;
    }

    @Nullable
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])$")
    @Size(max = 15)
    @JsonProperty(JSON_PROPERTY_ADDRESS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getAddress() {
        return address;
    }

    @JsonProperty(JSON_PROPERTY_ADDRESS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setAddress(String address) {
        this.address = address;
    }

    public InverterBinding registrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    @Nullable
    @Pattern(regexp = "^[A-Z0-9]+$")
    @Size(min = 5, max = 20)
    @JsonProperty(JSON_PROPERTY_REGISTRATION_NUMBER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @JsonProperty(JSON_PROPERTY_REGISTRATION_NUMBER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public InverterBinding loggerSerial(Long loggerSerial) {
        this.loggerSerial = loggerSerial;
        return this;
    }

    @Nullable
    @Min(1L)
    @Max(4294967295L)
    @JsonProperty(JSON_PROPERTY_LOGGER_SERIAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getLoggerSerial() {
        return loggerSerial;
    }

    @JsonProperty(JSON_PROPERTY_LOGGER_SERIAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLoggerSerial(Long loggerSerial) {
        this.loggerSerial = loggerSerial;
    }

    public InverterBinding batterySerialNumber(String batterySerialNumber) {
        this.batterySerialNumber = batterySerialNumber;
        return this;
    }

    @Nullable
    @Pattern(regexp = "^[A-Z0-9]+$")
    @Size(min = 5, max = 40)
    @JsonProperty(JSON_PROPERTY_BATTERY_SERIAL_NUMBER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getBatterySerialNumber() {
        return batterySerialNumber;
    }

    @JsonProperty(JSON_PROPERTY_BATTERY_SERIAL_NUMBER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBatterySerialNumber(String batterySerialNumber) {
        this.batterySerialNumber = batterySerialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InverterBinding inverterBinding = (InverterBinding) o;
        return Objects.equals(this.brand, inverterBinding.brand)
                && Objects.equals(this.serialNumber, inverterBinding.serialNumber)
                && Objects.equals(this.address, inverterBinding.address)
                && Objects.equals(this.registrationNumber, inverterBinding.registrationNumber)
                && Objects.equals(this.loggerSerial, inverterBinding.loggerSerial)
                && Objects.equals(this.batterySerialNumber, inverterBinding.batterySerialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, serialNumber, address, registrationNumber, loggerSerial, batterySerialNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InverterBinding {\n");
        sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
        sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
        sb.append("    registrationNumber: ").append(toIndentedString(registrationNumber)).append("\n");
        sb.append("    loggerSerial: ").append(toIndentedString(loggerSerial)).append("\n");
        sb.append("    batterySerialNumber: ").append(toIndentedString(batterySerialNumber)).append("\n");
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
