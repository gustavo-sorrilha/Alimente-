package services;

import java.util.List;

import model.DoadorDoacao;
import repository.DoadorDoacaoRepository;

public class DoadorDoacaoService {

    public static boolean exists(int idDoador, int idDoacao) {
        return DoadorDoacaoRepository.findByIds(idDoador, idDoacao) != null;
    }

    public static DoadorDoacao create(DoadorDoacao doadorDoacao) {
        return DoadorDoacaoRepository.create(doadorDoacao);
    }

    public static List<DoadorDoacao> findAll() {
        return DoadorDoacaoRepository.findAll();
    }

    public static DoadorDoacao findByIds(int idDoador, int idDoacao) {
        return DoadorDoacaoRepository.findByIds(idDoador, idDoacao);
    }

    public static DoadorDoacao update(int idDoador, int idDoacao, DoadorDoacao doadorDoacao) {
        boolean doadorDoacaoExists = DoadorDoacaoService.findByIds(idDoador, idDoacao) != null;
        DoadorDoacao doadorDoacaoAtualizado = null;

        if (doadorDoacaoExists) {
            doadorDoacaoAtualizado = DoadorDoacaoRepository.update(doadorDoacao, idDoador, idDoacao);
            doadorDoacaoAtualizado.setIdDoador(idDoador);
            doadorDoacaoAtualizado.setIdDoacao(idDoacao);

            return doadorDoacaoAtualizado;
        } else {
            return null;
        }
    }

    public static boolean delete(int idDoador, int idDoacao) {
        if (exists(idDoador, idDoacao)) {
            return DoadorDoacaoRepository.delete(idDoador, idDoacao);
        } else {
            return false;
        }
    }
}
