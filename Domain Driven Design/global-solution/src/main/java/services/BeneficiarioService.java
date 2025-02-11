package services;

import java.util.List;

import model.Beneficiario;
import repository.BeneficiarioRepository;

public class BeneficiarioService {

    public static boolean exists(int id) {
        return BeneficiarioRepository.findById(id) != null;
    }

    public static Beneficiario create(Beneficiario beneficiario) {
        return BeneficiarioRepository.create(beneficiario);
    }

    public static List<Beneficiario> findAll() {
        return BeneficiarioRepository.findAll();
    }

    public static Beneficiario findById(int id) {
        return BeneficiarioRepository.findById(id);
    }

    public static Beneficiario update(int id, Beneficiario beneficiario) {
        boolean beneficiarioExists = BeneficiarioService.findById(id) != null;
        Beneficiario beneficiarioAtualizado = null;

        if (beneficiarioExists) {
            beneficiarioAtualizado = BeneficiarioRepository.update(beneficiario, id);
            beneficiarioAtualizado.setId_cliente(id);

            return beneficiarioAtualizado;
        } else {
            return null;
        }
    }

    public static boolean delete(int id) {
        if (exists(id)) {
            return BeneficiarioRepository.delete(id);
        } else {
            return false;
        }
    }
}
