package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({HouseHoldMember.JSON_PROPERTY_NAME, HouseHoldMember.JSON_PROPERTY_PHONE,
        HouseHoldMember.JSON_PROPERTY_DEVICE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class HouseHoldMember {

    public static final String JSON_PROPERTY_NAME = "name";
    private String name;
    public static final String JSON_PROPERTY_PHONE = "phone";
    private String phone;
    public static final String JSON_PROPERTY_DEVICE = "device";
    private List<@Valid MemberDevice> device = new ArrayList<>();

    public HouseHoldMember name(String name) {
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

    public HouseHoldMember phone(String phone) {
        this.phone = phone;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^\\+?[0-9]{1,3}?[-. (]*(?:\\d{1,3}[-. ]?){2,10}\\d{1,4}$")
    @JsonProperty(JSON_PROPERTY_PHONE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getPhone() {
        return phone;
    }

    @JsonProperty(JSON_PROPERTY_PHONE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HouseHoldMember device(List<@Valid MemberDevice> device) {
        this.device = device;
        return this;
    }

    public HouseHoldMember addDeviceItem(MemberDevice deviceItem) {
        if (this.device == null) {
            this.device = new ArrayList<>();
        }
        this.device.add(deviceItem);
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_DEVICE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<@Valid MemberDevice> getDevice() {
        return device;
    }

    @JsonProperty(JSON_PROPERTY_DEVICE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDevice(List<@Valid MemberDevice> device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HouseHoldMember houseHoldMember = (HouseHoldMember) o;
        return Objects.equals(this.name, houseHoldMember.name) && Objects.equals(this.phone, houseHoldMember.phone)
                && Objects.equals(this.device, houseHoldMember.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, device);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HouseHoldMember {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    device: ").append(toIndentedString(device)).append("\n");
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
