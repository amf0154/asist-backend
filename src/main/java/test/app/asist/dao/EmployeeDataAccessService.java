package test.app.asist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import test.app.asist.modal.Employee;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class EmployeeDataAccessService implements EmployeeDao {
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertEmployee(UUID id, Employee employee) {
        String sql = "INSERT INTO employee (" +
                "id," +
                "name," +
                "workingHours," +
                "vacationHours," +
                "sickHours," +
                "date)"+
                "VALUES (?, ?, ?, ?, ?, ?::date)";
        return jdbcTemplate.update(
                sql,
                id,
                employee.getName(),
                employee.getWorkingHours(),
                employee.getVacationHours(),
                employee.getSickHours(),
                employee.getDate()
        );
    }

    @Override
    public List<Employee> selectAllEmployees() {
        final String sql = "SELECT * from employee";
        return jdbcTemplate.query(sql,(resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            return new Employee(
                    id,
                    resultSet.getString("name"),
                    resultSet.getInt("workingHours"),
                    resultSet.getInt("vacationHours"),
                    resultSet.getInt("sickHours"),
                    resultSet.getDate("date")
            );
        });
    }

    @Override
    public int deleteEmployeeById(UUID id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateEmployeeById(UUID id, Employee employeeUpdateData) {
        String sql = "UPDATE employee " +
                "SET name = ?,workingHours = ?,vacationHours = ?,sickHours = ?, date = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                employeeUpdateData.getName(),
                employeeUpdateData.getWorkingHours(),
                employeeUpdateData.getVacationHours(),
                employeeUpdateData.getSickHours(),
                employeeUpdateData.getDate(),
                id
        );
    }

    @Override
    public Optional<Employee> selectEmployeeById(UUID id) {
        final String sql = "SELECT * from employee where id = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{id},(resultSet, i) -> {
            UUID employeeId = UUID.fromString(resultSet.getString("id"));
            return new Employee(
                    employeeId,
                    resultSet.getString("name"),
                    resultSet.getInt("workingHours"),
                    resultSet.getInt("vacationHours"),
                    resultSet.getInt("sickHours"),
                    resultSet.getDate("date")
            );
        });
        return Optional.ofNullable(employee);
    }
}
