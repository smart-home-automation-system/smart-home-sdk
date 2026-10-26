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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({HouseholdNotification.JSON_PROPERTY_EVENT, HouseholdNotification.JSON_PROPERTY_SUBJECT,
        HouseholdNotification.JSON_PROPERTY_DEVICE_ID, HouseholdNotification.JSON_PROPERTY_OCCURRED_AT,
        HouseholdNotification.JSON_PROPERTY_REMINDER, HouseholdNotification.JSON_PROPERTY_ACKNOWLEDGE_PATH,
        HouseholdNotification.JSON_PROPERTY_MEMBER})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class HouseholdNotification {

    public static final String JSON_PROPERTY_EVENT = "event";
    private HouseholdEvent event;
    public static final String JSON_PROPERTY_SUBJECT = "subject";
    private String subject;
    public static final String JSON_PROPERTY_DEVICE_ID = "deviceId";
    private Long deviceId;
    public static final String JSON_PROPERTY_OCCURRED_AT = "occurredAt";
    private LocalDateTime occurredAt;
    public static final String JSON_PROPERTY_REMINDER = "reminder";
    private Integer reminder;
    public static final String JSON_PROPERTY_ACKNOWLEDGE_PATH = "acknowledgePath";
    private String acknowledgePath;
    public static final String JSON_PROPERTY_MEMBER = "member";
    private String member;

    public HouseholdNotification event(HouseholdEvent event) {
        this.event = event;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_EVENT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public HouseholdEvent getEvent() {
        return event;
    }

    @JsonProperty(JSON_PROPERTY_EVENT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setEvent(HouseholdEvent event) {
        this.event = event;
    }

    public HouseholdNotification subject(String subject) {
        this.subject = subject;
        return this;
    }

    @Nonnull
    @NotNull
    @Size(min = 3, max = 50)
    @JsonProperty(JSON_PROPERTY_SUBJECT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getSubject() {
        return subject;
    }

    @JsonProperty(JSON_PROPERTY_SUBJECT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public HouseholdNotification deviceId(Long deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(1L)
    @JsonProperty(JSON_PROPERTY_DEVICE_ID)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Long getDeviceId() {
        return deviceId;
    }

    @JsonProperty(JSON_PROPERTY_DEVICE_ID)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public HouseholdNotification occurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
        return this;
    }

    @Nonnull
    @NotNull
    @Valid
    @JsonProperty(JSON_PROPERTY_OCCURRED_AT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    @JsonProperty(JSON_PROPERTY_OCCURRED_AT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    public HouseholdNotification reminder(Integer reminder) {
        this.reminder = reminder;
        return this;
    }

    @Nonnull
    @NotNull
    @Min(0)
    @Max(10)
    @JsonProperty(JSON_PROPERTY_REMINDER)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public Integer getReminder() {
        return reminder;
    }

    @JsonProperty(JSON_PROPERTY_REMINDER)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setReminder(Integer reminder) {
        this.reminder = reminder;
    }

    public HouseholdNotification acknowledgePath(String acknowledgePath) {
        this.acknowledgePath = acknowledgePath;
        return this;
    }

    @Nullable
    @Pattern(regexp = "^(/[A-Za-z0-9_-]+)+$")
    @Size(max = 200)
    @JsonProperty(JSON_PROPERTY_ACKNOWLEDGE_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getAcknowledgePath() {
        return acknowledgePath;
    }

    @JsonProperty(JSON_PROPERTY_ACKNOWLEDGE_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setAcknowledgePath(String acknowledgePath) {
        this.acknowledgePath = acknowledgePath;
    }

    public HouseholdNotification member(String member) {
        this.member = member;
        return this;
    }

    @Nullable
    @Size(min = 3, max = 50)
    @JsonProperty(JSON_PROPERTY_MEMBER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getMember() {
        return member;
    }

    @JsonProperty(JSON_PROPERTY_MEMBER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HouseholdNotification householdNotification = (HouseholdNotification) o;
        return Objects.equals(this.event, householdNotification.event)
                && Objects.equals(this.subject, householdNotification.subject)
                && Objects.equals(this.deviceId, householdNotification.deviceId)
                && Objects.equals(this.occurredAt, householdNotification.occurredAt)
                && Objects.equals(this.reminder, householdNotification.reminder)
                && Objects.equals(this.acknowledgePath, householdNotification.acknowledgePath)
                && Objects.equals(this.member, householdNotification.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, subject, deviceId, occurredAt, reminder, acknowledgePath, member);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HouseholdNotification {\n");
        sb.append("    event: ").append(toIndentedString(event)).append("\n");
        sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
        sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
        sb.append("    occurredAt: ").append(toIndentedString(occurredAt)).append("\n");
        sb.append("    reminder: ").append(toIndentedString(reminder)).append("\n");
        sb.append("    acknowledgePath: ").append(toIndentedString(acknowledgePath)).append("\n");
        sb.append("    member: ").append(toIndentedString(member)).append("\n");
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
