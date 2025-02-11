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
import model.Doador;
import services.DoadorService;

@Path("/doador")
public class DoadorResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Doador doador) {
        Doador response = DoadorService.create(doador);

        if (response != null) {
            final URI doadorUri = UriBuilder
                    .fromResource(DoadorResource.class)
                    .path("/doador/{id}")
                    .build(response.getId_cliente());

            return Response.created(doadorUri).entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível criar o doador")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Doador> response = DoadorService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível listar os doadores")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        Doador response = DoadorService.findById(id);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(404)
                    .entity("Não foi possível encontrar o doador com ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Doador doador) {
        Doador response = null;
        response = DoadorService.update(id, doador);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível atualizar o doador com ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        if (DoadorService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível deletar o doador com ID: " + id)
                    .build();
        }
    }
}
