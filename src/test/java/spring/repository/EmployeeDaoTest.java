package spring.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import spring.SpringContextTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * see add-departments_text.sql
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
public class EmployeeDaoTest extends SpringContextTest {

    private static final String SEE_SQL = "See the add-employees_test.sql.";

    @Autowired
    private IEmployeeRepository repository;

    @Test
    public void getAllEmployees() {
        assertFalse("Shouldn't be empty" + SEE_SQL, repository.getAllEmployees().isEmpty());
        assertEquals("Should contains 3 entries" + SEE_SQL, 3, repository.getAllEmployees().size());
    }

    @Test
    public void checkContentIds() {
        assertEquals(SEE_SQL, 1, repository.getAllEmployees().get(0).getId());
        assertEquals(SEE_SQL, 2, repository.getAllEmployees().get(1).getId());
        assertEquals(SEE_SQL, 3, repository.getAllEmployees().get(2).getId());
    }

    @Test
    public void checkContentNames() {
        assertEquals(SEE_SQL, "a", repository.getAllEmployees().get(0).getName());
        assertEquals(SEE_SQL, "b", repository.getAllEmployees().get(1).getName());
        assertEquals(SEE_SQL, "c", repository.getAllEmployees().get(2).getName());
    }

    @Test
    public void checkContentDepartmentIds() {
        assertEquals(SEE_SQL, 1, repository.getAllEmployees().get(0).getDepartmentId());
        assertEquals(SEE_SQL, 2, repository.getAllEmployees().get(1).getDepartmentId());
        assertEquals(SEE_SQL, 1, repository.getAllEmployees().get(2).getDepartmentId());
    }

    @Test
    public void checkContentMails() {
        assertEquals(SEE_SQL, "a@a.a", repository.getAllEmployees().get(0).getMail());
        assertEquals(SEE_SQL, "b@b.b", repository.getAllEmployees().get(1).getMail());
        assertNull(SEE_SQL, repository.getAllEmployees().get(2).getMail());
    }

    @Test
    public void checkContentPhones() {
        assertEquals(SEE_SQL, "1(111)111-11-11", repository.getAllEmployees().get(0).getPhone());
        assertEquals(SEE_SQL, "2(222)222-22-22", repository.getAllEmployees().get(1).getPhone());
        assertNull(SEE_SQL, repository.getAllEmployees().get(2).getPhone());
    }

    @Test
    public void getEmployeesByDepartment() {
        assertTrue(repository.getEmployeesByDepartment(-1).isEmpty());
        assertEquals(2, repository.getEmployeesByDepartment(1).size());
        assertEquals(1, repository.getEmployeesByDepartment(2).size());
    }
}
