package spring.shedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Sergey Pomelov on 18/04/2016.
 */
@SuppressWarnings("ClassIndependentOfModule")
@Service
class UtilScheduler {

    private static final Logger log = LoggerFactory.getLogger(UtilScheduler.class);

    @Scheduled(fixedDelay = 120000) // makes me sure that the hotswap wasn't messed up
    private static void showDeployedCodeVersion() {
        log.debug("scheduler tick: code version 11.6.1b");
    }
}
