package spring.repository;

import java.util.List;

import javax.annotation.Nonnull;

import model.Employee;

/**
 * DAO for employees. Read-only.
 * @see Employee
 * @see spring.service.EmployeesDataService
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface IEmployeeRepository {
    /** @return The all employees list contained in the storage.  */
    @Nonnull
    List<Employee> getAllEmployees();
    /**
     * @param departmentId The unique id of the department.
     *
     * @return The employees list filtered by department.
     * */
    @Nonnull
    List<Employee> getEmployeesByDepartment(int departmentId);
}
