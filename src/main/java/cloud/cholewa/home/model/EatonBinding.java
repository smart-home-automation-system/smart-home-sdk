package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({EatonBinding.JSON_PROPERTY_GATEWAY, EatonBinding.JSON_PROPERTY_POINT})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EatonBinding {

    public static final String JSON_PROPERTY_GATEWAY = "gateway";
    private EatonGatewayType gateway;
    public static final String JSON_PROPERTY_POINT = "point";
    private Integer point;

    public EatonBinding gateway(EatonGatewayType gateway) {
        this.gateway = gateway;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public EatonGatewayType getGateway() {
        return gateway;
    }

    @JsonProperty(JSON_PROPERTY_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setGateway(EatonGatewayType gateway) {
        this.gateway = gateway;
    }

    public EatonBinding point(Integer point) {
        this.point = point;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(1)
    @Max(99)
    @JsonProperty(JSON_PROPERTY_POINT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getPoint() {
        return point;
    }

    @JsonProperty(JSON_PROPERTY_POINT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EatonBinding eatonBinding = (EatonBinding) o;
        return Objects.equals(this.gateway, eatonBinding.gateway) && Objects.equals(this.point, eatonBinding.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gateway, point);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EatonBinding {\n");
        sb.append("    gateway: ").append(toIndentedString(gateway)).append("\n");
        sb.append("    point: ").append(toIndentedString(point)).append("\n");
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
