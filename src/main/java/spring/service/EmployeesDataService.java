package spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import model.Department;
import model.Employee;
import spring.cache.ICache;
import spring.service.paging.DataPageBuilder;
import spring.service.paging.DataPageDto;

/**
 * This implementation is never returning  the{@code Optional.empty()}, an empty collections sending instead.
 * Also, it can limit the asked by the client amount o data, see {@link #entriesInResponseLimit}.
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@Service
public final class EmployeesDataService implements IEmployeesDataService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeesDataService.class);

    // Decrease the not encoded requested data size (and other parameters) vulnerability.
    private static final int entriesInResponseLimit = 100;

    // Because this data is readonly the caches loaded and will not be dropped before the app stop.
    @Autowired
    private ICache<Employee> employeesCache;
    @Autowired
    private ICache<Department> departmentsCache;

    @Override
    @Nonnull
    public Optional<DataPageDto<Employee>> getAllEmployeesPageable(Integer start, Integer limit) {
        return DataPageBuilder.wrapDataInPage(employeesCache.getAll(), start, limit,
                entriesInResponseLimit, new StringBuilder(""));
    }

    @Override
    @Nonnull
    public Optional<DataPageDto<Employee>>
    getDepartmentsEmployeesPageable(Integer start,Integer limit, Integer departmentId) {
        final StringBuilder errorLog = new StringBuilder("");
        return DataPageBuilder.wrapDataInPage(getDepartmentEmployees(departmentId, errorLog),start, limit,
                entriesInResponseLimit, errorLog);
    }

    @Nonnull
    private Collection<Employee> getDepartmentEmployees(Integer departmentId,
                                                        @Nonnull StringBuilder errorLog) {
        if ((departmentId == null) || (departmentId < 0)) {
            errorLog.append("Department id ( ").append(departmentId).append(" ) should be defined and >0.");
            logger.warn("Department id ({}) should be defined and >0.", departmentId);
            return Collections.emptyList();
        } else if (!isDepartmentExists(departmentId)) {
            errorLog.append("Department with id (").append(departmentId).append(") not found.");
            logger.warn("Department with id ({}) not found.", departmentId);
            return Collections.emptyList();
        }
        return employeesCache.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    private boolean isDepartmentExists(Integer departmentId) {
        for (final Department department : departmentsCache.getAll()) {
            if (department.getId() == departmentId) {
                return true;
            }
        }
        return false;
    }
}