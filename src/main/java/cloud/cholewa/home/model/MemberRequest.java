package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({MemberRequest.JSON_PROPERTY_ID, MemberRequest.JSON_PROPERTY_MEMBER,
        MemberRequest.JSON_PROPERTY_TEXT, MemberRequest.JSON_PROPERTY_AUTHOR, MemberRequest.JSON_PROPERTY_CREATED_AT,
        MemberRequest.JSON_PROPERTY_SENT_AT, MemberRequest.JSON_PROPERTY_DONE_AT, MemberRequest.JSON_PROPERTY_DONE_BY,
        MemberRequest.JSON_PROPERTY_REMINDED_AT, MemberRequest.JSON_PROPERTY_DONE_ANNOUNCED_AT,
        MemberRequest.JSON_PROPERTY_STALE_ANNOUNCED_AT})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class MemberRequest {

    public static final String JSON_PROPERTY_ID = "id";
    private Long id;
    public static final String JSON_PROPERTY_MEMBER = "member";
    private String member;
    public static final String JSON_PROPERTY_TEXT = "text";
    private String text;
    public static final String JSON_PROPERTY_AUTHOR = "author";
    private String author;
    public static final String JSON_PROPERTY_CREATED_AT = "createdAt";
    private LocalDateTime createdAt;
    public static final String JSON_PROPERTY_SENT_AT = "sentAt";
    private LocalDateTime sentAt;
    public static final String JSON_PROPERTY_DONE_AT = "doneAt";
    private LocalDateTime doneAt;
    public static final String JSON_PROPERTY_DONE_BY = "doneBy";
    private String doneBy;
    public static final String JSON_PROPERTY_REMINDED_AT = "remindedAt";
    private LocalDateTime remindedAt;
    public static final String JSON_PROPERTY_DONE_ANNOUNCED_AT = "doneAnnouncedAt";
    private LocalDateTime doneAnnouncedAt;
    public static final String JSON_PROPERTY_STALE_ANNOUNCED_AT = "staleAnnouncedAt";
    private LocalDateTime staleAnnouncedAt;

    @JsonCreator
    public MemberRequest(@JsonProperty(JSON_PROPERTY_ID) Long id) {
        this();
        this.id = id;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getId() {
        return id;
    }

    public MemberRequest member(String member) {
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

    public MemberRequest text(String text) {
        this.text = text;
        return this;
    }

    @Nonnull
    @NotNull
    @Pattern(regexp = "^(?=.*\\S)[^\\p{Cc}\\p{Zl}\\p{Zp}]+$")
    @Size(min = 1, max = 100)
    @JsonProperty(JSON_PROPERTY_TEXT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getText() {
        return text;
    }

    @JsonProperty(JSON_PROPERTY_TEXT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setText(String text) {
        this.text = text;
    }

    public MemberRequest author(String author) {
        this.author = author;
        return this;
    }

    @Nonnull
    @NotNull
    @Size(min = 3, max = 50)
    @JsonProperty(JSON_PROPERTY_AUTHOR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public String getAuthor() {
        return author;
    }

    @JsonProperty(JSON_PROPERTY_AUTHOR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setAuthor(String author) {
        this.author = author;
    }

    public MemberRequest createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_CREATED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonProperty(JSON_PROPERTY_CREATED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MemberRequest sentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_SENT_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LocalDateTime getSentAt() {
        return sentAt;
    }

    @JsonProperty(JSON_PROPERTY_SENT_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public MemberRequest doneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_DONE_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    @JsonProperty(JSON_PROPERTY_DONE_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }

    public MemberRequest doneBy(String doneBy) {
        this.doneBy = doneBy;
        return this;
    }

    @Nullable
    @Size(min = 3, max = 50)
    @JsonProperty(JSON_PROPERTY_DONE_BY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getDoneBy() {
        return doneBy;
    }

    @JsonProperty(JSON_PROPERTY_DONE_BY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public MemberRequest remindedAt(LocalDateTime remindedAt) {
        this.remindedAt = remindedAt;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_REMINDED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LocalDateTime getRemindedAt() {
        return remindedAt;
    }

    @JsonProperty(JSON_PROPERTY_REMINDED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRemindedAt(LocalDateTime remindedAt) {
        this.remindedAt = remindedAt;
    }

    public MemberRequest doneAnnouncedAt(LocalDateTime doneAnnouncedAt) {
        this.doneAnnouncedAt = doneAnnouncedAt;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_DONE_ANNOUNCED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LocalDateTime getDoneAnnouncedAt() {
        return doneAnnouncedAt;
    }

    @JsonProperty(JSON_PROPERTY_DONE_ANNOUNCED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDoneAnnouncedAt(LocalDateTime doneAnnouncedAt) {
        this.doneAnnouncedAt = doneAnnouncedAt;
    }

    public MemberRequest staleAnnouncedAt(LocalDateTime staleAnnouncedAt) {
        this.staleAnnouncedAt = staleAnnouncedAt;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_STALE_ANNOUNCED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LocalDateTime getStaleAnnouncedAt() {
        return staleAnnouncedAt;
    }

    @JsonProperty(JSON_PROPERTY_STALE_ANNOUNCED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setStaleAnnouncedAt(LocalDateTime staleAnnouncedAt) {
        this.staleAnnouncedAt = staleAnnouncedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberRequest memberRequest = (MemberRequest) o;
        return Objects.equals(this.id, memberRequest.id) && Objects.equals(this.member, memberRequest.member)
                && Objects.equals(this.text, memberRequest.text) && Objects.equals(this.author, memberRequest.author)
                && Objects.equals(this.createdAt, memberRequest.createdAt)
                && Objects.equals(this.sentAt, memberRequest.sentAt)
                && Objects.equals(this.doneAt, memberRequest.doneAt)
                && Objects.equals(this.doneBy, memberRequest.doneBy)
                && Objects.equals(this.remindedAt, memberRequest.remindedAt)
                && Objects.equals(this.doneAnnouncedAt, memberRequest.doneAnnouncedAt)
                && Objects.equals(this.staleAnnouncedAt, memberRequest.staleAnnouncedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, member, text, author, createdAt, sentAt, doneAt, doneBy, remindedAt, doneAnnouncedAt,
                staleAnnouncedAt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MemberRequest {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    member: ").append(toIndentedString(member)).append("\n");
        sb.append("    text: ").append(toIndentedString(text)).append("\n");
        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        sb.append("    sentAt: ").append(toIndentedString(sentAt)).append("\n");
        sb.append("    doneAt: ").append(toIndentedString(doneAt)).append("\n");
        sb.append("    doneBy: ").append(toIndentedString(doneBy)).append("\n");
        sb.append("    remindedAt: ").append(toIndentedString(remindedAt)).append("\n");
        sb.append("    doneAnnouncedAt: ").append(toIndentedString(doneAnnouncedAt)).append("\n");
        sb.append("    staleAnnouncedAt: ").append(toIndentedString(staleAnnouncedAt)).append("\n");
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
