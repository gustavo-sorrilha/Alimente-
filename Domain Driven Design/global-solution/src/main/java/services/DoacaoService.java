package services;

import java.util.List;

import model.Doacao;
import repository.DoacaoRepository;

public class DoacaoService {

    public static boolean exists(int id) {
        return DoacaoRepository.findById(id) != null;
    }

    public static Doacao create(Doacao doacao) {
        return DoacaoRepository.create(doacao);
    }

    public static List<Doacao> findAll() {
        return DoacaoRepository.findAll();
    }

    public static Doacao findById(int id) {
        return DoacaoRepository.findById(id);
    }

    public static Doacao update(int id, Doacao doacao) {
        boolean doacaoExiste = DoacaoService.findById(id) != null;
        Doacao doacaoAtualizada = null;

        if (doacaoExiste) {
            doacaoAtualizada = DoacaoRepository.update(doacao, id);
            doacaoAtualizada.setId_doacao(id);

            return doacaoAtualizada;
        } else {
            return null;
        }
    }

    public static boolean delete(int id) {
        if (exists(id)) {
            return DoacaoRepository.delete(id);
        } else {
            return false;
        }
    }
}
