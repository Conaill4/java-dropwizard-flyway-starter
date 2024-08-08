package org.example.controllers;

import org.example.services.ProjectService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjects() {
        try {
            return Response.ok().entity(projectService.getAllProjects())
                    .build();
        } catch (SQLException e) {
            return Response.serverError().build();
            //throw new RuntimeException(e);
        }
        //return Response.ok().entity("List of Products").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(final ProjectRequest projectRequest) {
        try {
            //int orderId = orderService.createOrder(orderRequest);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(projectService.createProject(projectRequest))
                    .build();
        } catch (FailedToCreateException | SQLException e) {
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(
            @PathParam("id") final int id,
            final ProjectRequest productRequest) {
        try {
            productRequest.updateProject(id, productRequest);
            return Response.noContent().build();
        } catch (SQLException e) {
            //return Response.serverError().build();
        //} catch (InvalidException e) {
         //   return Response.status(Response.Status.BAD_REQUEST)
          //          .entity(e.getMessage()).build();
        //} catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

}
