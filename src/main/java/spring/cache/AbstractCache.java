package spring.cache;

import com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import static util.Restrictions.ifNullFail;

/**
 * Abstract loaded on a context start and not reloadable cache for read-only data.
 *
 * @author Sergey Pomelov on 05/11/2016.
 */
@ThreadSafe
@Repository
public abstract class AbstractCache<T> implements ICache<T>, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCache.class);

    @Nonnull
    private final String name;
    private Collection<T> entitiesCache = null;

    AbstractCache(String name) {
        this.name = ifNullFail(name);
    }

    @Override
    @Nonnull
    public Collection<T> getAll() {
        check();
        return entitiesCache;
    }

    @SuppressWarnings({"ProhibitedExceptionDeclared", "OverlyBroadThrowsClause"}) // as in Spring's interface
    @Override
    public void afterPropertiesSet() throws Exception {
        if (entitiesCache == null) {
            entitiesCache = ImmutableList.copyOf(getAllData());
        }
        check();
    }

    private void check() {
        if (entitiesCache == null) {
            logger.error("{} not loaded!", name);
            throw new IllegalStateException(name + " cache not loaded!");
        } else {
            logger.info("{} loaded, {} entities.", name, entitiesCache.size());
        }
    }

    protected abstract Collection<? extends T> getAllData();
}
