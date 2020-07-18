package test.app.asist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import test.app.asist.dao.EmployeeDao;
import test.app.asist.modal.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    private EmployeeDao employeeDao;
    @Autowired
    public EmployeeService(@Qualifier("fakeDao") EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }

    public int addEmployee(Employee employee){
        return employeeDao.insertEmployee(employee);
    }

    public List<Employee> getAllEmployee(){
        return employeeDao.selectAllEmployees();
    }

    public Optional<Employee> getEmployeeByID(UUID id){
        return this.employeeDao.selectEmployeeById(id);
    }

    public int deleteEmployeeById(UUID id){
        return this.employeeDao.deleteEmployeeById(id);
    }

    public int updateEmployeeById(UUID id,Employee newEmployee){
        return this.employeeDao.updateEmployeeById(id, newEmployee);
    }
}
