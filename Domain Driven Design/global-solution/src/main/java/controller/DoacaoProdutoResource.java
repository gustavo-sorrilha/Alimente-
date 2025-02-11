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
import model.DoacaoProduto;
import services.DoacaoProdutoService;

@Path("/doacao_produto")
public class DoacaoProdutoResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid DoacaoProduto doacaoProduto) {
        DoacaoProduto response = DoacaoProdutoService.create(doacaoProduto);

        if (response != null) {
            final URI doacaoProdutoUri = UriBuilder
                    .fromResource(DoacaoProdutoResource.class)
                    .path("/doacao_produto/{idDoacao}/{idProduto}")
                    .build(response.getIdDoacao(), response.getIdProduto());

            return Response.created(doacaoProdutoUri).entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível criar esta doação de produto")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<DoacaoProduto> response = DoacaoProdutoService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível listar as doações de produto")
                    .build();
        }
    }

    @GET
    @Path("/{idDoacao}/{idProduto}")
    public Response findById(@PathParam("idDoacao") int idDoacao, @PathParam("idProduto") int idProduto) {
        DoacaoProduto response = DoacaoProdutoService.findById(idDoacao, idProduto);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(404)
                    .entity("Não foi possível encontrar a doação de produto com ID: " + idDoacao + "/" + idProduto)
                    .build();
        }
    }

    @PUT
    @Path("/{idDoacao}/{idProduto}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idDoacao") int idDoacao, @PathParam("idProduto") int idProduto, DoacaoProduto doacaoProduto) {
        DoacaoProduto response = DoacaoProdutoService.update(idDoacao, idProduto, doacaoProduto);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível atualizar a doação de produto com ID: " + idDoacao + "/" + idProduto)
                    .build();
        }
    }


    @DELETE
    @Path("/{idDoacao}/{idProduto}")
    public Response delete(@PathParam("idDoacao") int idDoacao, @PathParam("idProduto") int idProduto) {
        if (DoacaoProdutoService.delete(idDoacao, idProduto)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível deletar a doação de produto com ID: " + idDoacao + "/" + idProduto)
                    .build();
        }
    }
}
