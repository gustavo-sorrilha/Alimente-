package model;

import java.util.Objects;

public class DoadorDoacao {
    private int idDoador;
    private int idDoacao;

    public DoadorDoacao() {
    }

    public DoadorDoacao(int idDoador, int idDoacao) {
        this.idDoador = idDoador;
        this.idDoacao = idDoacao;
    }

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
    }

    public int getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(int idDoacao) {
        this.idDoacao = idDoacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoadorDoacao that = (DoadorDoacao) o;
        return idDoador == that.idDoador && idDoacao == that.idDoacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoador, idDoacao);
    }

    @Override
    public String toString() {
        return "DoadorDoacao{" +
                "idDoador=" + idDoador +
                ", idDoacao=" + idDoacao +
                '}';
    }
}
