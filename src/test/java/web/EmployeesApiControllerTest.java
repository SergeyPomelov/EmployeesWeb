package web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
@SuppressWarnings("ClassMayBeInterface") // no, it can't, spring config won't up
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-config_test.xml")
@WebAppConfiguration
public class EmployeesApiControllerTest {

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void invalidRequest() throws Exception {
        mockMvc.perform(get("/api/DEADBEEF"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void noArgsRequest() throws Exception {
        mockMvc.perform(get("/api/getEmployees"))
                .andExpect(status().is(400))
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.total", is(3)))
                .andExpect(jsonPath("$.employees[0].name", is("a")));
    }

    @Test
    public void noValidArgsRequest() throws Exception {
        mockMvc.perform(get("/api/getEmployees?start=-1&limit=2"))
                .andExpect(status().is(400))
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.total", is(3)))
                .andExpect(jsonPath("$.employees[0].name", is("a")));
    }

    @Test
    public void validNoDepartmentRequest() throws Exception {
        mockMvc.perform(get("/api/getEmployees?start=1&limit=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.total", is(3)))
                .andExpect(jsonPath("$.employees[0].name", is("b")))
                .andExpect(jsonPath("$.employees[1].name", is("c")));
    }

    @Test
    public void validDepartmentRequest() throws Exception {
        mockMvc.perform(get("/api/getEmployees?departmentId=1&start=1&limit=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.total", is(2)))
                .andExpect(jsonPath("$.employees[0].name", is("c")))
                .andExpect(jsonPath("$.employees[0].departmentId", is(1)));
    }
}
