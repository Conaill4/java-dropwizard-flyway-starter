package org.example.daos;

import org.example.models.Client;
import org.example.models.ClientRequest;
import org.glassfish.jersey.client.ClientRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDao {

    public List<Client> getallclients() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try(Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT ClientID,ClientName,SalesEmpID FROM `Client` ORDER BY ClientID;");

            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("ProductID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getString("Price"));
                clients.add(client);
            }
        }

        return clients;
    }

    public int createClient(ClientRequest clientRequest) {

    }
}
