package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({TemperatureMessage.JSON_PROPERTY_DATE, TemperatureMessage.JSON_PROPERTY_ROOM,
        TemperatureMessage.JSON_PROPERTY_TEMPERATURE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class TemperatureMessage {

    public static final String JSON_PROPERTY_DATE = "date";
    private LocalDateTime date;
    public static final String JSON_PROPERTY_ROOM = "room";
    private RoomName room;
    public static final String JSON_PROPERTY_TEMPERATURE = "temperature";
    private Double temperature;

    public TemperatureMessage date(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_DATE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LocalDateTime getDate() {
        return date;
    }

    @JsonProperty(JSON_PROPERTY_DATE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TemperatureMessage room(RoomName room) {
        this.room = room;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_ROOM)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public RoomName getRoom() {
        return room;
    }

    @JsonProperty(JSON_PROPERTY_ROOM)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRoom(RoomName room) {
        this.room = room;
    }

    public TemperatureMessage temperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TEMPERATURE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getTemperature() {
        return temperature;
    }

    @JsonProperty(JSON_PROPERTY_TEMPERATURE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemperatureMessage temperatureMessage = (TemperatureMessage) o;
        return Objects.equals(this.date, temperatureMessage.date) && Objects.equals(this.room, temperatureMessage.room)
                && Objects.equals(this.temperature, temperatureMessage.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, room, temperature);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemperatureMessage {\n");
        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("    room: ").append(toIndentedString(room)).append("\n");
        sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
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
