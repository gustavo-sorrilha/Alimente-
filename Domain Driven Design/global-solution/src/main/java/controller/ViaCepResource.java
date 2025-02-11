package controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Endereco;
import services.ViaCepService;

@Path("/endereco")
public class ViaCepResource {
	@GET
	@Path("/{cep}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEndereco(@PathParam("cep") String cep) {
		ViaCepService viaCepService = new ViaCepService();
		Endereco endereco = null;
		
		try {
			endereco = viaCepService.getEndereco(cep);
		} catch (ClientProtocolException e) {
			String mensagem = "Ocorreu um erro de protocolo ao buscar o endereço. Detalhes: " + e.getMessage();
			return Response.status(500).entity(mensagem).build();
		} catch (IOException e) {
			String mensagem = "Ocorreu um erro de I/O ao buscar o endereço. Detalhes: " + e.getMessage();
			return Response.status(500).entity(mensagem).build();
		}
		
		if (endereco.getCep() != null) {
			return Response.ok().entity(endereco).build();			
		} else {
			return Response
					.status(400)
					.entity("Não foi possível obter o endereço para o CEP:" + cep)
					.build();
		}
	}
}
