package web;

import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.Map;

/**
 * Class only for using withing EmployeesApiController. Designed for reduce memory polluting and overhead,
 * just keeps an returns a mandatory pre-defined constant.
 * @see EmployeesApiController
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
final class LoadFailureResponse {

    private static final Map<String, Object> response = ImmutableMap.<String, Object>builder()
            .put("success", false)
            .put("total", 0)
            .put("employees", Collections.emptyList())
            .put("error", "Internal backend API error. See EmployeesApiController's logs.")
            .build();

    private LoadFailureResponse() { /* one constant construction handler */ }

    /**
     * @return must return the same pre-defined constant.
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    static Map<String, Object> get() {
        return response;
    }
}
