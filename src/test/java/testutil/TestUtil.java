package testutil;

import javax.annotation.Nonnull;

import static org.junit.Assert.assertTrue;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
public final class TestUtil {

    private TestUtil() { /* tests' utility class */ }

    public static void passedIfException(@Nonnull Runnable runnable) {
        passedIfException("Expected an exception there.", runnable);
    }

    public static void passedIfException(@Nonnull String errorMessage, @Nonnull Runnable runnable) {
        boolean thrown = false;
        try {
            runnable.run();
        } catch (RuntimeException ignored) {
            thrown = true;
        }
        assertTrue(errorMessage, thrown);
    }
}
