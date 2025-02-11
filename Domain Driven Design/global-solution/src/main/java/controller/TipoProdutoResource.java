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

import model.TipoProduto;
import services.TipoProdutoService;

@Path("/tipo-produto")
public class TipoProdutoResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid TipoProduto tipoProduto) {
        TipoProduto response = TipoProdutoService.create(tipoProduto);

        if (response != null) {
            final URI tipoProdutoUri = UriBuilder
                    .fromResource(TipoProdutoResource.class)
                    .path("/tipo-produto/{id}")
                    .build(response.getId_tipo_produto());

            return Response.created(tipoProdutoUri).entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível criar o tipo de produto")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<TipoProduto> response = TipoProdutoService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível listar os tipos de produto")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        TipoProduto response = TipoProdutoService.findById(id);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(404)
                    .entity("Não foi possível encontrar o tipo de produto com ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, @Valid TipoProduto tipoProduto) {
        TipoProduto response = TipoProdutoService.update(id, tipoProduto);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível atualizar o tipo de produto com ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        if (TipoProdutoService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível deletar o tipo de produto com ID: " + id)
                    .build();
        }
    }
}
