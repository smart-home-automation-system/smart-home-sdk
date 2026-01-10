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

@JsonPropertyOrder({EatonStatusReply.JSON_PROPERTY_GATEWAY, EatonStatusReply.JSON_PROPERTY_MESSAGE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EatonStatusReply {

    public static final String JSON_PROPERTY_GATEWAY = "gateway";
    private EatonGateway gateway;
    public static final String JSON_PROPERTY_MESSAGE = "message";
    private String message;

    public EatonStatusReply gateway(EatonGateway gateway) {
        this.gateway = gateway;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public EatonGateway getGateway() {
        return gateway;
    }

    @JsonProperty(JSON_PROPERTY_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setGateway(EatonGateway gateway) {
        this.gateway = gateway;
    }

    public EatonStatusReply message(String message) {
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
        EatonStatusReply eatonStatusReply = (EatonStatusReply) o;
        return Objects.equals(this.gateway, eatonStatusReply.gateway)
                && Objects.equals(this.message, eatonStatusReply.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gateway, message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EatonStatusReply {\n");
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
