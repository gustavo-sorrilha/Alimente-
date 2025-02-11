package model;

import java.util.Objects;

public class Selo {
	private int id_selo;
	private int id_doador;

	public Selo() {
		super();
	}

	public Selo(int id_selo, int id_doador) {
		super();
		this.id_selo = id_selo;
		this.id_doador = id_doador;
	}

	public int getId_selo() {
		return id_selo;
	}

	public void setId_selo(int id_selo) {
		this.id_selo = id_selo;
	}

	public int getId_doador() {
		return id_doador;
	}

	public void setId_doador(int id_doador) {
		this.id_doador = id_doador;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_doador, id_selo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Selo selo = (Selo) obj;
		return id_doador == selo.id_doador && id_selo == selo.id_selo;
	}

	@Override
	public String toString() {
		return "Selo [id_selo=" + id_selo + ", id_doador=" + id_doador + "]";
	}
}
