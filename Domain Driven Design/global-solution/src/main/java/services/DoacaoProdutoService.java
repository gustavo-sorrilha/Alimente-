package services;

import java.util.List;

import model.DoacaoProduto;
import repository.DoacaoProdutoRepository;

public class DoacaoProdutoService {
    public static DoacaoProduto create(DoacaoProduto doacaoProduto) {
        return DoacaoProdutoRepository.create(doacaoProduto);
    }

    public static List<DoacaoProduto> findAll() {
        return DoacaoProdutoRepository.findAll();
    }

    public static boolean delete(int idDoacao, int idProduto) {
        return DoacaoProdutoRepository.delete(idDoacao, idProduto);
    }
    
    public static boolean exists(int idDoacao, int idProduto) {
        return DoacaoProdutoRepository.findById(idDoacao, idProduto) != null;
    }
    
    public static DoacaoProduto findById(int idDoacao, int idProduto) {
        return DoacaoProdutoRepository.findById(idDoacao, idProduto);
    }
    
    public static DoacaoProduto update(int idDoacao, int idProduto, DoacaoProduto doacaoProduto) {
    	System.out.println("ID DOACAO: " + idDoacao + " ID PRODUTO: " + idProduto + " DADOS BODY: " + doacaoProduto + " SERVICE");
        boolean doacaoProdutoExists = DoacaoProdutoService.exists(idDoacao, idProduto);
        
        if (doacaoProdutoExists) {
            return DoacaoProdutoRepository.update(idDoacao, idProduto, doacaoProduto);
        } else {
            return null;
        }
    }


}
