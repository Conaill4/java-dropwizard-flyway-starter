package org.example.services;

import org.example.mappers.ClientMapper;
import org.example.models.Client;

import org.example.models.ClientResponse;
import org.example.models.ClientRequest;
import org.example.daos.ClientDao;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<ClientResponse> getAllClients() throws SQLException {
        return ClientMapper.mapClientListToClientResponseList(
                clientDao.getallclients());
    }


}
