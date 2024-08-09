package org.example.daos;

import org.example.models.Client;
import org.example.models.Employee;
import org.example.models.SalesEmployee;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ClientDao {

    public List<Client> getallclients() throws SQLException {

        List<Client> clients = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
        "SELECT ClientID,ClientName,SalesEmpID,SalesEmployee.Commission "
                + "FROM `Client` inner join `SalesEmployee` "
                + "N SalesEmployee.SalesID = Client.SalesEmpID;");

            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("ClientID"),
                        resultSet.getString("ClientName"),
                        new SalesEmployee(resultSet.getInt("SalesEmpID"),
                                 resultSet.getBigDecimal("Commission"),
                                new Employee()));
                clients.add(client);
            }

        }

        return clients;
    }

}




