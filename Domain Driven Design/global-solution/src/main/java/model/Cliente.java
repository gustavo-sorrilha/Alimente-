package model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Cliente {
	private int id_cliente;
	
	@NotBlank(message = "O nome não pode estar em vazio.")
	private String nome;
	
	@NotNull(message = "O email é obrigatório")
	private String email;
	
	@NotNull(message = "A senha é obrigatória")
	private String senha;
	
	@NotNull(message = "O termo é obrigatório")
	private int termo;

	public Cliente() {
		super();
	}
	
	public Cliente(int id_cliente, @NotBlank(message = "O nome não pode estar em vazio.") String nome,
			@NotNull(message = "O email é obrigatório") String email,
			@NotNull(message = "A senha é obrigatória") String senha,
			@NotNull(message = "O termo é obrigatório") int termo) {
		super();
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.termo = termo;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTermo() {
		return termo;
	}

	public void setTermo(int termo) {
		this.termo = termo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id_cliente, nome, senha, termo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(email, other.email) && id_cliente == other.id_cliente && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha) && termo == other.termo;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", termo=" + termo + "]";
	}
}
