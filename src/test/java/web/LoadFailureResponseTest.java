package web;

import org.junit.Test;

import java.util.Collections;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static testutil.TestUtil.passedIfException;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
public class LoadFailureResponseTest {

    @Test
    public void failResponseCheck() throws Exception {
        assertEquals(false, LoadFailureResponse.get().get("success"));
        assertEquals(0, LoadFailureResponse.get().get("total"));
        assertEquals(Collections.emptyList(), LoadFailureResponse.get().get("employees"));
        assertSame("Must return a pre-defined constant.",
                LoadFailureResponse.get(), LoadFailureResponse.get());
    }

    @Test
    public void failResponseCheckImmutable() throws Exception {
        passedIfException(() -> LoadFailureResponse.get().put("", ""));
    }
}
