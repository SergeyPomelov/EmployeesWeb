package spring.config;

import com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Utility class, it's reading and set system properties from files.
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@ParametersAreNonnullByDefault
final class PropertiesReader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);
    private static final String LOADING_PROPERTIES = "Loading sys properties from resource: {}";

    private PropertiesReader() { /* utility class */ }

    @SuppressWarnings("OverlyBroadThrowsClause")
    static void readPropertiesFromFiles(Iterable<String> names) throws IOException {
        for (final String name : names) {
            readPropertiesFromFile(name);
        }
    }

    private static void readPropertiesFromFile(@Nullable String name) throws IOException {
        InputStream is = null;
        try {
            if (name != null) {
                is = getResourceAsStream(name);
                if (is != null) {
                    loadAndSetPropsFromStream(is);
                } else {
                    throw new FileNotFoundException("No properties resource " + name);
                }
            }
        } finally {
            closeQuietly(is);
        }
    }

    @SuppressWarnings("OverlyBroadThrowsClause") // included in IOException
    private static void loadAndSetPropsFromStream(InputStream is) throws IOException {
        final Properties properties = new Properties();
        properties.loadFromXML(is);
        for (final Entry<Object, Object> entry : properties.entrySet()) {
            System.setProperty((String) entry.getKey(), (String) entry.getValue());
            log.debug("loaded property - {}: {}", entry.getKey(), entry.getValue());
        }
    }

    private static void closeQuietly(@Nullable Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            log.warn("Can't close the file.", e);
        }
    }

    @Nullable
    private static InputStream getResourceAsStream(String resourceName) throws IOException {
        return getResourceAsStreamInternal(resourceName);
    }

    @Nullable
    private static InputStream getResourceAsStreamInternal(String resourceName) throws IOException {
        final URL url = getResourceURL(resourceName);
        return (url == null) ? null : url.openStream();
    }

    @Nullable
    private static URL getResourceURL(String resourceName) {
        return getResourceURLInternal(resourceName);
    }

    @Nullable
    private static URL getResourceURLInternal(String resourceName) {
        String resourceNameLocal = resourceName;
        if ((!resourceNameLocal.isEmpty()) && (resourceNameLocal.charAt(0) == '/')) {
            resourceNameLocal = resourceNameLocal.substring(1);
        }
        if (Thread.currentThread().getContextClassLoader() != null) {
            return tryLoad(ImmutableList.of(resourceNameLocal, '/' + resourceNameLocal));
        }
        return null;
    }

    @Nullable
    private static URL tryLoad(Iterable<String> resourcesNames) {
        for (final String resource : resourcesNames) {
            final URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
            if (url != null) {
                log.info(LOADING_PROPERTIES, url);
                return url;
            }
        }
        return null;
    }
}
