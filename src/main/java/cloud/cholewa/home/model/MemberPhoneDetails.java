package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({MemberPhoneDetails.JSON_PROPERTY_NAME, MemberPhoneDetails.JSON_PROPERTY_MAC})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class MemberPhoneDetails {

    public static final String JSON_PROPERTY_NAME = "name";
    private String name;
    public static final String JSON_PROPERTY_MAC = "mac";
    private String mac;

    public MemberPhoneDetails name(String name) {
        this.name = name;
        return this;
    }

    @Nonnull
    @NotNull
    @JsonProperty(JSON_PROPERTY_NAME)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getName() {
        return name;
    }

    @JsonProperty(JSON_PROPERTY_NAME)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setName(String name) {
        this.name = name;
    }

    public MemberPhoneDetails mac(String mac) {
        this.mac = mac;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^([0-9a-f]{2}:){5}[0-9a-f]{2}$")
    @JsonProperty(JSON_PROPERTY_MAC)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getMac() {
        return mac;
    }

    @JsonProperty(JSON_PROPERTY_MAC)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberPhoneDetails memberPhoneDetails = (MemberPhoneDetails) o;
        return Objects.equals(this.name, memberPhoneDetails.name) && Objects.equals(this.mac, memberPhoneDetails.mac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mac);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MemberPhoneDetails {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    mac: ").append(toIndentedString(mac)).append("\n");
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
