package org.example.mappers;

import org.example.models.Client;
import org.glassfish.jersey.client.ClientResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.example.models.Client;

public class ClientMapper {

    public static List<ClientResponse> mapOrderListTo0rderResponseList(
            List<Client> getallclients) {
        public static List<ClientResponse> mapOrderListTo0rderResponseList(List<Client> clients){

            return clients.stream().map(order -> new ClientResponse(client.getclientbyid(), client.getclientname()))
                    .collect(Collectors.toList());
        }

    }
}
