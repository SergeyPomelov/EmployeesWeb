package spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import model.Employee;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, timeout = 10)
public final class JdbcEmployeeRepository implements IEmployeeRepository {

    private static final String DEPARTMENT_ID_DB_FIELD = "departmentId";
    private final RowMapper<Employee> mapper = new EmployeeMapper();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Nonnull
    public List<Employee> getAllEmployees() {
        final String sql = "SELECT * FROM employees";
        return namedParameterJdbcTemplate.query(sql, Collections.emptyMap(), mapper);
    }

    @Override
    @Nonnull
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        final Map<String, Object> params = new HashMap<>(1, 1.0F);
        params.put(DEPARTMENT_ID_DB_FIELD, departmentId);
        final String sql = "SELECT * FROM employees WHERE departmentId=:departmentId";
        return namedParameterJdbcTemplate.query(sql, params, mapper);
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt(DEPARTMENT_ID_DB_FIELD),
                    rs.getString("mail"), rs.getString("phone"));
        }
    }
}
