package net.models;

public class Question {
	private Integer id;
	private String libelle;
	private Integer questionnaire_id;
	
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
	public Integer getQuestionnaire_id() {
		return questionnaire_id;
	}
	public void setQuestionnaire_id(Integer questionnaire_id) {
		this.questionnaire_id = questionnaire_id;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", libelle=" + libelle
				+ ", questionnaire_id=" + questionnaire_id + "]";
	}
	
	
}
