package org.example.daos;

import org.example.models.DeliveryEmployee;
import org.example.models.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {
    public List<DeliveryEmployee> getAllDeliveryEmployees() throws SQLException {
        List<DeliveryEmployee> deliveryEmployees = new ArrayList<>();

        try(Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT DeliveryID, EmployeeID FROM `DeliveryEmployee` join `Employee` using (EmployeeID);");

            while (resultSet.next()) {
                DeliveryEmployee deliveryEmployee = new DeliveryEmployee(
                        resultSet.getInt("DeliveryID"),
                        new Employee(resultSet.getInt("EmployeeID"));

                deliveryEmployees.add(deliveryEmployee);
            }
        }

        return deliveryEmployees;
    }
}
