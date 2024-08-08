package org.example.controllers;

import io.swagger.annotations.Api;

import org.example.models.Client;
import org.example.models.ClientRequest;

import org.example.services.ClientService;
import org.glassfish.jersey.client.ClientRequest;

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

    @GET
    @Path ("/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id")int id) throws SQLException {
        try {
            return Response.ok().entity(clientService.getClientById(id)).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(ClientRequest ClientR) throws SQLException{
        try{
            return Response.status(Response.Status.CREATED)
                    .entity(clientService.createClient(ClientR)).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

}
