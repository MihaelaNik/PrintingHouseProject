package org.example.data.employees;

public class MachineOperator extends Employee{
    private static final long serialVersionUID = 1L;

    public MachineOperator(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double calculateSalary(double revenue, double bonusThreshold) {
        return baseSalary;
    }
}
