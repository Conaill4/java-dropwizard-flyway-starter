package org.example.services;


import org.example.daos.ClientDao;
import org.example.mappers.ClientMapper;
import org.example.models.Client;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.ClientResponse;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<ClientResponse> getAllClients() throws SQLException {
        return ClientMapper.mapOrderListTo0rderResponseList(
                clientDao.getallclients());

        //  return OrderMapper.mapOrderListTo0rderResponseList(orderDao.getAllOrders());
    }

    public Client getClientById(int id) throws SQLException {
        return clientDao.getclientbyID(id);
    }

    public int createClient(ClientRequest clientRequest)
            throws SQLException {

        int id = clientDao.createClient(clientRequest);

        if (id == -1) {
            throw new SQLException("Failed to create product");
        }

        return id;

    }

}
