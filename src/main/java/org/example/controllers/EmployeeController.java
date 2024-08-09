package org.example.controllers;
import io.swagger.annotations.Api;
import org.example.services.EmployeeService;
import org.example.models.EmployeeRequest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("DCML Employee API")
@Path("/api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() throws SQLException {
        return Response.ok().entity(employeeService.getAllEmployees()).build();
    }

    @GET
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSalesEmployees() throws SQLException {
        return Response.ok().entity(employeeService
                .getSalesEmployees()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") final int id)
            throws SQLException {
        return Response.ok().entity(employeeService.getEmployeeById(id))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(final EmployeeRequest
                                               employeeRequest)
            throws SQLException {
        try {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(employeeService.createEmployee(employeeRequest))
                    .build();
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(
            @PathParam("id") final int id, final
    EmployeeRequest employeeRequest) {
        try {
            employeeService.updateEmployee(id, employeeRequest);
            return Response.ok().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
