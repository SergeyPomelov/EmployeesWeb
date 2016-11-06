package spring.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.concurrent.ThreadSafe;

import model.Department;
import spring.repository.IDepartmentRepository;

/**
 * @author Sergey Pomelov on 05/11/2016.
 */
@ThreadSafe
@Repository
public final class DepartmentsCache extends AbstractCache<Department> {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentsCache.class);

    @Autowired
    private IDepartmentRepository departmentRepo;

    public DepartmentsCache() {
        super("Departments cache");
    }

    @Override
    protected Collection<Department> getAllData() {
        try {
            return departmentRepo.getAllDepartments();
        } catch (RuntimeException e) {
            logger.warn("Departments list loading from data base failed.", e);
            return Collections.emptyList();
        }
    }
}
