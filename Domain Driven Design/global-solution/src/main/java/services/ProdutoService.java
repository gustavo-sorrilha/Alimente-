package services;

import java.util.List;

import model.Produto;
import repository.ProdutoRepository;

public class ProdutoService {

    public static Produto create(Produto produto) {
        return ProdutoRepository.create(produto);
    }

    public static List<Produto> findAll() {
        return ProdutoRepository.findAll();
    }

    public static Produto findById(int id) {
        return ProdutoRepository.findById(id);
    }

    public static Produto update(int id, Produto produto) {
        boolean produtoExiste = ProdutoService.findById(id) != null;
        Produto produtoAtualizado = null;

        if (produtoExiste) {
            produtoAtualizado = ProdutoRepository.update(produto, id);
            produtoAtualizado.setId_produto(id);

            return produtoAtualizado;
        } else {
            return null;
        }
    }

    public static boolean delete(int id) {
        if (findById(id) != null) {
            return ProdutoRepository.delete(id);
        } else {
            return false;
        }
    }
}
