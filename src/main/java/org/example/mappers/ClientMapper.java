package org.example.mappers;

import org.example.models.Client;

import java.util.List;
import java.util.stream.Collectors;
import  org.example.models.ClientResponse;

public class ClientMapper {

    public static List<ClientResponse> mapClientListToClientResponseList(final
            List<Client> clients) {
            return clients.stream()
                    .map(client -> new ClientResponse(
                            client.getClientId(),
                            client.getClientName()))
                    .collect(Collectors.toList());
        }

        public ClientMapper(){

        }


    }


