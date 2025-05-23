package org.example.data.employees;

public class Manager extends Employee{
    private static final long serialVersionUID = 1L;
    private final double bonusPercent;

    public Manager(String name, double baseSalary, double bonusPercent) {
        super(name, baseSalary);
        this.bonusPercent = bonusPercent;
    }

    @Override
    public double calculateSalary(double revenue, double bonusThreshold) {
        if (revenue > bonusThreshold) {
            return baseSalary * (1 + bonusPercent / 100);
        } else {
            return baseSalary;
        }
    }

    public double getBonusPercent() {
        return bonusPercent;
    }
}

