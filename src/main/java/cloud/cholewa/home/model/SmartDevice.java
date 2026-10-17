package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({SmartDevice.JSON_PROPERTY_ID, SmartDevice.JSON_PROPERTY_NAME, SmartDevice.JSON_PROPERTY_ROOM,
        SmartDevice.JSON_PROPERTY_KIND, SmartDevice.JSON_PROPERTY_ENABLED, SmartDevice.JSON_PROPERTY_VENDOR,
        SmartDevice.JSON_PROPERTY_SHELLY, SmartDevice.JSON_PROPERTY_EATON, SmartDevice.JSON_PROPERTY_BTHOME,
        SmartDevice.JSON_PROPERTY_LIGHT_KIND, SmartDevice.JSON_PROPERTY_PLACEMENT,
        SmartDevice.JSON_PROPERTY_POSITIONING, SmartDevice.JSON_PROPERTY_OPENING_SENSORS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class SmartDevice {

    public static final String JSON_PROPERTY_ID = "id";
    private Long id;
    public static final String JSON_PROPERTY_NAME = "name";
    private String name;
    public static final String JSON_PROPERTY_ROOM = "room";
    private RoomName room;
    public static final String JSON_PROPERTY_KIND = "kind";
    private DeviceKind kind;
    public static final String JSON_PROPERTY_ENABLED = "enabled";
    private Boolean enabled;
    public static final String JSON_PROPERTY_VENDOR = "vendor";
    private SmartVendor vendor;
    public static final String JSON_PROPERTY_SHELLY = "shelly";
    private ShellyBinding shelly;
    public static final String JSON_PROPERTY_EATON = "eaton";
    private EatonBinding eaton;
    public static final String JSON_PROPERTY_BTHOME = "bthome";
    private BthomeBinding bthome;
    public static final String JSON_PROPERTY_LIGHT_KIND = "lightKind";
    private LightKind lightKind;
    public static final String JSON_PROPERTY_PLACEMENT = "placement";
    private BlindsPlacement placement;
    public static final String JSON_PROPERTY_POSITIONING = "positioning";
    private Boolean positioning;
    public static final String JSON_PROPERTY_OPENING_SENSORS = "openingSensors";
    private List<@Min(1L) Long> openingSensors = new ArrayList<>();

    @JsonCreator
    public SmartDevice(@JsonProperty(JSON_PROPERTY_ID) Long id) {
        this();
        this.id = id;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getId() {
        return id;
    }

    public SmartDevice name(String name) {
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

    public SmartDevice room(RoomName room) {
        this.room = room;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_ROOM)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public RoomName getRoom() {
        return room;
    }

    @JsonProperty(JSON_PROPERTY_ROOM)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setRoom(RoomName room) {
        this.room = room;
    }

    public SmartDevice kind(DeviceKind kind) {
        this.kind = kind;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_KIND)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public DeviceKind getKind() {
        return kind;
    }

    @JsonProperty(JSON_PROPERTY_KIND)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setKind(DeviceKind kind) {
        this.kind = kind;
    }

    public SmartDevice enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Nonnull
    @NotNull
    @JsonProperty(JSON_PROPERTY_ENABLED)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Boolean getEnabled() {
        return enabled;
    }

    @JsonProperty(JSON_PROPERTY_ENABLED)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public SmartDevice vendor(SmartVendor vendor) {
        this.vendor = vendor;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_VENDOR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public SmartVendor getVendor() {
        return vendor;
    }

    @JsonProperty(JSON_PROPERTY_VENDOR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setVendor(SmartVendor vendor) {
        this.vendor = vendor;
    }

    public SmartDevice shelly(ShellyBinding shelly) {
        this.shelly = shelly;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_SHELLY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public ShellyBinding getShelly() {
        return shelly;
    }

    @JsonProperty(JSON_PROPERTY_SHELLY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setShelly(ShellyBinding shelly) {
        this.shelly = shelly;
    }

    public SmartDevice eaton(EatonBinding eaton) {
        this.eaton = eaton;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_EATON)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public EatonBinding getEaton() {
        return eaton;
    }

    @JsonProperty(JSON_PROPERTY_EATON)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEaton(EatonBinding eaton) {
        this.eaton = eaton;
    }

    public SmartDevice bthome(BthomeBinding bthome) {
        this.bthome = bthome;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_BTHOME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public BthomeBinding getBthome() {
        return bthome;
    }

    @JsonProperty(JSON_PROPERTY_BTHOME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBthome(BthomeBinding bthome) {
        this.bthome = bthome;
    }

    public SmartDevice lightKind(LightKind lightKind) {
        this.lightKind = lightKind;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_LIGHT_KIND)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LightKind getLightKind() {
        return lightKind;
    }

    @JsonProperty(JSON_PROPERTY_LIGHT_KIND)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLightKind(LightKind lightKind) {
        this.lightKind = lightKind;
    }

    public SmartDevice placement(BlindsPlacement placement) {
        this.placement = placement;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_PLACEMENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public BlindsPlacement getPlacement() {
        return placement;
    }

    @JsonProperty(JSON_PROPERTY_PLACEMENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPlacement(BlindsPlacement placement) {
        this.placement = placement;
    }

    public SmartDevice positioning(Boolean positioning) {
        this.positioning = positioning;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_POSITIONING)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getPositioning() {
        return positioning;
    }

    @JsonProperty(JSON_PROPERTY_POSITIONING)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPositioning(Boolean positioning) {
        this.positioning = positioning;
    }

    public SmartDevice openingSensors(List<@Min(1L) Long> openingSensors) {
        this.openingSensors = openingSensors;
        return this;
    }

    public SmartDevice addOpeningSensorsItem(Long openingSensorsItem) {
        if (this.openingSensors == null) {
            this.openingSensors = new ArrayList<>();
        }
        this.openingSensors.add(openingSensorsItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_OPENING_SENSORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<@Min(1L) Long> getOpeningSensors() {
        return openingSensors;
    }

    @JsonProperty(JSON_PROPERTY_OPENING_SENSORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOpeningSensors(List<@Min(1L) Long> openingSensors) {
        this.openingSensors = openingSensors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmartDevice smartDevice = (SmartDevice) o;
        return Objects.equals(this.id, smartDevice.id) && Objects.equals(this.name, smartDevice.name)
                && Objects.equals(this.room, smartDevice.room) && Objects.equals(this.kind, smartDevice.kind)
                && Objects.equals(this.enabled, smartDevice.enabled) && Objects.equals(this.vendor, smartDevice.vendor)
                && Objects.equals(this.shelly, smartDevice.shelly) && Objects.equals(this.eaton, smartDevice.eaton)
                && Objects.equals(this.bthome, smartDevice.bthome)
                && Objects.equals(this.lightKind, smartDevice.lightKind)
                && Objects.equals(this.placement, smartDevice.placement)
                && Objects.equals(this.positioning, smartDevice.positioning)
                && Objects.equals(this.openingSensors, smartDevice.openingSensors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, room, kind, enabled, vendor, shelly, eaton, bthome, lightKind, placement,
                positioning, openingSensors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SmartDevice {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    room: ").append(toIndentedString(room)).append("\n");
        sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
        sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
        sb.append("    vendor: ").append(toIndentedString(vendor)).append("\n");
        sb.append("    shelly: ").append(toIndentedString(shelly)).append("\n");
        sb.append("    eaton: ").append(toIndentedString(eaton)).append("\n");
        sb.append("    bthome: ").append(toIndentedString(bthome)).append("\n");
        sb.append("    lightKind: ").append(toIndentedString(lightKind)).append("\n");
        sb.append("    placement: ").append(toIndentedString(placement)).append("\n");
        sb.append("    positioning: ").append(toIndentedString(positioning)).append("\n");
        sb.append("    openingSensors: ").append(toIndentedString(openingSensors)).append("\n");
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
