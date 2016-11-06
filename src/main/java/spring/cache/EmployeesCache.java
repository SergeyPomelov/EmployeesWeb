package spring.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.concurrent.ThreadSafe;

import model.Employee;
import spring.repository.IEmployeeRepository;

/**
 * @author Sergey Pomelov on 05/11/2016.
 */
@ThreadSafe
@Repository
public final class EmployeesCache extends AbstractCache<Employee>  {

    private static final Logger logger = LoggerFactory.getLogger(EmployeesCache.class);

    @Autowired
    private IEmployeeRepository employeeRepo;

    public EmployeesCache() {
        super("Employees cache");
    }

    @Override
    protected Collection<Employee> getAllData() {
        try {
            return employeeRepo.getAllEmployees();
        } catch (RuntimeException e) {
            logger.warn("Employees list loading from data base failed.", e);
            return Collections.emptyList();
        }
    }
}
