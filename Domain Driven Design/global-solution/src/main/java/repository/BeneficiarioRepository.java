package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import model.Beneficiario;

public class BeneficiarioRepository extends Repository {
	public static Beneficiario create(@Valid Beneficiario beneficiario) {
	    String sql = "INSERT INTO beneficiario (id_cliente, cpf, quantidade_recebida) VALUES (?, ?, ?)";

	    Connection connection = null;
	    PreparedStatement ps = null;

	    try {
	        connection = getConnection();
	        ps = connection.prepareStatement(sql);

	        ps.setInt(1, beneficiario.getId_cliente());
	        ps.setString(2, beneficiario.getCpf());
	        ps.setInt(3, beneficiario.getQuantidade_recebida());

	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected > 0) {
	            return beneficiario;
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao salvar o beneficiário no banco de dados: " + e.getMessage());
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                System.out.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
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


    public static List<Beneficiario> findAll() {
    	String sql = "SELECT * FROM cliente JOIN beneficiario ON cliente.id_cliente = beneficiario.id_cliente";

    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
        try {
        	ps = getConnection().prepareStatement(sql);
        	rs = ps.executeQuery();
        	
            List<Beneficiario> beneficiarios = new ArrayList<>();

            while (rs.next()) {
                Beneficiario beneficiario = new Beneficiario();
                beneficiario.setId_cliente(rs.getInt("id_cliente"));
                beneficiario.setCpf(rs.getString("cpf"));
                beneficiario.setQuantidade_recebida(rs.getInt("quantidade_recebida"));
                beneficiario.setNome(rs.getString("nome"));
                beneficiario.setEmail(rs.getString("email"));
                beneficiario.setSenha(rs.getString("senha"));
                beneficiario.setTermo(rs.getInt("termo"));
                
                beneficiarios.add(beneficiario);
            }

            if (beneficiarios.isEmpty()) {
                System.out.println("Não foram encontrados registros na tabela beneficiario");
            }

            return beneficiarios;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a listagem de beneficiários: " + e.getMessage());
        } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                System.out.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
	            }
	        }

	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
	            }
	        }
	    }

        return null;
    }

    public static Beneficiario findById(int id) {
        String sql = "SELECT * FROM cliente JOIN beneficiario ON cliente.id_cliente = beneficiario.id_cliente WHERE cliente.id_cliente = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.isBeforeFirst()) {
                    Beneficiario beneficiario = new Beneficiario();

                    while (rs.next()) {
                        beneficiario.setId_cliente(rs.getInt("id_cliente"));
                        beneficiario.setCpf(rs.getString("cpf"));
                        beneficiario.setQuantidade_recebida(rs.getInt("quantidade_recebida"));
                        beneficiario.setNome(rs.getString("nome"));
                        beneficiario.setEmail(rs.getString("email"));
                        beneficiario.setSenha(rs.getString("senha"));
                        beneficiario.setTermo(rs.getInt("termo"));
                    }

                    return beneficiario;
                }
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar este beneficiário: " + e.getMessage());
        }

        return null;
    }

    public static Beneficiario update(Beneficiario beneficiario, int id) {
        String sql = "UPDATE beneficiario SET cpf = ?, quantidade_recebida = ? WHERE id_cliente = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, beneficiario.getCpf());
            ps.setInt(2, beneficiario.getQuantidade_recebida());
            ps.setInt(3, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return beneficiario;
            } 
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar beneficiário no banco de dados: " + e.getMessage());
        }

        return null;
    }

    public static boolean delete(int clienteId) {
        Beneficiario beneficiario = findById(clienteId);

        if (beneficiario == null) {
            return false;
        }

        String sql = "DELETE FROM beneficiario WHERE id_cliente = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro para deletar o beneficiário no banco de dados: " + e.getMessage());
        }

        return false;
    }
}
