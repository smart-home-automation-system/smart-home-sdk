package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({PushSubscription.JSON_PROPERTY_ENDPOINT, PushSubscription.JSON_PROPERTY_KEYS,
        PushSubscription.JSON_PROPERTY_USER_AGENT, PushSubscription.JSON_PROPERTY_CREATED_AT})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class PushSubscription {

    public static final String JSON_PROPERTY_ENDPOINT = "endpoint";
    private String endpoint;
    public static final String JSON_PROPERTY_KEYS = "keys";
    private PushSubscriptionKeys keys;
    public static final String JSON_PROPERTY_USER_AGENT = "userAgent";
    private String userAgent;
    public static final String JSON_PROPERTY_CREATED_AT = "createdAt";
    private LocalDateTime createdAt;

    public PushSubscription endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^https://\\S+$")
    @Size(min = 1, max = 2000)
    @JsonProperty(JSON_PROPERTY_ENDPOINT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getEndpoint() {
        return endpoint;
    }

    @JsonProperty(JSON_PROPERTY_ENDPOINT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public PushSubscription keys(PushSubscriptionKeys keys) {
        this.keys = keys;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_KEYS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public PushSubscriptionKeys getKeys() {
        return keys;
    }

    @JsonProperty(JSON_PROPERTY_KEYS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setKeys(PushSubscriptionKeys keys) {
        this.keys = keys;
    }

    public PushSubscription userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Nullable
    @Size(max = 500)
    @JsonProperty(JSON_PROPERTY_USER_AGENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getUserAgent() {
        return userAgent;
    }

    @JsonProperty(JSON_PROPERTY_USER_AGENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public PushSubscription createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_CREATED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonProperty(JSON_PROPERTY_CREATED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PushSubscription pushSubscription = (PushSubscription) o;
        return Objects.equals(this.endpoint, pushSubscription.endpoint)
                && Objects.equals(this.keys, pushSubscription.keys)
                && Objects.equals(this.userAgent, pushSubscription.userAgent)
                && Objects.equals(this.createdAt, pushSubscription.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpoint, keys, userAgent, createdAt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PushSubscription {\n");
        sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
        sb.append("    keys: ").append(toIndentedString(keys)).append("\n");
        sb.append("    userAgent: ").append(toIndentedString(userAgent)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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
