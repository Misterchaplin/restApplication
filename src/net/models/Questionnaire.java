package net.models;

import java.util.Date;

public class Questionnaire {
	private Integer id;
	private String libelle;
	private Integer domaine_id;
	private Integer utilisateur_id;
	private Date date;
	
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
	public Integer getDomaine_id() {
		return domaine_id;
	}
	public void setDomaine_id(Integer domaine_id) {
		this.domaine_id = domaine_id;
	}
	public Integer getUtilisateur_id() {
		return utilisateur_id;
	}
	public void setUtilisateur_id(Integer utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Questionnaire [id=" + id + ", libelle=" + libelle
				+ ", domaine_id=" + domaine_id + ", utilisateur_id="
				+ utilisateur_id + ", date=" + date + "]";
	}
	
}
