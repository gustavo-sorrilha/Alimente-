package model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Beneficiario extends Cliente {
    private int id_cliente;

    @NotBlank(message = "O CPF não pode estar em branco.")
    private String cpf;

    @NotNull(message = "A quantidade recebida é obrigatória.")
    private int quantidade_recebida;

    public Beneficiario() {
        super();
    }

    public Beneficiario(int id_cliente, @NotBlank(message = "O CPF não pode estar em branco.") String cpf,
            @NotNull(message = "A quantidade recebida é obrigatória.") int quantidade_recebida) {
        super();
        this.id_cliente = id_cliente;
        this.cpf = cpf;
        this.quantidade_recebida = quantidade_recebida;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getQuantidade_recebida() {
        return quantidade_recebida;
    }

    public void setQuantidade_recebida(int quantidade_recebida) {
        this.quantidade_recebida = quantidade_recebida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, id_cliente, quantidade_recebida);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Beneficiario other = (Beneficiario) obj;
        return Objects.equals(cpf, other.cpf) && id_cliente == other.id_cliente
                && quantidade_recebida == other.quantidade_recebida;
    }

    @Override
    public String toString() {
        return "Beneficiario [id_cliente=" + id_cliente + ", cpf=" + cpf + ", quantidade_recebida="
                + quantidade_recebida + "]";
    }
}
