package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.Employee;
import spring.service.IEmployeesDataService;
import spring.service.paging.DataPageDto;

/**
 * Despite the name it isn't acting like a real API just cutting all not clear requests.
 * In most cases of wrong parameters the app ignoring them and fallbacks to a default
 * behaviour defined in service, however, returning detailed logged errors list in JSON.
 * @see IEmployeesDataService
 *
 * @author Sergey Pomelov on 04/11/2016.
 */
@Controller
@ResponseBody
@RequestMapping(value = "api",
        method = RequestMethod.GET,
        produces = "application/json; charset=UTF-8")
public class EmployeesApiController {

    private final Logger logger = LoggerFactory.getLogger(EmployeesApiController.class);

    @Autowired
    private IEmployeesDataService employeesDataService;

    @RequestMapping("getEmployees")
    public ResponseEntity<Map<String, Object>> getEmployees(
            @RequestParam(value = "departmentId", required = false) Integer departmentId,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "limit", required = false) Integer limit) {

        logger.debug("/api/getEmployees params {departmentId={},start={},limit={}}",
                departmentId, start, limit);

        final Optional<DataPageDto<Employee>> employeesDataPage = (departmentId == null) ?
                employeesDataService.getAllEmployeesPageable(start, limit) :
                employeesDataService.getDepartmentsEmployeesPageable(start, limit, departmentId);

        return generateResponse(employeesDataPage, departmentId, start, limit);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private ResponseEntity<Map<String, Object>> generateResponse(
            Optional<DataPageDto<Employee>> employeesDataPage,
            Integer departmentId, Integer start, Integer limit) {

        if (employeesDataPage.isPresent()) {
            final Map<String, Object> response = new HashMap<>(4, 1.0F);
            response.put("success", true);
            response.put("total", employeesDataPage.get().getTotalAmount());
            response.put("employees", employeesDataPage.get().getPageData());
            final Optional<String> error = employeesDataPage.get().getErrorMessage();
            if (error.isPresent()) {
                response.put("error", error.get());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.ok(response);
        } else {
            logger.error("/api/getEmployees is called with params {departmentId={},start={},limit={}}, " +
                    " internal error!", departmentId, start, limit);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(LoadFailureResponse.get());
        }
    }
}
