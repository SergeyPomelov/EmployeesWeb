package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static testutil.TestUtil.passedIfException;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
public class DepartmentTest {

    private static final int id = 1;
    private static final String name = "A";
    private static final Department department = new Department(id, name);

    @Test
    public void negativeId() {
        passedIfException(() -> new Department(-1, "A"));
    }

    @Test
    public void nullName() {
        passedIfException(() -> new Department(1, null));
    }

    @Test
    public void getId() {
        assertEquals("should return exactly the set id", id, department.getId());
    }

    @Test
    public void getName() {
        assertEquals("should return exactly the set name", name, department.getName());
    }

    @Test
    public void testToString() {
        assertNotNull("toString() shouldn't fail or return null", department.toString());
    }

    @Test
    public void testEqual() {
        final Department otherDepartment = new Department(id, "B");
        assertEquals("equal id = equal entities", department, otherDepartment);
        assertEquals(otherDepartment, otherDepartment); // standard equals agreement
    }

    @Test
    public void testNotEqual() {
        final Department otherDepartment = new Department(id + 1, name);
        assertNotEquals("not equal id = not equal entities", department, otherDepartment);
        assertNotEquals(null, otherDepartment); // standard equals agreement
        assertNotEquals(1, otherDepartment); // standard equals agreement
    }
}
