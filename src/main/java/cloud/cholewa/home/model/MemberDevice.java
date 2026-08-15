package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({MemberDevice.JSON_PROPERTY_NAME, MemberDevice.JSON_PROPERTY_TYPE, MemberDevice.JSON_PROPERTY_MAC})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class MemberDevice {

    public static final String JSON_PROPERTY_NAME = "name";
    private String name;

    public enum TypeEnum {

        PHONE("phone"),
        TABLET("tablet");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static TypeEnum fromValue(String value) {
            for (TypeEnum b : TypeEnum.values()) {
                if (b.value.equalsIgnoreCase(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    public static final String JSON_PROPERTY_TYPE = "type";
    private TypeEnum type = TypeEnum.PHONE;
    public static final String JSON_PROPERTY_MAC = "mac";
    private String mac;

    public MemberDevice name(String name) {
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

    public MemberDevice type(TypeEnum type) {
        this.type = type;
        return this;
    }

    @Nonnull
    @NotNull
    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public TypeEnum getType() {
        return type;
    }

    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setType(TypeEnum type) {
        this.type = type;
    }

    public MemberDevice mac(String mac) {
        this.mac = mac;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$")
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
        MemberDevice memberDevice = (MemberDevice) o;
        return Objects.equals(this.name, memberDevice.name) && Objects.equals(this.type, memberDevice.type)
                && Objects.equals(this.mac, memberDevice.mac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, mac);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MemberDevice {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
