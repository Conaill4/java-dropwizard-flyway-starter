package org.example.models;

public class DeliveryEmployee {
    private int deliveryEmployeeId;
    private Employee employee;

    public DeliveryEmployee(final int deliveryEmployeeId,
                            final Employee employee) {
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.employee = employee;
    }




    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(final int deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }
}
