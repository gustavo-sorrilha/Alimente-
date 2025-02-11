package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Doacao;
import model.DoacaoProduto;
import model.DoadorDoacao;
import services.DoacaoProdutoService;
import services.DoadorDoacaoService;

public class DoacaoRepository extends Repository {

	public static Doacao create(Doacao doacao) {
		String sql = "INSERT INTO doacao (id_doacao, imagem, descricao, disponibilidade) VALUES (SQ_DOACAO.nextval, ?, ?, ?)";

		PreparedStatement ps = null;

		try {
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, doacao.getImagem());
			ps.setString(2, doacao.getDescricao());
			ps.setInt(3, doacao.getDisponibilidade());

			ps.executeUpdate();

			return doacao;

		} catch (SQLException e) {
			System.out.println("Erro ao salvar a doação no banco de dados: " + e.getMessage());
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

	public static List<Doacao> findAll() {
		String sql = "SELECT d.*, c.*, doador.telefone, doador.endereco FROM doacao d INNER JOIN doador_doacao dd ON d.id_doacao = dd.id_doacao INNER JOIN cliente c ON dd.id_doador = c.id_cliente INNER JOIN doador ON c.id_cliente = doador.id_cliente";

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Doacao> doacoes = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Doacao doacao = new Doacao();
				doacao.setId_doacao(rs.getInt("id_doacao"));
				doacao.setImagem(rs.getString("imagem"));
				doacao.setDescricao(rs.getString("descricao") + " " + rs.getString("endereco"));
				doacao.setDisponibilidade(rs.getInt("disponibilidade"));
				doacao.setNome(rs.getString("nome"));
				doacao.setId_cliente(rs.getInt("id_cliente"));
				doacoes.add(doacao);
			}

			if (doacoes.isEmpty())
				System.out.println("Não foram encontrados registros na tabela doacao");

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem de doações: " + e.getMessage());
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
				} catch (Exception e) {
					System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
				}
			}
		}

		return doacoes;
	}

	public static Doacao findById(int id) {
		String sql = "SELECT d.*, c.*, doador.telefone, doador.endereco FROM doacao d INNER JOIN doador_doacao dd ON d.id_doacao = dd.id_doacao INNER JOIN cliente c ON dd.id_doador = c.id_cliente INNER JOIN doador ON c.id_cliente = doador.id_cliente WHERE dd.id_doacao = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Doacao doacao = new Doacao();

				while (rs.next()) {
					doacao.setId_doacao(rs.getInt("id_doacao"));
					doacao.setImagem(rs.getString("imagem"));
					doacao.setDescricao(rs.getString("descricao") + " " + rs.getString("endereco"));
					doacao.setDisponibilidade(rs.getInt("disponibilidade"));
					doacao.setNome(rs.getString("nome"));
					doacao.setId_cliente(rs.getInt("id_cliente"));
				}

				return doacao;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar esta doação: " + e.getMessage());
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
				} catch (Exception e) {
					System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
				}
			}
		}

		return null;
	}

	public static Doacao update(Doacao doacao, int id) {
		String sql = "UPDATE doacao SET imagem = ?, descricao = ?, disponibilidade = ? WHERE id_doacao = ?";

		PreparedStatement ps = null;

		try {
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, doacao.getImagem());
			ps.setString(2, doacao.getDescricao());
			ps.setInt(3, doacao.getDisponibilidade());
			ps.setInt(4, id);

			ps.executeUpdate();

			return doacao;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar a doação no banco de dados: " + e.getMessage());
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

	public static boolean delete(int id) {

		List<DoadorDoacao> doador_doacao = DoadorDoacaoService.findAll();

		for (DoadorDoacao dd : doador_doacao) {
			if (dd.getIdDoacao() == id) {
				DoadorDoacaoService.delete(dd.getIdDoador(), dd.getIdDoacao());
			}
		}

		List<DoacaoProduto> doacao_produto = DoacaoProdutoService.findAll();

		for (DoacaoProduto dp : doacao_produto) {
			if (dp.getIdDoacao() == id) {
				DoacaoProdutoService.delete(dp.getIdDoacao(), dp.getIdProduto());
			}
		}

		String sql = "DELETE FROM doacao WHERE id_doacao = ?";

		PreparedStatement ps = null;

		try {
			ps = getConnection().prepareStatement(sql);

			ps.setInt(1, id);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir a doação no banco de dados: " + e.getMessage());
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
