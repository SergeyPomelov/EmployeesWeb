package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static testutil.TestUtil.passedIfException;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
public class EmployeeTest {

    private static final int id = 1;
    private static final String name = "A Aa";
    private static final int departmentId = 1;
    private static final String mail = "AAa@a.a";
    private static final String phone = "7(923)1231212";
    private static final Employee employee = new Employee(id, name, departmentId, mail, phone);

    @Test
    public void validConstructorParams() {
        assertNotNull(new Employee(id, name, departmentId, mail, phone));
        assertNotNull(new Employee(id, name, departmentId, null, phone));
        assertNotNull(new Employee(id, name, departmentId, mail, null));
        assertNotNull(new Employee(id, name, departmentId, null, null));
    }

    @Test
    public void negativeId() {
        passedIfException(() -> new Employee(-1, name, departmentId, null, null));
    }

    @Test
    public void nullName() {
        passedIfException(() -> new Employee(id, null, departmentId, null, null));
    }

    @Test
    public void negativeDepartmentId() {
        passedIfException(() -> new Employee(1, name, -1, null, null));
    }

    @Test
    public void getId() {
        assertEquals("should return exactly the set id", id, employee.getId());
    }

    @Test
    public void getName() {
        assertEquals("should return exactly the set name", name, employee.getName());
    }

    @Test
    public void getDepartmentId() {
        assertEquals("should return exactly the set department id", departmentId,
                employee.getDepartmentId());
    }

    @Test
    public void getMail() {
        assertEquals("should return exactly the set mail", mail, employee.getMail());
    }

    @Test
    public void getPhone() {
        assertEquals("should return exactly the set phone", phone, employee.getPhone());
    }

    @Test
    public void testToString() {
        assertNotNull("The toString() shouldn't fail or return null", employee.toString());
    }

    @Test
    public void testEqual() {
        final Employee otherEmployee = new Employee(id, name + 'B', departmentId + 1, mail, phone);
        assertEquals("equal id = equal entities", employee, otherEmployee);
        assertEquals("standard equals agreement fails", employee, employee);
    }

    @Test
    public void testNotEqual() {
        final Employee otherEmployee = new Employee(id + 1, name, departmentId, null, null);
        assertNotEquals("not equal id = not equal entities", employee,otherEmployee);
        assertNotEquals("standard equals agreement fails", null, employee);
        assertNotEquals("standard equals agreement fails", "string", employee);
    }
}
