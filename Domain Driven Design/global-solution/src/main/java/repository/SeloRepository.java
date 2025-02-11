package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import model.Selo;

public class SeloRepository extends Repository {

    public static Selo create(@Valid Selo selo) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO selo (id_selo, id_doador) VALUES (SQ_SELO.nextval, ?)";
            ps = connection.prepareStatement(sql, new String[] { "id_selo" });
            ps.setInt(1, selo.getId_doador());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    selo.setId_selo(generatedKeys.getInt(1));
                }
                return selo;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o selo no banco de dados: " + e.getMessage());
        } finally {
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
                }
            }
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
                    System.out.println("Erro ao fechar a Connection: " + e.getMessage());
                }
            }
        }
        return null;
    }

    public static List<Selo> findAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM selo";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            List<Selo> selos = new ArrayList<>();
            while (rs.next()) {
                Selo selo = new Selo();
                selo.setId_selo(rs.getInt("id_selo"));
                selo.setId_doador(rs.getInt("id_doador"));
                selos.add(selo);
            }

            if (selos.isEmpty()) {
                System.out.println("Não foram encontrados registros na tabela selo");
            }

            return selos;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a listagem de selos: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
                }
            }
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
                    System.out.println("Erro ao fechar a Connection: " + e.getMessage());
                }
            }
        }
        return null;
    }

    public static Selo findById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM selo WHERE id_selo = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Selo selo = new Selo();
                selo.setId_selo(rs.getInt("id_selo"));
                selo.setId_doador(rs.getInt("id_doador"));
                return selo;
            }

            System.out.println("Selo não encontrado com o ID: " + id);
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar o selo: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
                }
            }
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
                    System.out.println("Erro ao fechar a Connection: " + e.getMessage());
                }
            }
        }
        return null;
    }

    public static Selo update(Selo selo, int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            String sql = "UPDATE selo SET id_doador = ? WHERE id_selo = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, selo.getId_doador());
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return selo;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar selo no banco de dados: " + e.getMessage());
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
                    System.out.println("Erro ao fechar a Connection: " + e.getMessage());
                }
            }
        }
        return null;
    }

    public static boolean delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            String sql = "DELETE FROM selo WHERE id_selo = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o selo no banco de dados: " + e.getMessage());
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
                    System.out.println("Erro ao fechar a Connection: " + e.getMessage());
                }
            }
        }
        return false;
    }
}
