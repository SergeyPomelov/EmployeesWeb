package spring.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * This class reads system.properties.xml, this can be expanded to a list of configuration files.
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@SuppressWarnings({"ClassIndependentOfModule", "ClassUnconnectedToPackage"})
@Component
final class SysPropsConfigLoader implements InitializingBean {

    // This method fails if any of properties files loading fails.
    @SuppressWarnings({"ProhibitedExceptionDeclared", "OverlyBroadThrowsClause"}) // as in Spring's interface
    @Override
    public void afterPropertiesSet() throws Exception {
        PropertiesReader.readPropertiesFromFiles(Collections.singletonList("system.properties.xml"));
    }
}
