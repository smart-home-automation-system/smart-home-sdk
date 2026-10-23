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

@JsonPropertyOrder({PushSubscriptionKeys.JSON_PROPERTY_P256DH, PushSubscriptionKeys.JSON_PROPERTY_AUTH})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class PushSubscriptionKeys {

    public static final String JSON_PROPERTY_P256DH = "p256dh";
    private String p256dh;
    public static final String JSON_PROPERTY_AUTH = "auth";
    private String auth;

    public PushSubscriptionKeys p256dh(String p256dh) {
        this.p256dh = p256dh;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_-]+=?$")
    @Size(min = 87, max = 88)
    @JsonProperty(JSON_PROPERTY_P256DH)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getP256dh() {
        return p256dh;
    }

    @JsonProperty(JSON_PROPERTY_P256DH)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setP256dh(String p256dh) {
        this.p256dh = p256dh;
    }

    public PushSubscriptionKeys auth(String auth) {
        this.auth = auth;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_-]+={0,2}$")
    @Size(min = 22, max = 24)
    @JsonProperty(JSON_PROPERTY_AUTH)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getAuth() {
        return auth;
    }

    @JsonProperty(JSON_PROPERTY_AUTH)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PushSubscriptionKeys pushSubscriptionKeys = (PushSubscriptionKeys) o;
        return Objects.equals(this.p256dh, pushSubscriptionKeys.p256dh)
                && Objects.equals(this.auth, pushSubscriptionKeys.auth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p256dh, auth);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PushSubscriptionKeys {\n");
        sb.append("    p256dh: ").append(toIndentedString(p256dh)).append("\n");
        sb.append("    auth: ").append(toIndentedString(auth)).append("\n");
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
