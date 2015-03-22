package net.models;

public class Groupe {
	private Integer id;
	private String libelle;
	private Integer code;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Groupe [id=" + id + ", libelle=" + libelle + ", code=" + code
				+ "]";
	}
	
	
}
