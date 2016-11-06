package spring.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import spring.SpringContextTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * see add-departments_text.sql
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
public class DepartmentDaoTest extends SpringContextTest {

    private static final String SEE_SQL = "See the add-departments_test.sql.";

    @Autowired
    private IDepartmentRepository repository;

    @Test
    public void getAllDepartments() {
        assertFalse("Shouldn't be empty" + SEE_SQL, repository.getAllDepartments().isEmpty());
        assertEquals("Should contains 2 entries" + SEE_SQL, 2, repository.getAllDepartments().size());
    }

    @Test
    public void contentCheck() {
        assertEquals(SEE_SQL, 1, repository.getAllDepartments().get(0).getId());
        assertEquals(SEE_SQL, 2, repository.getAllDepartments().get(1).getId());
        assertEquals(SEE_SQL, "A", repository.getAllDepartments().get(0).getName());
        assertEquals(SEE_SQL, "B", repository.getAllDepartments().get(1).getName());
    }
}