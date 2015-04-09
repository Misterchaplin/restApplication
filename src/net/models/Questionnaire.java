package net.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Questionnaire {
	private Integer id;
	private String libelle;
	private Integer domaine_id;
//	private Integer questionnaire_id;

	private String date;
	private boolean isSelected;
	
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
	
	/*public Integer getQuestionnaire_id() {
		return questionnaire_id;
	}
	public void setQuestionnaire_id(Integer questionnaire_id) {
		this.questionnaire_id = questionnaire_id;
	}*/
	public String getDate() {
		return date;
	}
	public void setDate(String string) {
		this.date = string;
	}
	
	@Override
	public String toString() {
		return libelle;
	}
	
}
