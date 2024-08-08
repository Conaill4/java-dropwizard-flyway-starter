package org.example.models;

import java.math.BigDecimal;

public class Employee {
    private int employeeId;
    private String employeeName;
    private BigDecimal salary;
    private int bankAccountNumber;
    private int nationalInsuranceNumber;

    public Employee(final int employeeId, final String employeeName,
                    final BigDecimal salary, final int bankAccountNumber,
                    final int nationalInsuranceNumber) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(final int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(final BigDecimal salary) {
        this.salary = salary;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(final int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(final int nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }
}
