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
import model.Movimentacao;
import services.MovimentacaoService;

@Path("/movimentacao")
public class MovimentacaoResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Movimentacao movimentacao) {
        Movimentacao response = MovimentacaoService.create(movimentacao);

        if (response != null) {
            // @formatter:off
            final URI movimentacaoUri = UriBuilder
                    .fromResource(MovimentacaoResource.class)
                    .path("/movimentacao/{id}")
                    .build(response.getId_movimentacao());
            // @formatter:on

            return Response.created(movimentacaoUri).entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível criar esta movimentação")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Movimentacao> response = MovimentacaoService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível listar as movimentações")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        Movimentacao response = MovimentacaoService.findById(id);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(404)
                    .entity("Não foi possível encontrar a movimentação com ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, @Valid Movimentacao movimentacao) {
        Movimentacao response = null;
        response = MovimentacaoService.update(id, movimentacao);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível atualizar a movimentação com ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        if (MovimentacaoService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível deletar a movimentação com ID: " + id)
                    .build();
        }
    }
}
