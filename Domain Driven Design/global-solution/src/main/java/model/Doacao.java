package model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Doacao extends Cliente {
    private int id_doacao;

    @NotBlank(message = "A imagem não pode estar em branco.")
    private String imagem;

    @NotBlank(message = "A descrição não pode estar em branco.")
    private String descricao;

    @NotNull(message = "A disponibilidade é obrigatória.")
    private int disponibilidade;

    public Doacao() {
        super();
    }

    public Doacao(int id_doacao, @NotBlank(message = "A imagem não pode estar em branco.") String imagem,
            @NotBlank(message = "A descrição não pode estar em branco.") String descricao,
            @NotNull(message = "A disponibilidade é obrigatória.") int disponibilidade) {
        super();
        this.id_doacao = id_doacao;
        this.imagem = imagem;
        this.descricao = descricao;
        this.disponibilidade = disponibilidade;
    }

    public int getId_doacao() {
        return id_doacao;
    }

    public void setId_doacao(int id_doacao) {
        this.id_doacao = id_doacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, disponibilidade, id_doacao, imagem);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Doacao other = (Doacao) obj;
        return Objects.equals(descricao, other.descricao) && disponibilidade == other.disponibilidade
                && id_doacao == other.id_doacao && Objects.equals(imagem, other.imagem);
    }

    @Override
    public String toString() {
        return "Doacao [id_doacao=" + id_doacao + ", imagem=" + imagem + ", descricao=" + descricao
                + ", disponibilidade=" + disponibilidade + "]";
    }
}
