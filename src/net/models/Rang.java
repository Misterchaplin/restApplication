package net.models;

public class Rang {
	private Integer id;
	private String libelle;
	
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
	
	@Override
	public String toString() {
		return "Rang [id=" + id + ", libelle=" + libelle + "]";
	}
	
	
}
