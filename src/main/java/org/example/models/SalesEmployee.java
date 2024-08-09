package org.example.models;

import java.math.BigDecimal;

public class SalesEmployee {
    private int employeeId;
    private int salesEmployeeId;
    private BigDecimal commissionRate;

    public SalesEmployee(int employeeId, int salesEmployeeId,
                         final BigDecimal commissionRate) {
        this.employeeId = employeeId;
        this.salesEmployeeId = salesEmployeeId;
        this.commissionRate = commissionRate;
    }


    public int getSalesEmployeeId() {
        return salesEmployeeId;
    }

    public void setSalesEmployeeId(final int salesEmployeeId) {
        this.salesEmployeeId = salesEmployeeId;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(final BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployee(final int employeeId) {
        this.employeeId = employeeId;
    }
}

