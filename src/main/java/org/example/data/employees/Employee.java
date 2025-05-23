package org.example.data.employees;

import java.io.Serializable;

public abstract class Employee implements Serializable {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public abstract double calculateSalary(double revenue, double bonusThreshold);

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
