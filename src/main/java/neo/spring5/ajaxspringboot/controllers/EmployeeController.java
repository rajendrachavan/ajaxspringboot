package neo.spring5.ajaxspringboot.controllers;

import neo.spring5.ajaxspringboot.models.Employee;
import neo.spring5.ajaxspringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee emp){
        return employeeService.save(emp);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.listAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") long id){
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long id,
                                                   @Valid @RequestBody Employee employeeDetails){
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        employee.setEmpname(employeeDetails.getEmpname());
        employee.setEmail(employeeDetails.getEmail());

        Employee updateEmp = employeeService.save(employee);
        return ResponseEntity.ok().body(updateEmp);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long id){
        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        employeeService.delete(id);

        return ResponseEntity.ok().build();
    }

   /* @RequestMapping(value = "/getdata", method = RequestMethod.GET)
    public ResponseEntity<Object> getData(){
        Employee employee1 = new Employee("rajendra", "rajendrachavan.jvm@gmail.com");
        return new ResponseEntity<>(employee1,HttpStatus.OK);
    }

    @RequestMapping(value = "/postdata", method = RequestMethod.POST)
    public ResponseEntity<Object> postData(@RequestBody Employee employee){
        System.out.println("Employee: "+employee);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }*/
}
