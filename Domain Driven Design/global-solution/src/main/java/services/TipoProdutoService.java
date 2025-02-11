package services;

import java.util.List;

import model.TipoProduto;
import repository.TipoProdutoRepository;

public class TipoProdutoService {

    public static TipoProduto create(TipoProduto tipoProduto) {
        return TipoProdutoRepository.create(tipoProduto);
    }

    public static List<TipoProduto> findAll() {
        return TipoProdutoRepository.findAll();
    }

    public static TipoProduto findById(int id) {
        return TipoProdutoRepository.findById(id);
    }

    public static TipoProduto update(int id, TipoProduto tipoProduto) {
        boolean tipoProdutoExiste = TipoProdutoService.findById(id) != null;
        TipoProduto tipoProdutoAtualizado = null;

        if (tipoProdutoExiste) {
            tipoProdutoAtualizado = TipoProdutoRepository.update(tipoProduto, id);
            tipoProdutoAtualizado.setId_tipo_produto(id);

            return tipoProdutoAtualizado;
        } else {
            return null;
        }
    }

    public static boolean delete(int id) {
        if (TipoProdutoService.findById(id) != null) {
            return TipoProdutoRepository.delete(id);
        } else {
            return false;
        }
    }
}
