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
import model.Beneficiario;
import services.BeneficiarioService;

@Path("/beneficiario")
public class BeneficiarioResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Beneficiario beneficiario) {
        Beneficiario response = BeneficiarioService.create(beneficiario);

        if (response != null) {
            final URI beneficiarioUri = UriBuilder
                    .fromResource(BeneficiarioResource.class)
                    .path("/beneficiario/{id}")
                    .build(response.getId_cliente());

            return Response.created(beneficiarioUri).entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível criar este beneficiário")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Beneficiario> response = BeneficiarioService.findAll();

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível listar os beneficiários")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        Beneficiario response = BeneficiarioService.findById(id);

        if (response != null) {
            return Response.ok().entity(response).build();
        } else {
            return Response
                    .status(404)
                    .entity("Não foi possível encontrar o beneficiário com ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Beneficiario beneficiario) {
        Beneficiario response = BeneficiarioService.update(id, beneficiario);

        if (response != null) {
            return Response.ok(response).build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível atualizar o beneficiário com ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        if (BeneficiarioService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response
                    .status(400)
                    .entity("Não foi possível deletar o beneficiário com ID: " + id)
                    .build();
        }
    }
}
