package repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import model.Produto;

public class ProdutoRepository extends Repository {
	public static Produto create(@Valid Produto produto) {
	    String sql = "INSERT INTO produto (id_produto, nome_produto, quantidade, imagem_produto, data_validade, id_tipo_produto) VALUES (SQ_PRODUTO.nextval, ?, ?, ?, ?, ?)";

	    Connection connection = null;
	    PreparedStatement ps = null;

	    try {
	        connection = getConnection();
	        ps = connection.prepareStatement(sql, new String[] { "id_produto" });

	        ps.setString(1, produto.getNome_produto());
	        ps.setInt(2, produto.getQuantidade());
	        ps.setString(3, produto.getImagem_produto());
	        ps.setDate(4, new java.sql.Date(produto.getData_validade().getTime()));
	        ps.setInt(5, produto.getId_tipo_produto());

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                produto.setId_produto(generatedKeys.getInt(1));
	            }
	            return produto;
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao salvar o produto no banco de dados: " + e.getMessage());
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


    public static List<Produto> findAll() {
        String sql = "SELECT * FROM produto";

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome_produto(rs.getString("nome_produto"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setImagem_produto(rs.getString("imagem_produto"));
                produto.setData_validade(rs.getDate("data_validade"));
                produto.setId_tipo_produto(rs.getInt("id_tipo_produto"));
                produtos.add(produto);
            }

            if (produtos.isEmpty())
                System.out.println("Não foram encontrados registros na tabela produto");

        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a listagem de produtos: " + e.getMessage());
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
                } catch (Exception e2) {
                    System.out.println("Erro ao fechar o ResultSet: " + e2.getMessage());
                }
            }
        }

        return produtos;
    }

    public static Produto findById(int id) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                Produto produto = new Produto();

                while (rs.next()) {
                    produto.setId_produto(rs.getInt("id_produto"));
                    produto.setNome_produto(rs.getString("nome_produto"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setImagem_produto(rs.getString("imagem_produto"));
                    produto.setData_validade(rs.getDate("data_validade"));
                    produto.setId_tipo_produto(rs.getInt("id_tipo_produto"));
                }

                return produto;
            }

        } catch (SQLException e) {
            System.out.println("Não foi possível consultar este produto: " + e.getMessage());
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
                } catch (Exception e2) {
                    System.out.println("Erro ao fechar o ResultSet: " + e2.getMessage());
                }
            }
        }

        return null;
    }

    public static Produto update(@Valid Produto produto, int id) {
        String sql = "UPDATE produto SET nome_produto = ?, quantidade = ?, imagem_produto = ?, data_validade = ?, id_tipo_produto = ? WHERE id_produto = ?";

        PreparedStatement ps = null;

        try {
            ps = getConnection().prepareStatement(sql);

            ps.setString(1, produto.getNome_produto());
            ps.setInt(2, produto.getQuantidade());
            ps.setString(3, produto.getImagem_produto());
            ps.setDate(4, new java.sql.Date(produto.getData_validade().getTime()));
            ps.setInt(5, produto.getId_tipo_produto());
            ps.setInt(6, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return produto;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto no banco de dados: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
                }
            }
        }

        return null;
    }

    public static boolean delete(int produtoId) {
        Produto produto = null;
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        PreparedStatement ps = null;

        produto = findById(produtoId);

        if (produto == null)
            return false;

        try {
            ps = getConnection().prepareStatement(sql);

            ps.setInt(1, produtoId);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro para deletar o produto no banco de dados: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
                }
            }
        }

        return false;
    }
}
