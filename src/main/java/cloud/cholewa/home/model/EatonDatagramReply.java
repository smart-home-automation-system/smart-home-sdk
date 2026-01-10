package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({EatonDatagramReply.JSON_PROPERTY_GATEWAY, EatonDatagramReply.JSON_PROPERTY_MESSAGE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EatonDatagramReply {

    public static final String JSON_PROPERTY_GATEWAY = "gateway";
    private EatonGatewayType gateway;
    public static final String JSON_PROPERTY_MESSAGE = "message";
    private String message;

    public EatonDatagramReply gateway(EatonGatewayType gateway) {
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

    public EatonDatagramReply message(String message) {
        this.message = message;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^5A(,[0-9A-Fa-f]{1,2}){8,18},A5$")
    @JsonProperty(JSON_PROPERTY_MESSAGE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getMessage() {
        return message;
    }

    @JsonProperty(JSON_PROPERTY_MESSAGE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EatonDatagramReply eatonDatagramReply = (EatonDatagramReply) o;
        return Objects.equals(this.gateway, eatonDatagramReply.gateway)
                && Objects.equals(this.message, eatonDatagramReply.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gateway, message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EatonDatagramReply {\n");
        sb.append("    gateway: ").append(toIndentedString(gateway)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
