package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Movimentacao;

public class MovimentacaoRepository extends Repository {
    
	public static Movimentacao create(Movimentacao movimentacao) {
	    String sql = "INSERT INTO movimentacao (id_cliente, id_produto, data_movimentacao, id_movimentacao) VALUES (?, ?, ?, SQ_MOVIMENTACAO.nextval)";

	    Connection connection = null;
	    PreparedStatement ps = null;

	    try {
	        connection = getConnection();
	        ps = connection.prepareStatement(sql, new String[] { "id_movimentacao" });

	        ps.setInt(1, movimentacao.getId_cliente());
	        ps.setInt(2, movimentacao.getId_produto());
	        ps.setDate(3, new java.sql.Date(movimentacao.getData_movimentacao().getTime()));

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                movimentacao.setId_movimentacao(generatedKeys.getInt(1));
	            }
	            return movimentacao;
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao criar a movimentação no banco de dados: " + e.getMessage());
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
	
    public static List<Movimentacao> findAll() {
        String sql = "SELECT * FROM movimentacao";
        
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            List<Movimentacao> movimentacoes = new ArrayList<>();
            
            while (rs.next()) {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setId_movimentacao(rs.getInt("id_movimentacao"));
                movimentacao.setId_cliente(rs.getInt("id_cliente"));
                movimentacao.setId_produto(rs.getInt("id_produto"));
                movimentacao.setData_movimentacao(rs.getDate("data_movimentacao"));
                
                movimentacoes.add(movimentacao);
            }
            
            if (movimentacoes.isEmpty()) {
                System.out.println("Não foram encontrados registros na tabela movimentacao");
            }
            
            return movimentacoes;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a listagem de movimentações: " + e.getMessage());
        }
        
        return null;
    }
    
    public static Movimentacao findById(int id) {
        String sql = "SELECT * FROM movimentacao WHERE id_movimentacao = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setId_movimentacao(rs.getInt("id_movimentacao"));
                movimentacao.setId_cliente(rs.getInt("id_cliente"));
                movimentacao.setId_produto(rs.getInt("id_produto"));
                movimentacao.setData_movimentacao(rs.getDate("data_movimentacao"));

                return movimentacao;
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar esta movimentação: " + e.getMessage());
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

    
    public static Movimentacao update(Movimentacao movimentacao, int id) {
        String sql = "UPDATE movimentacao SET id_cliente = ?, id_produto = ?, data_movimentacao = ? WHERE id_movimentacao = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, movimentacao.getId_cliente());
            ps.setInt(2, movimentacao.getId_produto());
            ps.setDate(3, new java.sql.Date(movimentacao.getData_movimentacao().getTime()));
            ps.setInt(4, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                movimentacao.setId_movimentacao(id);
                return movimentacao;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a movimentação no banco de dados: " + e.getMessage());
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

    
    public static boolean delete(int id) {
        String sql = "DELETE FROM movimentacao WHERE id_movimentacao = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar a movimentação do banco de dados: " + e.getMessage());
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

        return false;
    }

}
