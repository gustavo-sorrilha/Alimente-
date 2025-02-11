package services;

import java.util.List;

import model.Movimentacao;
import repository.MovimentacaoRepository;

public class MovimentacaoService {

    public static boolean exists(int id) {
        return MovimentacaoRepository.findById(id) != null;
    }

    public static Movimentacao create(Movimentacao movimentacao) {
        return MovimentacaoRepository.create(movimentacao);
    }

    public static List<Movimentacao> findAll() {
        return MovimentacaoRepository.findAll();
    }

    public static Movimentacao findById(int id) {
        return MovimentacaoRepository.findById(id);
    }

    public static Movimentacao update(int id, Movimentacao movimentacao) {
        boolean movimentacaoExiste = MovimentacaoService.findById(id) != null;
        Movimentacao movimentacaoAtualizada = null;

        if (movimentacaoExiste) {
            movimentacaoAtualizada = MovimentacaoRepository.update(movimentacao, id);
            movimentacaoAtualizada.setId_movimentacao(id);

            return movimentacaoAtualizada;
        } else {
            return null;
        }
    }

    public static boolean delete(int id) {
        if (exists(id)) {
            return MovimentacaoRepository.delete(id);
        } else {
            return false;
        }
    }
}
