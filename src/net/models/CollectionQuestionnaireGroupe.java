package net.models;

public class CollectionQuestionnaireGroupe {
	private Integer questionnaire_id;
	private String questionnaire_libelle;
	private Integer questionnaire_domaine_id;
	private String questionnaire_date;
	private Integer groupe_id;
	private String groupe_libelle;
	private String groupe_code;
	
	public String getQuestionnaire_date() {
		return questionnaire_date;
	}
	public void setQuestionnaire_date(String questionnaire_date) {
		this.questionnaire_date = questionnaire_date;
	}
	
	@Override
	public String toString() {
		return questionnaire_libelle + " " + groupe_libelle;
	}
	public Integer getQuestionnaire_id() {
		return questionnaire_id;
	}
	public void setQuestionnaire_id(Integer questionnaire_id) {
		this.questionnaire_id = questionnaire_id;
	}
	public String getQuestionnaire_libelle() {
		return questionnaire_libelle;
	}
	public void setQuestionnaire_libelle(String questionnaire_libelle) {
		this.questionnaire_libelle = questionnaire_libelle;
	}
	public Integer getQuestionnaire_domaine_id() {
		return questionnaire_domaine_id;
	}
	public void setQuestionnaire_domaine_id(Integer questionnaire_domaine_id) {
		this.questionnaire_domaine_id = questionnaire_domaine_id;
	}
	public Integer getGroupe_id() {
		return groupe_id;
	}
	public void setGroupe_id(Integer groupe_id) {
		this.groupe_id = groupe_id;
	}
	public String getGroupe_libelle() {
		return groupe_libelle;
	}
	public void setGroupe_libelle(String groupe_libelle) {
		this.groupe_libelle = groupe_libelle;
	}
	public String getGroupe_code() {
		return groupe_code;
	}
	public void setGroupe_code(String groupe_code) {
		this.groupe_code = groupe_code;
	}
}
