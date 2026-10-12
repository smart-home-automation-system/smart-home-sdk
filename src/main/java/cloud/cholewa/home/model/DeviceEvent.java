package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({DeviceEvent.JSON_PROPERTY_AT, DeviceEvent.JSON_PROPERTY_VENDOR, DeviceEvent.JSON_PROPERTY_GATEWAY,
        DeviceEvent.JSON_PROPERTY_POINT, DeviceEvent.JSON_PROPERTY_EVENT, DeviceEvent.JSON_PROPERTY_VALUE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class DeviceEvent {

    public static final String JSON_PROPERTY_AT = "at";
    private LocalDateTime at;
    public static final String JSON_PROPERTY_VENDOR = "vendor";
    private SmartVendor vendor;
    public static final String JSON_PROPERTY_GATEWAY = "gateway";
    private EatonGatewayType gateway;
    public static final String JSON_PROPERTY_POINT = "point";
    private Integer point;
    public static final String JSON_PROPERTY_EVENT = "event";
    private EatonRxEvent event;
    public static final String JSON_PROPERTY_VALUE = "value";
    private Integer value;

    public DeviceEvent at(LocalDateTime at) {
        this.at = at;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_AT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public LocalDateTime getAt() {
        return at;
    }

    @JsonProperty(JSON_PROPERTY_AT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    public DeviceEvent vendor(SmartVendor vendor) {
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

    public DeviceEvent gateway(EatonGatewayType gateway) {
        this.gateway = gateway;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public EatonGatewayType getGateway() {
        return gateway;
    }

    @JsonProperty(JSON_PROPERTY_GATEWAY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setGateway(EatonGatewayType gateway) {
        this.gateway = gateway;
    }

    public DeviceEvent point(Integer point) {
        this.point = point;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(1)
    @Max(99)
    @JsonProperty(JSON_PROPERTY_POINT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getPoint() {
        return point;
    }

    @JsonProperty(JSON_PROPERTY_POINT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setPoint(Integer point) {
        this.point = point;
    }

    public DeviceEvent event(EatonRxEvent event) {
        this.event = event;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_EVENT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public EatonRxEvent getEvent() {
        return event;
    }

    @JsonProperty(JSON_PROPERTY_EVENT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setEvent(EatonRxEvent event) {
        this.event = event;
    }

    public DeviceEvent value(Integer value) {
        this.value = value;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getValue() {
        return value;
    }

    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceEvent deviceEvent = (DeviceEvent) o;
        return Objects.equals(this.at, deviceEvent.at) && Objects.equals(this.vendor, deviceEvent.vendor)
                && Objects.equals(this.gateway, deviceEvent.gateway) && Objects.equals(this.point, deviceEvent.point)
                && Objects.equals(this.event, deviceEvent.event) && Objects.equals(this.value, deviceEvent.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(at, vendor, gateway, point, event, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceEvent {\n");
        sb.append("    at: ").append(toIndentedString(at)).append("\n");
        sb.append("    vendor: ").append(toIndentedString(vendor)).append("\n");
        sb.append("    gateway: ").append(toIndentedString(gateway)).append("\n");
        sb.append("    point: ").append(toIndentedString(point)).append("\n");
        sb.append("    event: ").append(toIndentedString(event)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
