package org.example.service;
import org.example.data.employees.Employee;
import org.example.service.interfaces.EmployeeService;
import java.util.HashSet;
import java.util.Set;

public class EmployeeServiceImpl implements EmployeeService {
    private final Set<Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashSet<>();
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public Set<Employee> getEmployees() {
        return employees;
    }

    @Override
    public double calculateTotalSalaries(double revenue, double bonusThreshold) {
        return employees.stream()
                .mapToDouble(e -> e.calculateSalary(revenue, bonusThreshold))
                .sum();
    }
}
