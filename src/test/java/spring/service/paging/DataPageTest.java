package spring.service.paging;

import com.google.common.collect.ImmutableList;

import org.junit.Test;

import java.util.List;

import model.Department;
import model.Employee;

import static org.junit.Assert.assertEquals;
import static testutil.TestUtil.passedIfException;

/**
 * see add-departments_text.sql
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
public class DataPageTest {

    private static final int total = 3;
    private static final List<Employee> employees = ImmutableList.<Employee>builder()
            .add(new Employee(1, "a", 1, null, null))
            .add(new Employee(2, "b", 2, null, null))
            .build();

    private static final DataPageDto<Employee> dataPage = new DataPageDto<>(employees, total,"");

    @Test
    public void negativeTotalData() {
        new DataPageDto<>(employees, -1, "");
    }

    @Test
    public void nullName() {
        passedIfException(() -> new Department(1, null));
    }

    @Test
    public void getPageData() {
        assertEquals("should return exactly the same data", employees, dataPage.getPageData());
    }

    @Test
    public void getTotalAmount() {
        assertEquals("should return exactly the set total amount", total, dataPage.getTotalAmount());
    }
}
