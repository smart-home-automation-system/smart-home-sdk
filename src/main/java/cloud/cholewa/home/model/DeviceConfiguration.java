
package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ DeviceConfiguration.JSON_PROPERTY_EATON_CONFIGURATION,
        DeviceConfiguration.JSON_PROPERTY_SHELLY_CONFIGURATION })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class DeviceConfiguration {
    public static final String JSON_PROPERTY_EATON_CONFIGURATION = "eatonConfiguration";
    private EatonConfiguration eatonConfiguration;
    public static final String JSON_PROPERTY_SHELLY_CONFIGURATION = "shellyConfiguration";
    private ShellyConfiguration shellyConfiguration;

    public DeviceConfiguration eatonConfiguration(EatonConfiguration eatonConfiguration) {

        this.eatonConfiguration = eatonConfiguration;
        return this;
    }

    @Nullable
    @Valid

    @JsonProperty(JSON_PROPERTY_EATON_CONFIGURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public EatonConfiguration getEatonConfiguration() {
        return eatonConfiguration;
    }

    @JsonProperty(JSON_PROPERTY_EATON_CONFIGURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEatonConfiguration(EatonConfiguration eatonConfiguration) {
        this.eatonConfiguration = eatonConfiguration;
    }

    public DeviceConfiguration shellyConfiguration(ShellyConfiguration shellyConfiguration) {

        this.shellyConfiguration = shellyConfiguration;
        return this;
    }

    @Nullable
    @Valid

    @JsonProperty(JSON_PROPERTY_SHELLY_CONFIGURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public ShellyConfiguration getShellyConfiguration() {
        return shellyConfiguration;
    }

    @JsonProperty(JSON_PROPERTY_SHELLY_CONFIGURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setShellyConfiguration(ShellyConfiguration shellyConfiguration) {
        this.shellyConfiguration = shellyConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceConfiguration deviceConfiguration = (DeviceConfiguration) o;
        return Objects.equals(this.eatonConfiguration, deviceConfiguration.eatonConfiguration)
                && Objects.equals(this.shellyConfiguration, deviceConfiguration.shellyConfiguration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eatonConfiguration, shellyConfiguration);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceConfiguration {\n");
        sb.append("    eatonConfiguration: ").append(toIndentedString(eatonConfiguration)).append("\n");
        sb.append("    shellyConfiguration: ").append(toIndentedString(shellyConfiguration)).append("\n");
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
