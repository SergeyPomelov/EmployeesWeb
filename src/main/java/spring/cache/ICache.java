package spring.cache;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * @author Sergey Pomelov on 05/11/2016.
 */
public interface ICache<T> {

    @Nonnull
    Collection<T> getAll();
}
