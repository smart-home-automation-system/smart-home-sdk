package cloud.cholewa.home.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * An errand of one household member for another (HAS-408, epic HAS-407). Generated from
 * {@code swagger/household.yaml}; these tests pin the JSON names, what a client must send, the
 * bounds of the text and the names, and that a request is never a field of a member.
 */
class MemberRequestTest {

    @Test
    void shouldPublishTheseJsonPropertiesAndNoOther() {
        assertEquals(Set.of("id", "member", "text", "author", "createdAt", "sentAt", "doneAt", "doneBy"),
            JsonProperties.of(MemberRequest.class));
    }

    // A model of its own: a whole write of a member must not drop the requests, and the read of
    // the registry by notification-service stays as it is.
    @Test
    void shouldKeepTheRequestsOffTheMemberAndTheProfile() {
        assertTrue(JsonProperties.of(HouseholdMember.class).stream().noneMatch(name -> name.startsWith("request")));
        assertTrue(JsonProperties.of(HouseholdProfile.class).stream().noneMatch(name -> name.startsWith("request")));
    }

    // A client sends the text and its author; everything else is the server's.
    @Test
    void shouldRequireTheTextAndTheAuthorOnly() throws NoSuchMethodException {
        assertNotNull(MemberRequest.class.getMethod("getText").getAnnotation(NotNull.class));
        assertNotNull(MemberRequest.class.getMethod("getAuthor").getAnnotation(NotNull.class));
        for (String getter : new String[] {"getId", "getMember", "getCreatedAt", "getSentAt", "getDoneAt", "getDoneBy"}) {
            assertNull(MemberRequest.class.getMethod(getter).getAnnotation(NotNull.class), getter);
        }
    }

    // The id is the server's: read only, given by the constructor alone.
    @Test
    void shouldGiveTheIdByTheConstructorOnly() throws NoSuchMethodException {
        assertEquals(Long.class, MemberRequest.class.getMethod("getId").getReturnType());
        assertThrows(NoSuchMethodException.class, () -> MemberRequest.class.getMethod("setId", Long.class));
        assertEquals(42L, new MemberRequest(42L).getId());
    }

    @Test
    void shouldTypeTheTimesAsHouseTimes() throws NoSuchMethodException {
        for (String getter : new String[] {"getCreatedAt", "getSentAt", "getDoneAt"}) {
            assertEquals(LocalDateTime.class, MemberRequest.class.getMethod(getter).getReturnType(), getter);
        }
    }

    // The names of the registry: 3-50, as HouseholdMember.name.
    @Test
    void shouldBoundTheNamesAsTheRegistry() throws NoSuchMethodException {
        for (String getter : new String[] {"getMember", "getAuthor", "getDoneBy"}) {
            Size size = MemberRequest.class.getMethod(getter).getAnnotation(Size.class);
            assertEquals(3, size.min(), getter);
            assertEquals(50, size.max(), getter);
        }
    }

    @Test
    void shouldBoundTheTextToOneHundredCharacters() throws NoSuchMethodException {
        Size size = MemberRequest.class.getMethod("getText").getAnnotation(Size.class);
        assertEquals(1, size.min());
        assertEquals(100, size.max());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Buy bread and milk", "Kup chleb i mleko", "x", "Odbierz paczkę z paczkomatu (kod 1234)", " leading space"})
    void shouldTakeOneLineOfText(String text) {
        assertTrue(text.matches(pattern()), text);
    }

    // One line per request in the SMS: no line break, tab or other control character, and not
    // only spaces.
    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "Buy\nbread", "Buy\rbread", "Buy\tbread", "Buy\u0000bread", "Buy\u0085bread",
        "Buy bread", "Buy bread", "Buy\u007Fbread"})
    void shouldRefuseAnythingButOneLineOfText(String text) {
        assertFalse(text.matches(pattern()), text.replaceAll("\\p{Cc}", "?"));
    }

    private static String pattern() {
        try {
            return MemberRequest.class.getMethod("getText").getAnnotation(Pattern.class).regexp();
        } catch (NoSuchMethodException missing) {
            throw new IllegalStateException(missing);
        }
    }
}
