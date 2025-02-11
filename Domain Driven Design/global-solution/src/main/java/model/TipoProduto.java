package model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TipoProduto {
    private int id_tipo_produto;

    @NotBlank(message = "A medida não pode estar em branco.")
    private String medida;

    public TipoProduto() {
        super();
    }

    public TipoProduto(int id_tipo_produto, @NotBlank(message = "A medida não pode estar em branco.") String medida) {
        super();
        this.id_tipo_produto = id_tipo_produto;
        this.medida = medida;
    }

    public int getId_tipo_produto() {
        return id_tipo_produto;
    }

    public void setId_tipo_produto(int id_tipo_produto) {
        this.id_tipo_produto = id_tipo_produto;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_tipo_produto, medida);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TipoProduto other = (TipoProduto) obj;
        return id_tipo_produto == other.id_tipo_produto && Objects.equals(medida, other.medida);
    }

    @Override
    public String toString() {
        return "TipoProduto [id_tipo_produto=" + id_tipo_produto + ", medida=" + medida + "]";
    }
}
