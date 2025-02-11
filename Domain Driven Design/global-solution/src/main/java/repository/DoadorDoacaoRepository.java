package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DoadorDoacao;

public class DoadorDoacaoRepository extends Repository {

    public static DoadorDoacao create(DoadorDoacao doadorDoacao) {
        String sql = "INSERT INTO doador_doacao (id_doador, id_doacao) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, doadorDoacao.getIdDoador());
            ps.setInt(2, doadorDoacao.getIdDoacao());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return doadorDoacao;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o relacionamento doador_doacao no banco de dados: " + e.getMessage());
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

    public static List<DoadorDoacao> findAll() {
        String sql = "SELECT * FROM doador_doacao";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            List<DoadorDoacao> doadorDoacoes = new ArrayList<>();

            while (rs.next()) {
                DoadorDoacao doadorDoacao = new DoadorDoacao();
                doadorDoacao.setIdDoador(rs.getInt("id_doador"));
                doadorDoacao.setIdDoacao(rs.getInt("id_doacao"));

                doadorDoacoes.add(doadorDoacao);
            }

            if (doadorDoacoes.isEmpty()) {
                System.out.println("Não foram encontrados registros na tabela doador_doacao");
            }

            return doadorDoacoes;
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar a listagem de relacionamentos doador_doacao: " + e.getMessage());
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

    public static DoadorDoacao findByIds(int idDoador, int idDoacao) {
        String sql = "SELECT * FROM doador_doacao WHERE id_doador = ? AND id_doacao = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idDoador);
            ps.setInt(2, idDoacao);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.isBeforeFirst()) {
                    DoadorDoacao doadorDoacao = new DoadorDoacao();

                    while (rs.next()) {
                        doadorDoacao.setIdDoador(rs.getInt("id_doador"));
                        doadorDoacao.setIdDoacao(rs.getInt("id_doacao"));
                    }

                    return doadorDoacao;
                }
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar este relacionamento doador_doacao: " + e.getMessage());
        }

        return null;
    }

    public static DoadorDoacao update(DoadorDoacao doadorDoacao, int idDoador, int idDoacao) {
        String sql = "UPDATE doador_doacao SET id_doador = ?, id_doacao = ? WHERE id_doador = ? AND id_doacao = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, doadorDoacao.getIdDoador());
            ps.setInt(2, doadorDoacao.getIdDoacao());
            ps.setInt(3, idDoador);
            ps.setInt(4, idDoacao);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return doadorDoacao;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o relacionamento doador_doacao no banco de dados: " + e.getMessage());
        }

        return null;
    }

    public static boolean delete(int idDoador, int idDoacao) {
        DoadorDoacao doadorDoacao = findByIds(idDoador, idDoacao);

        if (doadorDoacao == null) {
            return false;
        }

        String sql = "DELETE FROM doador_doacao WHERE id_doador = ? AND id_doacao = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idDoador);
            ps.setInt(2, idDoacao);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro para deletar o relacionamento doador_doacao no banco de dados: " + e.getMessage());
        }

        return false;
    }
}
