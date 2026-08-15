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

@JsonPropertyOrder({HouseholdMember.JSON_PROPERTY_NAME, HouseholdMember.JSON_PROPERTY_PHONE,
        HouseholdMember.JSON_PROPERTY_DEVICES, HouseholdMember.JSON_PROPERTY_ACTIVE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class HouseholdMember {

    public static final String JSON_PROPERTY_NAME = "name";
    private String name;
    public static final String JSON_PROPERTY_PHONE = "phone";
    private String phone;
    public static final String JSON_PROPERTY_DEVICES = "devices";
    private List<@Valid MemberPhoneDetails> devices = new ArrayList<>();
    public static final String JSON_PROPERTY_ACTIVE = "active";
    private Boolean active = true;

    public HouseholdMember name(String name) {
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

    public HouseholdMember phone(String phone) {
        this.phone = phone;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{3}$")
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

    public HouseholdMember devices(List<@Valid MemberPhoneDetails> devices) {
        this.devices = devices;
        return this;
    }

    public HouseholdMember addDevicesItem(MemberPhoneDetails devicesItem) {
        if (this.devices == null) {
            this.devices = new ArrayList<>();
        }
        this.devices.add(devicesItem);
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_DEVICES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<@Valid MemberPhoneDetails> getDevices() {
        return devices;
    }

    @JsonProperty(JSON_PROPERTY_DEVICES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDevices(List<@Valid MemberPhoneDetails> devices) {
        this.devices = devices;
    }

    public HouseholdMember active(Boolean active) {
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
        HouseholdMember householdMember = (HouseholdMember) o;
        return Objects.equals(this.name, householdMember.name) && Objects.equals(this.phone, householdMember.phone)
                && Objects.equals(this.devices, householdMember.devices)
                && Objects.equals(this.active, householdMember.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, devices, active);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HouseholdMember {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    devices: ").append(toIndentedString(devices)).append("\n");
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
