package spring.service.paging;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import spring.service.EmployeesDataService;

/**
 * The data page. Isn't supposed to check consistency, this is services' job.
 *
 * @param <T> The contained data type parameter.
 * @see EmployeesDataService
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class DataPageDto<T> {

    @Nonnull
    private final List<T> pageData;
    @Nonnegative
    private final int totalAmount;
    @Nullable
    private final String errorMessage;

    /**
     * @param pageData     The page data, fails if it's {@literal null}.
     * @param totalAmount  The total entries tally in the whole data set.
     * @param errorMessage Optional adding if something went wrong.
     */
    DataPageDto(Collection<T> pageData, @Nonnegative int totalAmount,
                @Nullable String errorMessage) {
        this.pageData = ImmutableList.copyOf(pageData);
        this.totalAmount = totalAmount;
        this.errorMessage = errorMessage;
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField") // link to an immutable collection is even better
    @Nonnull
    public List<T> getPageData() {
        return pageData;
    }

    @Nonnegative
    public int getTotalAmount() {
        return totalAmount;
    }

    @Nonnull
    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(errorMessage);
    }
}
