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
import model.Doacao;
import services.DoacaoService;

@Path("/doacao")
public class DoacaoResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Doacao doacao) {
        Doacao response = DoacaoService.create(doacao);

        if (response != null) {
            // @formatter:off
            final URI doacaoUri = UriBuilder
                    .fromResource(DoacaoResource.class)
                    .path("/doacao/{id}")
                    .build(response.getId_doacao());
            // @formatter:on

            return Response.created(doacaoUri).entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível criar esta doação")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Doacao> response = DoacaoService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível listar as doações")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        Doacao response = DoacaoService.findById(id);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(404)
                    .entity("Não foi possível encontrar a doação com ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Doacao doacao) {
        Doacao response = null;
        response = DoacaoService.update(id, doacao);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível atualizar a doação com ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        if (DoacaoService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível deletar a doação com ID: " + id)
                    .build();
        }

    }
}
