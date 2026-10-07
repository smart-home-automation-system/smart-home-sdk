package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({HouseholdProfile.JSON_PROPERTY_NAME, HouseholdProfile.JSON_PROPERTY_ROLE,
        HouseholdProfile.JSON_PROPERTY_ROOMS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class HouseholdProfile {

    public static final String JSON_PROPERTY_NAME = "name";
    private String name;
    public static final String JSON_PROPERTY_ROLE = "role";
    private MemberRole role;
    public static final String JSON_PROPERTY_ROOMS = "rooms";
    private List<RoomName> rooms = new ArrayList<>();

    public HouseholdProfile name(String name) {
        this.name = name;
        return this;
    }

    @Nonnull
    @NotNull
    @Size(min = 3, max = 50)
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

    public HouseholdProfile role(MemberRole role) {
        this.role = role;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_ROLE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public MemberRole getRole() {
        return role;
    }

    @JsonProperty(JSON_PROPERTY_ROLE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setRole(MemberRole role) {
        this.role = role;
    }

    public HouseholdProfile rooms(List<RoomName> rooms) {
        this.rooms = rooms;
        return this;
    }

    public HouseholdProfile addRoomsItem(RoomName roomsItem) {
        if (this.rooms == null) {
            this.rooms = new ArrayList<>();
        }
        this.rooms.add(roomsItem);
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_ROOMS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<RoomName> getRooms() {
        return rooms;
    }

    @JsonProperty(JSON_PROPERTY_ROOMS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRooms(List<RoomName> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HouseholdProfile householdProfile = (HouseholdProfile) o;
        return Objects.equals(this.name, householdProfile.name) && Objects.equals(this.role, householdProfile.role)
                && Objects.equals(this.rooms, householdProfile.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, rooms);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HouseholdProfile {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    role: ").append(toIndentedString(role)).append("\n");
        sb.append("    rooms: ").append(toIndentedString(rooms)).append("\n");
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
