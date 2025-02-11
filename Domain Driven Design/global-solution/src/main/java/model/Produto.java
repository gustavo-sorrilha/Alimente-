package model;

import java.util.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;

public class Produto {
    private int id_produto;
    
    @NotBlank(message = "O nome do produto não pode estar vazio.")
    private String nome_produto;
    
    @NotNull(message = "A quantidade é obrigatória.")
    private int quantidade;
    
    @NotBlank(message = "A URL da imagem do produto não pode estar vazia.")
    private String imagem_produto;
    
    @NotNull(message = "A data de validade é obrigatória.")
    private Date data_validade;
    
    @NotNull(message = "O ID do tipo de produto é obrigatório.")
    private int id_tipo_produto;

    public Produto() {
        super();
    }

    public Produto(int id_produto, @NotBlank(message = "O nome do produto não pode estar vazio.") String nome_produto,
            @NotNull(message = "A quantidade é obrigatória.") int quantidade,
            @NotBlank(message = "A URL da imagem do produto não pode estar vazia.") String imagem_produto,
            @NotNull(message = "A data de validade é obrigatória.")
    		@NotNull(message = "A data de validade é obrigatória.") Date data_validade,
            @NotNull(message = "O ID do tipo de produto é obrigatório.") int id_tipo_produto) {
        super();
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.quantidade = quantidade;
        this.imagem_produto = imagem_produto;
        this.data_validade = data_validade;
        this.id_tipo_produto = id_tipo_produto;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getImagem_produto() {
        return imagem_produto;
    }

    public void setImagem_produto(String imagem_produto) {
        this.imagem_produto = imagem_produto;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public int getId_tipo_produto() {
        return id_tipo_produto;
    }

    public void setId_tipo_produto(int id_tipo_produto) {
        this.id_tipo_produto = id_tipo_produto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data_validade, id_produto, id_tipo_produto, imagem_produto, nome_produto, quantidade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        return Objects.equals(data_validade, other.data_validade) && id_produto == other.id_produto
                && id_tipo_produto == other.id_tipo_produto && Objects.equals(imagem_produto, other.imagem_produto)
                && Objects.equals(nome_produto, other.nome_produto) && quantidade == other.quantidade;
    }

    @Override
    public String toString() {
        return "Produto [id_produto=" + id_produto + ", nome_produto=" + nome_produto + ", quantidade=" + quantidade
                + ", imagem_produto=" + imagem_produto + ", data_validade=" + data_validade + "]";
    }
}
