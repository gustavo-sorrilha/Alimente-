package services;

import java.util.List;

import model.Cliente;
import repository.ClienteRepository;

public class ClienteService {

	public static boolean exists(int id) {
		return ClienteRepository.findById(id) != null ? true : false;
	}

	public static Cliente create(Cliente cliente) {
		return ClienteRepository.create(cliente);
	}

	public static List<Cliente> findAll() {
		return ClienteRepository.findAll();
	}

	public static Cliente findById(int id) {
		return ClienteRepository.findById(id);
	}

	public static Cliente update(int id, Cliente cliente) {
		boolean clienteExiste = ClienteService.findById(id) != null ? true : false;
		Cliente clienteAtualizado = null;

		if (clienteExiste) {
			clienteAtualizado = ClienteRepository.update(cliente, id);
			clienteAtualizado.setId_cliente(id);

			return clienteAtualizado;
		} else {
			return null;
		}

	}

	public static boolean delete(int id) {
		if (exists(id)) {
			return ClienteRepository.delete(id);
		} else {
			return false;
		}
	}
	
	public static Cliente validarLogin(String email, String senha) {		
	    Cliente cliente_login = ClienteRepository.findByEmail(email);

	    if (cliente_login != null && cliente_login.getSenha().equals(senha)) {
	        return cliente_login;
	    } else {
	        return null;
	    }
	}
}
