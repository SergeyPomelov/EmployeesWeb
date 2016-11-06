package spring.service.paging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Cut data sets into data pages and wrap them in DTOs.
 *
 * @param <T> The contained data type parameter.
 * @author Sergey Pomelov on 04/11/2016.
 */
public final class DataPageBuilder<T> {

    private static final Logger logger = LoggerFactory.getLogger(DataPageBuilder.class);

    private DataPageBuilder() { /* utility class */ }

    /**
     * Cut data sets into data pages and wrap them in DTOs.
     *
     * @param <T>                    The data type parameter.
     * @param data                   The whole data.
     * @param start                  The start (pointer to, cursor to) of the data page.
     * @param limit                  The limit, how many entries from start are needed.
     * @param entriesInResponseLimit The entries in response limit, you can't state the limit more that this.
     * @param errorLog               The errors log, a reference to an error collector.
     * @return the optional with DTO or {@link Optional#empty()} if a fatal error occurs.
     */
    public static <T> Optional<DataPageDto<T>> wrapDataInPage(
            Collection<T> data, Integer start, Integer limit, @Nonnegative Integer entriesInResponseLimit,
            @Nonnull StringBuilder errorLog) {

        return checkPagingParams(start, limit, entriesInResponseLimit, errorLog) ?
                intoOptionalDataPage(data, start, limit, errorLog) :
                intoOptionalDataPage(data, 0, entriesInResponseLimit, errorLog);
    }

    private static boolean checkPagingParams(Integer start, Integer limit,
                                             @Nonnegative Integer entriesInResponseLimit,
                                             @Nonnull StringBuilder errorLog) {

        if ((start == null) || (start < 0) || (limit == null) || (limit <= 0)
                || (limit > entriesInResponseLimit)) {
            registerParamsError(start, limit, errorLog);
            return false;
        }
        return true;
    }

    @Nonnull
    private static <T> Optional<DataPageDto<T>> intoOptionalDataPage(
            Collection<T> data, Integer start, Integer limit, @Nonnull CharSequence errorLog) {

        final String errorMessage = errorLog.toString().isEmpty() ? null : errorLog.toString();
        return Optional.of(new DataPageDto<>(getPage(data, start, limit), data.size(), errorMessage));
    }

    @Nonnull
    private static <T> List<T> getPage(Collection<T> data, Integer start, Integer limit) {
        return data.stream().skip(start).limit(limit).collect(Collectors.toList());
    }

    private static void registerParamsError(Integer start, Integer limit, StringBuilder errorLog) {
        errorLog.append("Wrong paging parameters {start= ").append(start).append(",limit=").append(limit)
                .append("}, were ").append("ignored. ");
        logger.warn("Wrong paging parameters {start={},limit={}}, were ignored, must be not null and " +
                "not negative, also thr limit must not exceed the service's restriction.", start, limit);
    }
}
