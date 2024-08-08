package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.services.DeliveryEmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("DCML Employee API")
@Path("/api/deliveryEmployees")
public class DeliveryEmployeeController {
    DeliveryEmployeeService deliveryEmployeeService;

    public DeliveryEmployeeController(DeliveryEmployeeService deliveryEmployeeService) {this.deliveryEmployeeService = deliveryEmployeeService;}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployees() throws SQLException, ClassNotFoundException {
        return Response.ok().entity(deliveryEmployeeService.getAllDeliveryEmployees()).build();
    }


}
