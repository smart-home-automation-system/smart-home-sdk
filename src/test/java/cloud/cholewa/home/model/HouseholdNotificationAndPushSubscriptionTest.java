package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * The payload of the household category of notification-service and the push subscription of
 * a member's browser (HAS-271). Generated from {@code swagger/household-notification.yaml} and
 * {@code household.yaml}; these tests pin the JSON names, the types, what is mandatory and the
 * bounds.
 */
class HouseholdNotificationAndPushSubscriptionTest {

    @Test
    void shouldPublishTheseJsonPropertiesOfANotificationAndNoOther() {
        assertEquals(
            Set.of("event", "subject", "deviceId", "occurredAt", "reminder", "acknowledgePath"),
            JsonProperties.of(HouseholdNotification.class)
        );
    }

    // The payload says what happened, never how to word it: notification-service composes the
    // texts from the event, so there is no field for one.
    @Test
    void shouldRequireEverythingOfANotificationButThePathAndCarryNoText() throws NoSuchMethodException {
        for (String getter : new String[] {"getEvent", "getSubject", "getDeviceId", "getOccurredAt", "getReminder"}) {
            assertNotNull(HouseholdNotification.class.getMethod(getter).getAnnotation(NotNull.class), getter);
        }
        assertNull(HouseholdNotification.class.getMethod("getAcknowledgePath").getAnnotation(NotNull.class));
        assertFalse(JsonProperties.of(HouseholdNotification.class).contains("text"));
        assertFalse(JsonProperties.of(HouseholdNotification.class).contains("message"));
    }

    // The same clock of the house as the other messages of the broker; the subject is a device
    // name of the registry and the reminder never goes past maxReminders.
    @Test
    void shouldTypeAndBoundANotification() throws NoSuchMethodException {
        assertEquals(HouseholdEvent.class, HouseholdNotification.class.getMethod("getEvent").getReturnType());
        assertEquals(LocalDateTime.class, HouseholdNotification.class.getMethod("getOccurredAt").getReturnType());
        assertEquals(Long.class, HouseholdNotification.class.getMethod("getDeviceId").getReturnType());
        assertEquals(1L, HouseholdNotification.class.getMethod("getDeviceId").getAnnotation(Min.class).value());

        Size subject = HouseholdNotification.class.getMethod("getSubject").getAnnotation(Size.class);
        Size name = SmartDevice.class.getMethod("getName").getAnnotation(Size.class);
        assertEquals(name.min(), subject.min());
        assertEquals(name.max(), subject.max());

        assertEquals(0, HouseholdNotification.class.getMethod("getReminder").getAnnotation(Min.class).value());
        assertEquals(
            ApplianceSettings.class.getMethod("getMaxReminders").getAnnotation(Max.class).value(),
            HouseholdNotification.class.getMethod("getReminder").getAnnotation(Max.class).value()
        );
    }

    // The path becomes a link in a message: a path of the application only, never a host, a
    // protocol-relative "//" or a query that could send the household elsewhere.
    @Test
    void shouldTakeAPathOfTheApplicationOnly() throws NoSuchMethodException {
        String pattern = HouseholdNotification.class.getMethod("getAcknowledgePath").getAnnotation(Pattern.class)
            .regexp();

        assertTrue("/room".matches(pattern));
        assertTrue("/u/anna/room".matches(pattern));
        assertTrue("/lights/rooms/hall_up".matches(pattern));
        assertFalse("/".matches(pattern));
        assertFalse("//evil.example".matches(pattern));
        assertFalse("https://evil.example/room".matches(pattern));
        assertFalse("room".matches(pattern));
        assertFalse("/room?next=x".matches(pattern));
        assertFalse("/room/".matches(pattern));
        assertFalse("/../room".matches(pattern));
    }

    @Test
    void shouldPublishTheseJsonPropertiesOfASubscriptionAndNoOther() {
        assertEquals(Set.of("endpoint", "keys", "userAgent", "createdAt"), JsonProperties.of(PushSubscription.class));
        assertEquals(Set.of("p256dh", "auth"), JsonProperties.of(PushSubscriptionKeys.class));
    }

    // A model of its own (owner, 2026-10-23): a whole write of a member must not drop the
    // subscriptions, and no read of the household may hand the keys out.
    @Test
    void shouldKeepTheSubscriptionsOffTheMemberAndTheProfile() {
        assertTrue(JsonProperties.of(HouseholdMember.class).stream().noneMatch(name -> name.startsWith("push")));
        assertTrue(JsonProperties.of(HouseholdProfile.class).stream().noneMatch(name -> name.startsWith("push")));
    }

    // The endpoint is text: as a java.net.URI it could carry neither @Pattern nor @Size, and a
    // validated body would fail with UnexpectedTypeException instead of a 400.
    @Test
    void shouldRequireAnHttpsEndpointAsBoundedText() throws NoSuchMethodException {
        var endpoint = PushSubscription.class.getMethod("getEndpoint");
        assertEquals(String.class, endpoint.getReturnType());
        assertNotNull(endpoint.getAnnotation(NotNull.class));
        assertEquals(1, endpoint.getAnnotation(Size.class).min());
        assertEquals(2000, endpoint.getAnnotation(Size.class).max());

        String pattern = endpoint.getAnnotation(Pattern.class).regexp();
        assertTrue("https://push.example.com/send/abc123".matches(pattern));
        assertFalse("http://push.example.com/send/abc123".matches(pattern));
        assertFalse("https://".matches(pattern));
        assertFalse("https://push.example.com/a b".matches(pattern));
        assertFalse("ftp://push.example.com".matches(pattern));
    }

    @Test
    void shouldRequireTheKeysAndValidateThemWithTheSubscription() throws NoSuchMethodException {
        assertNotNull(PushSubscription.class.getMethod("getKeys").getAnnotation(NotNull.class));
        assertNotNull(PushSubscription.class.getMethod("getKeys").getAnnotation(Valid.class));
        assertNull(PushSubscription.class.getMethod("getUserAgent").getAnnotation(NotNull.class));
        assertNull(PushSubscription.class.getMethod("getCreatedAt").getAnnotation(NotNull.class));
        assertEquals(500, PushSubscription.class.getMethod("getUserAgent").getAnnotation(Size.class).max());
        assertEquals(LocalDateTime.class, PushSubscription.class.getMethod("getCreatedAt").getReturnType());
    }

    // base64url as a browser gives it: a P-256 point of 65 bytes is 87 characters (88 padded),
    // a secret of 16 bytes 22 (24 padded).
    @Test
    void shouldTakeTheKeysOfABrowserInBase64Url() throws NoSuchMethodException {
        var p256dh = PushSubscriptionKeys.class.getMethod("getP256dh");
        var auth = PushSubscriptionKeys.class.getMethod("getAuth");
        assertNotNull(p256dh.getAnnotation(NotNull.class));
        assertNotNull(auth.getAnnotation(NotNull.class));
        assertEquals(87, p256dh.getAnnotation(Size.class).min());
        assertEquals(88, p256dh.getAnnotation(Size.class).max());
        assertEquals(22, auth.getAnnotation(Size.class).min());
        assertEquals(24, auth.getAnnotation(Size.class).max());

        String key = "B" + "A1b2_-".repeat(14) + "x";
        assertEquals(86, key.length());
        String keyPattern = p256dh.getAnnotation(Pattern.class).regexp();
        assertTrue((key + "Q").matches(keyPattern));
        assertTrue((key + "Q=").matches(keyPattern));
        //the length alone would let 86 characters and a "=" through: no P-256 point
        assertFalse((key + "=").matches(keyPattern));
        assertFalse((key + "+").matches(keyPattern));
        assertFalse((key + "/").matches(keyPattern));

        String authPattern = auth.getAnnotation(Pattern.class).regexp();
        assertTrue("abcdefghijklmnopqrstuv".matches(authPattern));
        assertTrue("abcdefghijklmnopqrstuv==".matches(authPattern));
        assertFalse("abcdefghijklmnopqrstu=".matches(authPattern));
        assertFalse("abcdefghijklmnopqrstuv=".matches(authPattern));
        assertFalse("abcdefghijklmnopqrstu==".matches(authPattern));
        assertFalse("abcdefghijklmnopqrst+v".matches(authPattern));
    }
}
