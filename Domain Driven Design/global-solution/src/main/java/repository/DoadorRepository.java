package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import model.Doador;

public class DoadorRepository extends Repository {
    public static Doador create(Doador doador) {
        Connection connection = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO doador (id_cliente, cnpj, telefone, endereco, quantidade_doada) VALUES (?, ?, ?, ?, ?)";

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, doador.getId_cliente());
            ps.setString(2, doador.getCnpj());
            ps.setString(3, doador.getTelefone());
            ps.setString(4, doador.getEndereco());
            ps.setInt(5, doador.getQuantidade_doada());

            ps.executeUpdate();

            return doador;
        } catch (SQLException e) {
            System.out.println("Erro ao criar doador no banco de dados: " + e.getMessage());
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

    public static List<Doador> findAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM cliente JOIN doador ON cliente.id_cliente = doador.id_cliente";

        List<Doador> doadores = new ArrayList<>();

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Doador doador = new Doador();
                doador.setId_cliente(rs.getInt("id_cliente"));
                doador.setCnpj(rs.getString("cnpj"));
                doador.setTelefone(rs.getString("telefone"));
                doador.setEndereco(rs.getString("endereco"));
                doador.setQuantidade_doada(rs.getInt("quantidade_doada"));
                doador.setNome(rs.getString("nome"));
                doador.setEmail(rs.getString("email"));
                doador.setSenha(rs.getString("senha"));
                doador.setTermo(rs.getInt("termo"));
                doadores.add(doador);
            }

            if (doadores.isEmpty())
                System.out.println("Não foram encontrados registros na tabela doador");
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a listagem de doadores: " + e.getMessage());
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
                    System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
                }
            }
        }

        return doadores;
    }

    public static Doador findById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM cliente JOIN doador ON cliente.id_cliente = doador.id_cliente WHERE cliente.id_cliente = ?";
        

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Doador doador = new Doador();
                doador.setId_cliente(rs.getInt("id_cliente"));
                doador.setCnpj(rs.getString("cnpj"));
                doador.setTelefone(rs.getString("telefone"));
                doador.setEndereco(rs.getString("endereco"));
                doador.setQuantidade_doada(rs.getInt("quantidade_doada"));
                doador.setNome(rs.getString("nome"));
                doador.setEmail(rs.getString("email"));
                doador.setSenha(rs.getString("senha"));
                doador.setTermo(rs.getInt("termo"));
                return doador;
            }

        } catch (SQLException e) {
            System.out.println("Não foi possível consultar o doador: " + e.getMessage());
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
                    System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
                }
            }
        }

        return null;
    }

    public static Doador update(Doador doador, int id) {
        Connection connection = null;
        PreparedStatement ps = null;

        String sql = "UPDATE doador SET cnpj = ?, telefone = ?, endereco = ?, quantidade_doada = ? WHERE id_cliente = ?";

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, doador.getCnpj());
            ps.setString(2, doador.getTelefone());
            ps.setString(3, doador.getEndereco());
            ps.setInt(4, doador.getQuantidade_doada());
            ps.setInt(5, id);

            ps.executeUpdate();

            return doador;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar doador no banco de dados: " + e.getMessage());
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

    public static boolean delete(int clienteId) {
        Connection connection = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM doador WHERE id_cliente = ?";

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, clienteId);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o doador no banco de dados: " + e.getMessage());
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
