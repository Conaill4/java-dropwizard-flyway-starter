package org.example.controllers;

import io.swagger.annotations.Api;

import org.example.models.ClientRequest;

import org.example.services.ClientService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Engineering Academy Dropwizard DCML")
@Path("/api")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) { this.clientService = clientService; ;}

    @GET
    @Path("/clients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() throws SQLException
    {   try {
        return Response.ok().entity(clientService.getAllClients()).build();
    }catch (SQLException e){
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }


    }

}
