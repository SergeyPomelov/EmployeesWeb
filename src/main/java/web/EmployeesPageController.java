package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * There is the only one real page as document into entire app - index.jsp.
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@SuppressWarnings({"MethodReturnAlwaysConstant", "SameReturnValue"}) // by design
@Controller
public class EmployeesPageController {

    /** From here any new user will starts, sends the index.jsp for a root request. */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("favicon.ico")
    public String favicon() {
        return "forward:/resources/images/favicon.ico";
    }
}