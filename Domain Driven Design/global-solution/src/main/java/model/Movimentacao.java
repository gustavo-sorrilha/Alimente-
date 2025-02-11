package model;

import java.util.Date;
import java.util.Objects;

public class Movimentacao {
    private int id_movimentacao;
    private int id_cliente;
    private int id_produto;
    private Date data_movimentacao;

    public Movimentacao() {
    
    }

    public Movimentacao(int id_movimentacao, int id_cliente, int id_produto, Date data_movimentacao) {
        this.id_movimentacao = id_movimentacao;
        this.id_cliente = id_cliente;
        this.id_produto = id_produto;
        this.data_movimentacao = data_movimentacao;
    }

    public int getId_movimentacao() {
        return id_movimentacao;
    }

    public void setId_movimentacao(int id_movimentacao) {
        this.id_movimentacao = id_movimentacao;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public Date getData_movimentacao() {
        return data_movimentacao;
    }

    public void setData_movimentacao(Date data_movimentacao) {
        this.data_movimentacao = data_movimentacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data_movimentacao, id_cliente, id_movimentacao, id_produto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movimentacao other = (Movimentacao) obj;
        return Objects.equals(data_movimentacao, other.data_movimentacao) && id_cliente == other.id_cliente
                && id_movimentacao == other.id_movimentacao && id_produto == other.id_produto;
    }

    @Override
    public String toString() {
        return "Movimentacao [id_movimentacao=" + id_movimentacao + ", id_cliente=" + id_cliente + ", id_produto="
                + id_produto + ", data_movimentacao=" + data_movimentacao + "]";
    }
}
