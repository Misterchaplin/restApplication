package net.models;

public class Utilisateur {
	private Integer id;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private Integer rang_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Integer getRang_id() {
		return rang_id;
	}
	public void setRang_id(Integer rang_id) {
		this.rang_id = rang_id;
	}
	
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", password="
				+ password + ", nom=" + nom + ", prenom=" + prenom
				+ ", rang_id=" + rang_id + "]";
	}
	
	
}
