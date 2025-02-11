package services;

import java.util.List;

import model.Selo;
import repository.SeloRepository;

public class SeloService {

    public static boolean exists(int id) {
        return SeloRepository.findById(id) != null;
    }

    public static Selo create(Selo selo) {
        return SeloRepository.create(selo);
    }

    public static List<Selo> findAll() {
        return SeloRepository.findAll();
    }

    public static Selo findById(int id) {
        return SeloRepository.findById(id);
    }

    public static Selo update(int id, Selo selo) {
        boolean seloExists = SeloService.findById(id) != null;
        Selo seloAtualizado = null;

        if (seloExists) {
            seloAtualizado = SeloRepository.update(selo, id);
            seloAtualizado.setId_selo(id);

            return seloAtualizado;
        } else {
            return null;
        }
    }

    public static boolean delete(int id) {
        if (exists(id)) {
            return SeloRepository.delete(id);
        } else {
            return false;
        }
    }
}
