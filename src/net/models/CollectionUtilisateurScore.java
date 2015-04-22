package net.models;

import java.util.Date;

public class CollectionUtilisateurScore {
	private String nom;
	private String prenom;
	private String login;
	private Integer score;
	private Date date;
	@Override
	public String toString() {
		return "CollectionUtilisateurScore [nom=" + nom + ", prenom=" + prenom
				+ ", login=" + login + ", score=" + score + ", date=" + date
				+ "]";
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
