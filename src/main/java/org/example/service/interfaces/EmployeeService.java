package org.example.service.interfaces;
import org.example.data.employees.Employee;

import java.util.Set;

public interface EmployeeService {
    void addEmployee(Employee employee);
    Set<Employee> getEmployees();
    double calculateTotalSalaries(double revenue, double bonusThreshold);
}
