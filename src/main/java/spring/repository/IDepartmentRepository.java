package spring.repository;

import java.util.List;

import javax.annotation.Nonnull;

import model.Department;

/**
 * Repository for departments. Read-only, this is mandatory.
 *
 * @author Sergey Pomelov on 04/11/2016.
 * @see Department
 */
@SuppressWarnings({"InterfaceMayBeAnnotatedFunctional", "InterfaceWithOnlyOneDirectInheritor"})
public interface IDepartmentRepository {
    /** @return The all departments list contained in the storage.  */
    @Nonnull
    List<Department> getAllDepartments();
}
