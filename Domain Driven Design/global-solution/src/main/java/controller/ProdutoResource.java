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
import model.Produto;
import services.ProdutoService;

@Path("/produto")
public class ProdutoResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Produto produto) {
        Produto response = ProdutoService.create(produto);

        if (response != null) {
            // @formatter:off
            final URI produtoUri = UriBuilder
                    .fromResource(ProdutoResource.class)
                    .path("/{id}")
                    .build(response.getId_produto());
            // @formatter:on

            return Response.created(produtoUri).entity(response).build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Não foi possível criar este produto")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Produto> response = ProdutoService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Não foi possível listar os produtos")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        Produto response = ProdutoService.findById(id);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Não foi possível encontrar o produto com ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, @Valid Produto produto) {
        Produto response = null;
        response = ProdutoService.update(id, produto);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Não foi possível atualizar o produto com ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        if (ProdutoService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Não foi possível deletar o produto com ID: " + id)
                    .build();
        }
    }
}
