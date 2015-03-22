package net.models;

public class GroupeUtilisateur {
	private Integer utilisateur_id;
	private Integer groupe_id;
	
	public Integer getUtilisateur_id() {
		return utilisateur_id;
	}
	public void setUtilisateur_id(Integer utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}
	public Integer getGroupe_id() {
		return groupe_id;
	}
	public void setGroupe_id(Integer groupe_id) {
		this.groupe_id = groupe_id;
	}
	
	@Override
	public String toString() {
		return "GroupeUtilisateur [utilisateur_id=" + utilisateur_id
				+ ", groupe_id=" + groupe_id + "]";
	}
	
	
}
