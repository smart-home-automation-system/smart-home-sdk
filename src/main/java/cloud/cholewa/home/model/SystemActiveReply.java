package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({SystemActiveReply.JSON_PROPERTY_ACTIVE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class SystemActiveReply {

    public static final String JSON_PROPERTY_ACTIVE = "active";
    private Boolean active;

    public SystemActiveReply active(Boolean active) {
        this.active = active;
        return this;
    }

    @Nonnull
    @NotNull
    @JsonProperty(JSON_PROPERTY_ACTIVE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Boolean getActive() {
        return active;
    }

    @JsonProperty(JSON_PROPERTY_ACTIVE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SystemActiveReply systemActiveReply = (SystemActiveReply) o;
        return Objects.equals(this.active, systemActiveReply.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SystemActiveReply {\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
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
