package model;

import java.util.Objects;

public class DoacaoProduto {
    private int idDoacao;
    private int idProduto;

    public DoacaoProduto() {
    }

    public DoacaoProduto(int idDoacao, int idProduto) {
        this.idDoacao = idDoacao;
        this.idProduto = idProduto;
    }

    public int getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(int idDoacao) {
        this.idDoacao = idDoacao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoacao, idProduto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DoacaoProduto other = (DoacaoProduto) obj;
        return idDoacao == other.idDoacao && idProduto == other.idProduto;
    }

    @Override
    public String toString() {
        return "DoacaoProduto [idDoacao=" + idDoacao + ", idProduto=" + idProduto + "]";
    }
}
