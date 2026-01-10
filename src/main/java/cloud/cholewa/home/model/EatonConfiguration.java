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

@JsonPropertyOrder({EatonConfiguration.JSON_PROPERTY_DATA_POINT, EatonConfiguration.JSON_PROPERTY_EATON_GATEWAY})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EatonConfiguration {

    public static final String JSON_PROPERTY_DATA_POINT = "dataPoint";
    private Integer dataPoint;
    public static final String JSON_PROPERTY_EATON_GATEWAY = "eatonGateway";
    private EatonGateway eatonGateway;

    public EatonConfiguration dataPoint(Integer dataPoint) {
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

    public EatonConfiguration eatonGateway(EatonGateway eatonGateway) {
        this.eatonGateway = eatonGateway;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_EATON_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public EatonGateway getEatonGateway() {
        return eatonGateway;
    }

    @JsonProperty(JSON_PROPERTY_EATON_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEatonGateway(EatonGateway eatonGateway) {
        this.eatonGateway = eatonGateway;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EatonConfiguration eatonConfiguration = (EatonConfiguration) o;
        return Objects.equals(this.dataPoint, eatonConfiguration.dataPoint)
                && Objects.equals(this.eatonGateway, eatonConfiguration.eatonGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataPoint, eatonGateway);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EatonConfiguration {\n");
        sb.append("    dataPoint: ").append(toIndentedString(dataPoint)).append("\n");
        sb.append("    eatonGateway: ").append(toIndentedString(eatonGateway)).append("\n");
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
