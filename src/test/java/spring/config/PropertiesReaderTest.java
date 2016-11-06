package spring.config;

import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
public class PropertiesReaderTest {

    // IDE's test auto-configurated run may mess this test to fail (wrong build schema),
    // use gradle test or specify build dirs and process in IDE manually
    @Test
    public void readPropertiesFromFiles() {
        try {
            PropertiesReader.readPropertiesFromFiles(Collections.singletonList("system.properties.xml"));
        } catch (IOException ignored) {
            fail("Exception thrown while system.properties.xml loading, this file must be in the build.");
        }
        assertEquals("unit_tests", System.getProperty("profile"));
    }

    @Test
    public void readPropertiesFromWrongFiles() {
        try {
            PropertiesReader.readPropertiesFromFiles(Collections.singletonList("DEADBEEF"));
        } catch (IOException ignore) {
            // test passed
        }
    }
}
