package org.example.data.employees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    // Тестов подклас за Employee
    static class TestEmployee extends Employee {
        public TestEmployee(String name, double baseSalary) {
            super(name, baseSalary);
        }

        @Override
        public double calculateSalary(double revenue, double bonusThreshold) {
            if (revenue > bonusThreshold) {
                return baseSalary + 1000;
            }
            return baseSalary;
        }
    }

    @Test
    void testConstructorAndGetters() {
        Employee emp = new TestEmployee("Иван", 2000.0);
        assertEquals("Иван", emp.getName());
        assertEquals(2000.0, emp.getBaseSalary());
    }

    @Test
    void testCalculateSalary_BelowThreshold() { //тества метода calculateSalary с приход по-малък от прага за бонус
        Employee emp = new TestEmployee("Иван", 2000.0);
        double salary = emp.calculateSalary(5000, 10000);
        assertEquals(2000.0, salary);
    }

    @Test
    void testCalculateSalary_AboveThreshold() { // тества calculateSalary с приход над прага за бонус
        Employee emp = new TestEmployee("Иван", 2000.0);
        double salary = emp.calculateSalary(15000, 10000);
        assertEquals(3000.0, salary); // 2000 + 1000 бонус
    }

}