package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({CalendarDay.JSON_PROPERTY_DATE, CalendarDay.JSON_PROPERTY_TYPE, CalendarDay.JSON_PROPERTY_REASON})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class CalendarDay {

    public static final String JSON_PROPERTY_DATE = "date";
    private LocalDate date;
    public static final String JSON_PROPERTY_TYPE = "type";
    private DayType type;
    public static final String JSON_PROPERTY_REASON = "reason";
    private String reason;

    public CalendarDay date(LocalDate date) {
        this.date = date;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_DATE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public LocalDate getDate() {
        return date;
    }

    @JsonProperty(JSON_PROPERTY_DATE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CalendarDay type(DayType type) {
        this.type = type;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public DayType getType() {
        return type;
    }

    @JsonProperty(JSON_PROPERTY_TYPE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setType(DayType type) {
        this.type = type;
    }

    public CalendarDay reason(String reason) {
        this.reason = reason;
        return this;
    }

    @Nullable
    @Size(max = 100)
    @JsonProperty(JSON_PROPERTY_REASON)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getReason() {
        return reason;
    }

    @JsonProperty(JSON_PROPERTY_REASON)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CalendarDay calendarDay = (CalendarDay) o;
        return Objects.equals(this.date, calendarDay.date) && Objects.equals(this.type, calendarDay.type)
                && Objects.equals(this.reason, calendarDay.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, type, reason);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CalendarDay {\n");
        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
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
