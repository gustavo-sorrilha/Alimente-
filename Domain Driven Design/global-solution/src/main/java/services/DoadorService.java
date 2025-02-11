package services;

import java.util.List;

import model.Doador;
import repository.DoadorRepository;

public class DoadorService {

    public static boolean exists(int id) {
        return DoadorRepository.findById(id) != null;
    }

    public static Doador create(Doador doador) {
        return DoadorRepository.create(doador);
    }

    public static List<Doador> findAll() {
        return DoadorRepository.findAll();
    }

    public static Doador findById(int id) {
        return DoadorRepository.findById(id);
    }

    public static Doador update(int id, Doador doador) {
        boolean doadorExists = DoadorService.findById(id) != null;
        Doador updatedDoador = null;

        if (doadorExists) {
            updatedDoador = DoadorRepository.update(doador, id);
            updatedDoador.setId_cliente(id);

            return updatedDoador;
        } else {
            return null;
        }
    }

    public static boolean delete(int id) {
        if (exists(id)) {
            return DoadorRepository.delete(id);
        } else {
            return false;
        }
    }
}
