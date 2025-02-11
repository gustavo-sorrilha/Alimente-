package controller;

import java.net.URI;
import java.util.List;

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
import model.DoadorDoacao;
import services.DoadorDoacaoService;

@Path("/doador_doacao")
public class DoadorDoacaoResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(DoadorDoacao doadorDoacao) {
        DoadorDoacao response = DoadorDoacaoService.create(doadorDoacao);

        if (response != null) {
            final URI doadorDoacaoUri = UriBuilder
                    .fromResource(DoadorDoacaoResource.class)
                    .path("/doador_doacao/{idDoador}/{idDoacao}")
                    .build(response.getIdDoador(), response.getIdDoacao());

            return Response.created(doadorDoacaoUri).entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível criar este relacionamento doador-doacao")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<DoadorDoacao> response = DoadorDoacaoService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível listar os relacionamentos doador-doacao")
                    .build();
        }
    }

    @GET
    @Path("/{idDoador}/{idDoacao}")
    public Response findByIds(@PathParam("idDoador") int idDoador, @PathParam("idDoacao") int idDoacao) {
        DoadorDoacao response = DoadorDoacaoService.findByIds(idDoador, idDoacao);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(404)
                    .entity("Não foi possível encontrar o relacionamento doador-doacao com os IDs: " + idDoador + ", " + idDoacao)
                    .build();
        }
    }

    @PUT
    @Path("/{idDoador}/{idDoacao}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idDoador") int idDoador, @PathParam("idDoacao") int idDoacao, DoadorDoacao doadorDoacao) {
        DoadorDoacao response = DoadorDoacaoService.update(idDoador, idDoacao, doadorDoacao);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível atualizar o relacionamento doador-doacao com os IDs: " + idDoador + ", " + idDoacao)
                    .build();
        }
    }

    @DELETE
    @Path("/{idDoador}/{idDoacao}")
    public Response delete(@PathParam("idDoador") int idDoador, @PathParam("idDoacao") int idDoacao) {
        if (DoadorDoacaoService.delete(idDoador, idDoacao)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível deletar o relacionamento doador-doacao com os IDs: " + idDoador + ", " + idDoacao)
                    .build();
        }
    }
}
