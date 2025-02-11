package repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import model.TipoProduto;

public class TipoProdutoRepository extends Repository {

	public static TipoProduto create(@Valid TipoProduto tipoProduto) {
	    String sql = "DECLARE v_id_tipo_produto NUMBER; BEGIN INSERT INTO tipo_produto (id_tipo_produto, medida) VALUES (SQ_TIPO_PRODUTO.nextval, ?) RETURNING id_tipo_produto INTO v_id_tipo_produto; ? := v_id_tipo_produto; END;";

	    Connection connection = null;
	    CallableStatement cs = null;

	    try {
	        connection = getConnection();
	        cs = connection.prepareCall(sql);

	        cs.setString(1, tipoProduto.getMedida());
	        cs.registerOutParameter(2, java.sql.Types.INTEGER);
	        cs.executeUpdate();
	        tipoProduto.setId_tipo_produto(cs.getInt(2));

	        return tipoProduto;

	    } catch (SQLException e) {
	        System.out.println("Erro ao salvar o tipo de produto no banco de dados: " + e.getMessage());
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



    public static List<TipoProduto> findAll() {
        String sql = "SELECT * FROM tipo_produto";

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoProduto> tiposProduto = new ArrayList<>();

        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoProduto tipoProduto = new TipoProduto();
                tipoProduto.setId_tipo_produto(rs.getInt("id_tipo_produto"));
                tipoProduto.setMedida(rs.getString("medida"));
                tiposProduto.add(tipoProduto);
            }

            if (tiposProduto.isEmpty())
                System.out.println("Não foram encontrados registros na tabela tipo_produto");

        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a listagem de tipos de produto: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o Prepared Statement: " + e.getMessage());
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

        return tiposProduto;
    }

    public static TipoProduto findById(int id) {
        String sql = "SELECT * FROM tipo_produto WHERE id_tipo_produto = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                TipoProduto tipoProduto = new TipoProduto();

                while (rs.next()) {
                    tipoProduto.setId_tipo_produto(rs.getInt("id_tipo_produto"));
                    tipoProduto.setMedida(rs.getString("medida"));
                }

                return tipoProduto;
            }

        } catch (SQLException e) {
            System.out.println("Não foi possível consultar este tipo de produto: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o Prepared Statement: " + e.getMessage());
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

    public static TipoProduto update(@Valid TipoProduto tipoProduto, int id) {
        String sql = "UPDATE tipo_produto SET medida = ? WHERE id_tipo_produto = ?";

        PreparedStatement ps = null;

        try {
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, tipoProduto.getMedida());
            ps.setInt(2, id);
            ps.executeUpdate();

            return tipoProduto;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tipo de produto no banco de dados: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o Prepared Statement: " + e.getMessage());
                }
            }
        }

        return null;
    }

    public static boolean delete(int id) {
        String sql = "DELETE FROM tipo_produto WHERE id_tipo_produto = ?";

        PreparedStatement ps = null;

        try {
            ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar tipo de produto no banco de dados: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o Prepared Statement: " + e.getMessage());
                }
            }
        }

        return false;
    }
}
