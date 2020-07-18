package test.app.asist.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import test.app.asist.modal.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeEmployeeDataAccessService implements EmployeeDao {
    private static List<Employee> DB = new ArrayList<>();


    @Override
    public int insertEmployee(UUID id, Employee employee) {
        DB.add(new Employee(id, employee.getName(), employee.getWorkingHours(), employee.getVacationHours(), employee.getSickHours(),employee.getDate()));
        return 1;
    }
    @Override
    public List <Employee> selectAllEmployees(){
        return DB;
    }

    @Override
    public int deleteEmployeeById(UUID id) {
        Optional<Employee> maybeEmployee = selectEmployeeById(id);
        if(maybeEmployee.isEmpty()){
            return 0;
        }
        DB.remove(maybeEmployee.get());
        return 1;
    }

    @Override
    public int updateEmployeeById(UUID id, Employee employeeUpdateData) {
        return selectEmployeeById(id).map(employee -> {
            int indexOfEmployeeToUpdate = DB.indexOf(employee);
            if(indexOfEmployeeToUpdate >=0){
                DB.set(indexOfEmployeeToUpdate,
                        new Employee(id,employeeUpdateData.getName(),
                                employeeUpdateData.getWorkingHours(),
                                employeeUpdateData.getVacationHours(),
                                employeeUpdateData.getSickHours(),
                                employeeUpdateData.getDate())
                );
                return 1;
            }
            return 0;
        }).orElse(null);
    }

    @Override
    public Optional<Employee> selectEmployeeById(UUID id) {
        return DB.stream().filter(employee -> employee.getId().equals(id)).findFirst();
    }

}
