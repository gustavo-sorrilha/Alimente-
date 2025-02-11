package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DoacaoProduto;

public class DoacaoProdutoRepository extends Repository {
    public static DoacaoProduto create(DoacaoProduto doacaoProduto) {
        String sql = "INSERT INTO doacao_produto (id_doacao, id_produto) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, doacaoProduto.getIdDoacao());
            ps.setInt(2, doacaoProduto.getIdProduto());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return doacaoProduto;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar a doação de produto no banco de dados: " + e.getMessage());
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
    
    public static DoacaoProduto findById(int idDoacao, int idProduto) {
        String sql = "SELECT * FROM doacao_produto WHERE id_doacao = ? AND id_produto = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idDoacao);
            ps.setInt(2, idProduto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DoacaoProduto doacaoProduto = new DoacaoProduto();
                    doacaoProduto.setIdDoacao(rs.getInt("id_doacao"));
                    doacaoProduto.setIdProduto(rs.getInt("id_produto"));

                    return doacaoProduto;
                }
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a doação de produto: " + e.getMessage());
        }

        return null;
    }


    public static List<DoacaoProduto> findAll() {
        String sql = "SELECT * FROM doacao_produto";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            List<DoacaoProduto> doacaoProdutos = new ArrayList<>();

            while (rs.next()) {
                DoacaoProduto doacaoProduto = new DoacaoProduto();
                doacaoProduto.setIdDoacao(rs.getInt("id_doacao"));
                doacaoProduto.setIdProduto(rs.getInt("id_produto"));

                doacaoProdutos.add(doacaoProduto);
            }

            if (doacaoProdutos.isEmpty()) {
                System.out.println("Não foram encontrados registros na tabela doacao_produto");
            }

            return doacaoProdutos;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a listagem de doações de produto: " + e.getMessage());
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
    
    public static DoacaoProduto update(int idDoacao, int idProduto, DoacaoProduto doacaoProduto) {
    	System.out.println("ID DOACAO: " + idDoacao + " ID PRODUTO: " + idProduto + " DADOS BODY: " + doacaoProduto);
        String sql = "UPDATE doacao_produto SET id_produto = ? WHERE id_doacao = ? AND id_produto = ?";
        
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, doacaoProduto.getIdProduto());
            ps.setInt(2, idDoacao);
            ps.setInt(3, idProduto);
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                doacaoProduto.setIdDoacao(idDoacao);
                doacaoProduto.setIdProduto(doacaoProduto.getIdProduto());
                return doacaoProduto;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a doação de produto no banco de dados: " + e.getMessage());
        }
        
        return null;
    }


    public static boolean delete(int idDoacao, int idProduto) {
        String sql = "DELETE FROM doacao_produto WHERE id_doacao = ? AND id_produto = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idDoacao);
            ps.setInt(2, idProduto);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar a doação de produto no banco de dados: " + e.getMessage());
        }

        return false;
    }
}
