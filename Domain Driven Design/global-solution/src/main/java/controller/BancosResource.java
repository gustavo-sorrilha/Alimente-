package controller;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Bancos;
import services.BancosService;

@Path("/bancos")
public class BancosResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBancos() {
        BancosService bancosService = new BancosService();
        List<Bancos> bancos = null;

        try {
            bancos = bancosService.getBancos();
        } catch (ClientProtocolException e) {
            String mensagem = "Ocorreu um erro de protocolo ao buscar os bancos. Detalhes: " + e.getMessage();
            return Response.status(500).entity(mensagem).build();
        } catch (IOException e) {
            String mensagem = "Ocorreu um erro de I/O ao buscar os bancos. Detalhes: " + e.getMessage();
            return Response.status(500).entity(mensagem).build();
        }

        if (bancos != null) {
            return Response.ok().entity(bancos).build();
        } else {
            return Response.status(400).entity("Não foi possível obter a lista de bancos.").build();
        }
    }
}
