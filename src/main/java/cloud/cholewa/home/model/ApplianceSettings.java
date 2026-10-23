package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ApplianceSettings.JSON_PROPERTY_RUNNING_ABOVE_WATTS,
        ApplianceSettings.JSON_PROPERTY_IDLE_BELOW_WATTS, ApplianceSettings.JSON_PROPERTY_MIN_RUN_MINUTES,
        ApplianceSettings.JSON_PROPERTY_FINISHED_AFTER_MINUTES, ApplianceSettings.JSON_PROPERTY_REMINDER_EVERY_MINUTES,
        ApplianceSettings.JSON_PROPERTY_MAX_REMINDERS, ApplianceSettings.JSON_PROPERTY_QUIET_FROM,
        ApplianceSettings.JSON_PROPERTY_QUIET_TO})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ApplianceSettings {

    public static final String JSON_PROPERTY_RUNNING_ABOVE_WATTS = "runningAboveWatts";
    private Integer runningAboveWatts;
    public static final String JSON_PROPERTY_IDLE_BELOW_WATTS = "idleBelowWatts";
    private Integer idleBelowWatts;
    public static final String JSON_PROPERTY_MIN_RUN_MINUTES = "minRunMinutes";
    private Integer minRunMinutes;
    public static final String JSON_PROPERTY_FINISHED_AFTER_MINUTES = "finishedAfterMinutes";
    private Integer finishedAfterMinutes;
    public static final String JSON_PROPERTY_REMINDER_EVERY_MINUTES = "reminderEveryMinutes";
    private Integer reminderEveryMinutes;
    public static final String JSON_PROPERTY_MAX_REMINDERS = "maxReminders";
    private Integer maxReminders;
    public static final String JSON_PROPERTY_QUIET_FROM = "quietFrom";
    private String quietFrom;
    public static final String JSON_PROPERTY_QUIET_TO = "quietTo";
    private String quietTo;

    public ApplianceSettings runningAboveWatts(Integer runningAboveWatts) {
        this.runningAboveWatts = runningAboveWatts;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(5)
    @Max(3000)
    @JsonProperty(JSON_PROPERTY_RUNNING_ABOVE_WATTS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getRunningAboveWatts() {
        return runningAboveWatts;
    }

    @JsonProperty(JSON_PROPERTY_RUNNING_ABOVE_WATTS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setRunningAboveWatts(Integer runningAboveWatts) {
        this.runningAboveWatts = runningAboveWatts;
    }

    public ApplianceSettings idleBelowWatts(Integer idleBelowWatts) {
        this.idleBelowWatts = idleBelowWatts;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(0)
    @Max(100)
    @JsonProperty(JSON_PROPERTY_IDLE_BELOW_WATTS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getIdleBelowWatts() {
        return idleBelowWatts;
    }

    @JsonProperty(JSON_PROPERTY_IDLE_BELOW_WATTS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setIdleBelowWatts(Integer idleBelowWatts) {
        this.idleBelowWatts = idleBelowWatts;
    }

    public ApplianceSettings minRunMinutes(Integer minRunMinutes) {
        this.minRunMinutes = minRunMinutes;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(1)
    @Max(180)
    @JsonProperty(JSON_PROPERTY_MIN_RUN_MINUTES)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getMinRunMinutes() {
        return minRunMinutes;
    }

    @JsonProperty(JSON_PROPERTY_MIN_RUN_MINUTES)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setMinRunMinutes(Integer minRunMinutes) {
        this.minRunMinutes = minRunMinutes;
    }

    public ApplianceSettings finishedAfterMinutes(Integer finishedAfterMinutes) {
        this.finishedAfterMinutes = finishedAfterMinutes;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(1)
    @Max(60)
    @JsonProperty(JSON_PROPERTY_FINISHED_AFTER_MINUTES)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getFinishedAfterMinutes() {
        return finishedAfterMinutes;
    }

    @JsonProperty(JSON_PROPERTY_FINISHED_AFTER_MINUTES)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setFinishedAfterMinutes(Integer finishedAfterMinutes) {
        this.finishedAfterMinutes = finishedAfterMinutes;
    }

    public ApplianceSettings reminderEveryMinutes(Integer reminderEveryMinutes) {
        this.reminderEveryMinutes = reminderEveryMinutes;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(5)
    @Max(180)
    @JsonProperty(JSON_PROPERTY_REMINDER_EVERY_MINUTES)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getReminderEveryMinutes() {
        return reminderEveryMinutes;
    }

    @JsonProperty(JSON_PROPERTY_REMINDER_EVERY_MINUTES)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setReminderEveryMinutes(Integer reminderEveryMinutes) {
        this.reminderEveryMinutes = reminderEveryMinutes;
    }

    public ApplianceSettings maxReminders(Integer maxReminders) {
        this.maxReminders = maxReminders;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(0)
    @Max(10)
    @JsonProperty(JSON_PROPERTY_MAX_REMINDERS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getMaxReminders() {
        return maxReminders;
    }

    @JsonProperty(JSON_PROPERTY_MAX_REMINDERS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setMaxReminders(Integer maxReminders) {
        this.maxReminders = maxReminders;
    }

    public ApplianceSettings quietFrom(String quietFrom) {
        this.quietFrom = quietFrom;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$")
    @JsonProperty(JSON_PROPERTY_QUIET_FROM)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getQuietFrom() {
        return quietFrom;
    }

    @JsonProperty(JSON_PROPERTY_QUIET_FROM)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setQuietFrom(String quietFrom) {
        this.quietFrom = quietFrom;
    }

    public ApplianceSettings quietTo(String quietTo) {
        this.quietTo = quietTo;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$")
    @JsonProperty(JSON_PROPERTY_QUIET_TO)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getQuietTo() {
        return quietTo;
    }

    @JsonProperty(JSON_PROPERTY_QUIET_TO)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setQuietTo(String quietTo) {
        this.quietTo = quietTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplianceSettings applianceSettings = (ApplianceSettings) o;
        return Objects.equals(this.runningAboveWatts, applianceSettings.runningAboveWatts)
                && Objects.equals(this.idleBelowWatts, applianceSettings.idleBelowWatts)
                && Objects.equals(this.minRunMinutes, applianceSettings.minRunMinutes)
                && Objects.equals(this.finishedAfterMinutes, applianceSettings.finishedAfterMinutes)
                && Objects.equals(this.reminderEveryMinutes, applianceSettings.reminderEveryMinutes)
                && Objects.equals(this.maxReminders, applianceSettings.maxReminders)
                && Objects.equals(this.quietFrom, applianceSettings.quietFrom)
                && Objects.equals(this.quietTo, applianceSettings.quietTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runningAboveWatts, idleBelowWatts, minRunMinutes, finishedAfterMinutes,
                reminderEveryMinutes, maxReminders, quietFrom, quietTo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApplianceSettings {\n");
        sb.append("    runningAboveWatts: ").append(toIndentedString(runningAboveWatts)).append("\n");
        sb.append("    idleBelowWatts: ").append(toIndentedString(idleBelowWatts)).append("\n");
        sb.append("    minRunMinutes: ").append(toIndentedString(minRunMinutes)).append("\n");
        sb.append("    finishedAfterMinutes: ").append(toIndentedString(finishedAfterMinutes)).append("\n");
        sb.append("    reminderEveryMinutes: ").append(toIndentedString(reminderEveryMinutes)).append("\n");
        sb.append("    maxReminders: ").append(toIndentedString(maxReminders)).append("\n");
        sb.append("    quietFrom: ").append(toIndentedString(quietFrom)).append("\n");
        sb.append("    quietTo: ").append(toIndentedString(quietTo)).append("\n");
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
