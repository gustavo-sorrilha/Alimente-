package controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import model.Selo;
import services.SeloService;

@Path("/selo")
public class SeloResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Selo selo) {
        Selo response = SeloService.create(selo);

        if (response != null) {
            // @formatter:off
            final URI seloUri = UriBuilder
                    .fromResource(SeloResource.class)
                    .path("/selo/{id}")
                    .build(response.getId_selo());
            // @formatter:on

            return Response.created(seloUri).entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível criar este selo")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Selo> response = SeloService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível listar os selos")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        Selo response = SeloService.findById(id);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(404)
                    .entity("Não foi possível encontrar o selo com ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, @Valid Selo selo) {
        Selo response = null;
        response = SeloService.update(id, selo);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível atualizar o selo com ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        if (SeloService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível deletar o selo com ID: " + id)
                    .build();
        }
    }
}
