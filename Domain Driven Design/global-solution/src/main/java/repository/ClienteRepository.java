package repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import model.Beneficiario;
import model.Cliente;
import model.Doador;
import services.BeneficiarioService;
import services.DoadorService;

public class ClienteRepository extends Repository {
	public static Cliente create(@Valid Cliente cliente) {
		// @formatter:off
		String sql = "DECLARE v_id_cliente NUMBER; BEGIN INSERT INTO cliente (id_cliente, nome, email, senha, termo) VALUES (SQ_CLIENTE.nextval, ?, ?, ?, ?) RETURNING id_cliente INTO v_id_cliente; ? := v_id_cliente; END;";
		// @formatter:on
		
		Connection connection = null;
	    CallableStatement cs = null;

		try {
			connection = getConnection();
	        cs = connection.prepareCall(sql);

			cs.setString(1, cliente.getNome());
			cs.setString(2, cliente.getEmail());
			cs.setString(3, cliente.getSenha());
			cs.setInt(4, cliente.getTermo());
			cs.registerOutParameter(5, java.sql.Types.INTEGER); // Id de retorno
			cs.executeUpdate();
			cliente.setId_cliente(cs.getInt(5)); // Setando o id de retorno no objeto cliente

			return cliente;

		} catch (SQLException e) {
			System.out.println("Erro ao salvar o usuário no banco de dados: " + e.getMessage());
		} finally {
	        if (cs != null) {
	            try {
	                cs.close();
	            } catch (SQLException e) {
	                System.out.println("Erro ao fechar o CallableStatement: " + e.getMessage());
	            }
	        }

	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
	            }
	        }
	    }

		return null;
	}

	public static List<Cliente> findAll() {
		String sql = "SELECT * FROM cliente";

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId_cliente(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setTermo(rs.getInt("termo"));
				clientes.add(cliente);
			}

			if (clientes.isEmpty())
				System.out.println("Não foram encontrados registros na tabela cliente");

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem de clientes: " + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o Prepared Statement " + e.getMessage());
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					System.out.println("Erro ao fechar o ResultSet: " + e2.getMessage());
				}
			}
		}

		return clientes;
	}

	public static Cliente findById(int id) {
		String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Cliente cliente = new Cliente();

				while (rs.next()) {
					cliente.setId_cliente(rs.getInt("id_cliente"));
					cliente.setNome(rs.getString("nome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setSenha(rs.getString("senha"));
					cliente.setTermo(rs.getInt("termo"));
				}

				return cliente;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar este cliente: " + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o Prepared Statement " + e.getMessage());
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					System.out.println("Erro ao fechar o ResultSet: " + e2.getMessage());
				}
			}
		}

		return null;
	}
	
	public static Cliente findByEmail(String email) {
	    String sql = "SELECT * FROM cliente WHERE email = ?";

	    try (Connection connection = getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setString(1, email);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                int clienteId = rs.getInt("id_cliente");

	                Doador isDoador = DoadorService.findById(clienteId);
	                if (isDoador != null && isDoador.getCnpj() != null) {
	                    // Se for doador, retorna um objeto do tipo Doador
	                    return isDoador;
	                }

	                Beneficiario isBeneficiario = BeneficiarioService.findById(clienteId);
	                if (isBeneficiario != null && isBeneficiario.getCpf() != null) {
	                    return isBeneficiario;
	                } else {
	                    Cliente cliente = new Cliente();
	                    cliente.setId_cliente(clienteId);
	                    cliente.setNome(rs.getString("nome"));
	                    cliente.setEmail(rs.getString("email"));
	                    cliente.setSenha(rs.getString("senha"));
	                    cliente.setTermo(rs.getInt("termo"));
	                    return cliente;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Não foi possível consultar este cliente: " + e.getMessage());
	    }

	    return null;
	}


	public static Cliente update(@Valid Cliente cliente, int id) {
	    String sql = "BEGIN UPDATE cliente SET nome = ?, email = ?, senha = ?, termo = ? WHERE id_cliente = ? RETURNING id_cliente INTO ?; END;";
	    
	    CallableStatement cs = null;
	    
	    try {
	        cs = getConnection().prepareCall(sql);
	        
	        cs.setString(1, cliente.getNome());
	        cs.setString(2, cliente.getEmail());
	        cs.setString(3, cliente.getSenha());
	        cs.setInt(4, cliente.getTermo());
	        cs.setInt(5, id);
	        cs.registerOutParameter(6, java.sql.Types.INTEGER); 
	        cs.executeUpdate();
	        
	        cliente.setId_cliente(cs.getInt(6));
	        
	        return cliente;
	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar cliente no banco de dados: " + e.getMessage());
	    } finally {
	        if (cs != null) {
	            try {
	                cs.close();
	            } catch (SQLException e) {
	                System.out.println("Não foi possível fechar o Callable Statement: " + e.getMessage());
	            }
	        }
	    }
	    
	    return null;
	}


	public static boolean delete(int clienteId) {
		Cliente cliente = null;
		String sql = "DELETE FROM cliente WHERE id_cliente = ?";
		PreparedStatement ps = null;

		cliente = findById(clienteId);

		if (cliente == null)
			return false;
		
		Doador usuarioDoador = DoadorService.findById(clienteId);
	    if (usuarioDoador != null) DoadorService.delete(usuarioDoador.getId_cliente());
	    
	    Beneficiario usuarioBeneficiario = BeneficiarioService.findById(clienteId);
	    if (usuarioBeneficiario != null) BeneficiarioService.delete(usuarioBeneficiario.getId_cliente());

		try {
			ps = getConnection().prepareStatement(sql);

			ps.setInt(1, clienteId);
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.out.println("Erro para deletar o cliente no banco de dados: " + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar a conexão com banco de dados " + e.getMessage());
				}
			}
		}

		return false;
	}
}
