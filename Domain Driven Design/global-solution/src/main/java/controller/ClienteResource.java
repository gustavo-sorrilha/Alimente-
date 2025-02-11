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
import model.Cliente;
import services.ClienteService;

@Path("/cliente")
public class ClienteResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid Cliente cliente) {
		Cliente response = ClienteService.create(cliente);

		if (response != null) {
			// @formatter:off
			final URI clienteUri = UriBuilder
					.fromResource(ClienteResource.class)
					.path("/cliente/{id}")
					.build(response.getId_cliente());
			// @formatter:on
			
			return Response.created(clienteUri).entity(response).build();
		} else {
			return Response
					.status(400)
					.entity("Não foi possível criar este usuário")
					.build();
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validarLogin(Cliente clienteLogin) {
	    String email = clienteLogin.getEmail();
	    String senha = clienteLogin.getSenha();

	    Cliente cliente_logado = ClienteService.validarLogin(email, senha);

	    if (cliente_logado != null) {              
	        return Response.ok().entity(cliente_logado).build();    
	    } else {
	        return Response.status(401).entity("Email e/ou senha inválida.").build();
	    }
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Cliente> response = ClienteService.findAll();
		
		if (response != null) {
			return Response.ok().entity(response).build();			
		} else {
			return Response
					.status(400)
					.entity("Não foi possível listar os usuários")
					.build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") int id) {
		Cliente response = ClienteService.findById(id);
		
		if (response != null) {
			return Response.ok().entity(response).build();			
		} else {
			return Response
					.status(404)
					.entity("Não foi possível encontrar o usuário com ID: " + id)
					.build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, @Valid Cliente cliente) {
		Cliente response = null;
		response = ClienteService.update(id, cliente);
		
		if (response != null) {
			return Response.ok(response).build();		
		} else {
			return Response
					.status(400)
					.entity("Não foi possível atualizar o usuário com ID: " + id)
					.build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		if (ClienteService.delete(id)) {
			return Response.noContent().build();			
		} else {
			return Response
					.status(400)
					.entity("Não foi possível deletar o usuário com ID: " + id)
					.build();
		}

	}
}
