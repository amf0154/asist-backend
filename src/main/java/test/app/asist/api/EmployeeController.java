package test.app.asist.api;
import org.springframework.web.bind.annotation.*;
import test.app.asist.modal.Employee;
import test.app.asist.service.EmployeeService;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/employee")
@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee){
        this.employeeService.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployee(){
        return this.employeeService.getAllEmployee();
    }
    @GetMapping(path = "{id}")
    public Employee getEmployeeById(@PathVariable("id") UUID id){
        return this.employeeService.getEmployeeByID(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployeeById(@PathVariable("id") UUID id){
        this.employeeService.deleteEmployeeById(id);
    }
    @PutMapping(path = "{id}")
    public void updateEmployeeById(@PathVariable("id") UUID id,@RequestBody Employee employeeToUpdate){
        this.employeeService.updateEmployeeById(id, employeeToUpdate);
    }
}
