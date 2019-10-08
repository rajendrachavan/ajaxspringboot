package neo.spring5.ajaxspringboot.controllers;

import neo.spring5.springbootAjax.models.Employee;
import neo.spring5.springbootAjax.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    private EmployeeService employeeService;

    public IndexController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping({"/"})
    public String indexPage(Model model){
        List<Employee> employeeList = employeeService.listAllEmployees();
        model.addAttribute("employeelist", employeeList);

        return "index";
    }

    @RequestMapping({"/registration","/registration.html"})
    public String registrationPage(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);

        return "registration";
    }
}