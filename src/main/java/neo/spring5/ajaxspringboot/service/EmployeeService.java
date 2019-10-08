package neo.spring5.ajaxspringboot.service;

import neo.spring5.ajaxspringboot.models.Employee;
import neo.spring5.ajaxspringboot.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> listAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).get();
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
