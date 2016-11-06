package spring.service;

import java.util.Optional;

import javax.annotation.Nonnull;

import model.Employee;
import spring.service.paging.DataPageDto;

/**
 * The interface Employees data service.
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface IEmployeesDataService {
    /**
     * @param start The number of the first entry, positive not {@literal null}.
     * @param limit The limit of entries, positive not {@literal null}.
     * @return the desired part of the data or if start or limit are wrong - all data.
     */
    @Nonnull
    Optional<DataPageDto<Employee>> getAllEmployeesPageable(Integer start, Integer limit);

    /**
     * @param start        The number of the first entry, positive not {@literal null}.
     * @param limit        The limit of entries, positive not {@literal null}.
     * @param departmentId The department id, positive not {@literal null}, existed Id.
     * @return the desired part of the data or if start or limit are wrong - all data. If the departmentId
     * wrong, returns empty list and log warning.
     */
    @Nonnull
    Optional<DataPageDto<Employee>> getDepartmentsEmployeesPageable(Integer start, Integer limit,
                                                                    Integer departmentId);
}
