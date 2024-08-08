package org.example.services;

import org.example.daos.DeliveryEmployeeDao;
import org.example.models.DeliveryEmployee;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {
        DeliveryEmployeeDao deliveryEmployeeDao;

    public DeliveryEmployeeService(DeliveryEmployeeDao deliveryEmployeeDao){
        this.deliveryEmployeeDao = deliveryEmployeeDao;
    }

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws SQLException {
        return deliveryEmployeeDao.getAllDeliveryEmployees();
    }
}
