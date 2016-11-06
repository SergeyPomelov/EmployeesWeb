package web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sergey Pomelov on 04/11/2016.
 */
public class EmployeesPageControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        // standalone here is brittle, if this bothering you, change to a contextual test.
        mockMvc = MockMvcBuilders.standaloneSetup(new EmployeesPageController())
                .setViewResolvers(viewResolver())
                .build();
    }

    private static ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Test
    public void smokeTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/index.jsp"));
    }

    @Test
    public void the404Test() throws Exception {
        mockMvc.perform(get("/DEADBEEF")).andExpect(status().isNotFound());
    }

    @Test
    public void iconTest() throws Exception {
        mockMvc.perform(get("/favicon.ico"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/resources/images/favicon.ico"));
    }
}
