package spring.repository;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This class able to start a DB GUI manager.
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@Component
class DbGuiLoader {

    /** Set "DB_gui" true in system.properties.xml if you want on a DB GUI. Better see the {@link sql}. */
    @PostConstruct
    private static void startDBManager() {
        //noinspection AccessOfSystemProperties, this is ok in this case
        if (Boolean.getBoolean("DB_gui")) {
            DatabaseManagerSwing.main(new String[]{"--url", "jdbc:hsqldb:mem:testdb",
                    "--user", "sa", "--password", ""});
        }
    }
}
