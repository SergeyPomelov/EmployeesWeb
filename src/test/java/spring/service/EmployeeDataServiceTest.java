package spring.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import model.Employee;
import spring.service.paging.DataPageDto;
import spring.SpringContextTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static testutil.TestUtil.passedIfException;

/**
 * see add-departments_text.sql.
 * For test data see add-employees_test.sql.
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
public class EmployeeDataServiceTest extends SpringContextTest {

    // should correlate with the context data from db-hsqldb-config_test.xml
    private static final int totalEmployees = 3;
    private static final int dept1TotalEmployees = 2;
    private static final int dept2TotalEmployees = 1;
    private static final String SEE_DOCS = "See the IEmployeesDataService interface's docs and " +
            "agreements there.";

    @Autowired
    private IEmployeesDataService service;

    @Test
    public void getAllEmployeesPageableValid() {
        assertEquals(SEE_DOCS, totalEmployees, getDataPage(0, totalEmployees).getPageData().size());
        assertEquals(SEE_DOCS, 2, getDataPage(0, 2).getPageData().size());
        assertEquals(SEE_DOCS, totalEmployees, getDataPage(0, totalEmployees).getTotalAmount());
    }

    @Test
    public void getAllEmployeesPageableNegativeStart() {
        assertEquals(SEE_DOCS, totalEmployees, getDataPage(-1, totalEmployees).getPageData().size());
    }

    @Test
    public void getAllEmployeesPageableNullStart() {
        assertEquals(SEE_DOCS, totalEmployees, getDataPage(null, totalEmployees).getPageData().size());
    }

    @Test
    public void getAllEmployeesPageableNegativeLimit() {
        assertEquals(SEE_DOCS, totalEmployees, getDataPage(0, -3).getPageData().size());
    }

    @Test
    public void getAllEmployeesPageableNullLimit() {
        assertEquals(SEE_DOCS, totalEmployees, getDataPage(0, null).getPageData().size());
    }

    @Test
    public void getAllEmployeesPageableTest() {
        final DataPageDto<Employee> data = service.getAllEmployeesPageable(0, 2).get();
        assertNotNull(SEE_DOCS, data.getPageData().get(0));
        assertNotNull(SEE_DOCS, data.getPageData().get(1));
        assertEquals(SEE_DOCS, 3, data.getTotalAmount());
    }

    @Test
    public void getAllEmployeesPageableNoDataAfterPageTest() {
        final DataPageDto<Employee> data = service.getAllEmployeesPageable(0, 2).get();
        passedIfException(SEE_DOCS, () -> data.getPageData().get(2));
    }

    @Test
    public void getDepartmentsEmployeesPageableNegativeStart() {
        assertEquals(SEE_DOCS, dept1TotalEmployees, getDataPageByDepartment(-1, 3, 1).getPageData().size());
    }

    @Test
    public void getDepartmentsEmployeesPageableNullStart() {
        assertEquals(SEE_DOCS, dept1TotalEmployees, getDataPageByDepartment(null, 3, 1).getPageData().size());
    }

    @Test
    public void getDepartmentsEmployeesPageableNegativeLimit() {
        assertEquals(SEE_DOCS, dept1TotalEmployees, getDataPageByDepartment(0, -3, 1).getPageData().size());
    }

    @Test
    public void getDepartmentsEmployeesPageableNullLimit() {
        assertEquals(SEE_DOCS, dept1TotalEmployees, getDataPageByDepartment(0, null, 1).getPageData().size());
    }

    @Test
    public void getDepartmentsEmployeesPageableNegativeDepartmentId() {
        assertEquals(SEE_DOCS, 0, getDataPageByDepartment(0, 3, -1).getPageData().size());
    }

    @Test
    public void getDepartmentsEmployeesPageableNullDepartmentId() {
        assertEquals(SEE_DOCS, 0, getDataPageByDepartment(0, 3, null).getPageData().size());
    }

    @Test
    public void getDepartmentsEmployeesPageableTest() {
        final int departmentId = 2;
        final DataPageDto<Employee> data = getDataPageByDepartment(0, 1, departmentId);
        assertNotNull(data.getPageData().get(0));
        assertEquals(departmentId, data.getPageData().get(0).getDepartmentId());
        assertEquals(dept2TotalEmployees, data.getTotalAmount());
    }

    @Test
    public void getDepartmentsEmployeesPageableNoDataAfterPageTest() {
        final DataPageDto<Employee> data = service.getDepartmentsEmployeesPageable(0, 1, 1).get();
        passedIfException(() -> data.getPageData().get(1));
    }

    private DataPageDto<Employee> getDataPage(Integer start, Integer limit) {
        return service.getAllEmployeesPageable(start, limit).get();
    }

    private DataPageDto<Employee>
    getDataPageByDepartment(Integer start, Integer limit, Integer departmentId) {
        return service.getDepartmentsEmployeesPageable(start, limit, departmentId).get();
    }
}
