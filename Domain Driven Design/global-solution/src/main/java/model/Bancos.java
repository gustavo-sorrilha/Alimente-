package model;

import java.util.Objects;

public class Bancos {
	private String ispb;
	private String name;
	private int code;
	private String fullName;
	
	public Bancos() {		
	}

	public Bancos(String ispb, String name, int code, String fullName) {
		super();
		this.ispb = ispb;
		this.name = name;
		this.code = code;
		this.fullName = fullName;
	}
	
	public String getIspb() {
		return ispb;
	}

	public void setIspb(String ispb) {
		this.ispb = ispb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Bancos [ispb=" + ispb + ", name=" + name + ", code=" + code + ", fullName=" + fullName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, fullName, ispb, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bancos other = (Bancos) obj;
		return code == other.code && Objects.equals(fullName, other.fullName) && Objects.equals(ispb, other.ispb)
				&& Objects.equals(name, other.name);
	}

}
