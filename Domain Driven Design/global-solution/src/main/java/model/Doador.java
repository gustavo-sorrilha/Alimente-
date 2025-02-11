package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Doador extends Cliente {
    private int id_cliente;

    @NotBlank(message = "O CNPJ não pode estar em branco.")
    private String cnpj;

    @NotBlank(message = "O telefone não pode estar em branco.")
    private String telefone;

    @NotBlank(message = "O endereço não pode estar em branco.")
    private String endereco;

    @NotNull(message = "A quantidade doada é obrigatória.")
    private int quantidade_doada;

    public Doador() {
    	super();
    }

    public Doador(int id_cliente, @NotBlank(message = "O CNPJ não pode estar em branco.") String cnpj,
                  @NotBlank(message = "O telefone não pode estar em branco.") String telefone,
                  @NotBlank(message = "O endereço não pode estar em branco.") String endereco,
                  @NotNull(message = "A quantidade doada é obrigatória.") int quantidade_doada) {
    	super();
        this.id_cliente = id_cliente;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.quantidade_doada = quantidade_doada;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQuantidade_doada() {
        return quantidade_doada;
    }

    public void setQuantidade_doada(int quantidade_doada) {
        this.quantidade_doada = quantidade_doada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cliente, cnpj, telefone, endereco, quantidade_doada);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Doador doador = (Doador) obj;
        return id_cliente == doador.id_cliente && quantidade_doada == doador.quantidade_doada &&
                Objects.equals(cnpj, doador.cnpj) && Objects.equals(telefone, doador.telefone) &&
                Objects.equals(endereco, doador.endereco);
    }

    @Override
    public String toString() {
        return "Doador{" +
                "id_cliente=" + id_cliente +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", quantidade_doada=" + quantidade_doada +
                '}';
    }
}
