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
import java.util.List;

import javax.annotation.Nonnull;

import model.Department;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, timeout = 10)
public final class JdbcDepartmentRepository implements IDepartmentRepository {

    private final RowMapper<Department> mapper = new DepartmentMapper();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Nonnull
    @Override
    public List<Department> getAllDepartments() {
        final String sql = "SELECT * FROM departments";
        return namedParameterJdbcTemplate.query(sql, Collections.emptyMap(), mapper);
    }

    private static final class DepartmentMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Department(rs.getInt("id"), rs.getString("name"));
        }
    }
}
