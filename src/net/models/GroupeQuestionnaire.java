package net.models;

public class GroupeQuestionnaire {
	private Integer questionnaire_id;
	private Integer groupe_id;
	
	public Integer getQuestionnaire_id() {
		return questionnaire_id;
	}
	public void setQuestionnaire_id(Integer questionnaire_id) {
		this.questionnaire_id = questionnaire_id;
	}
	public Integer getGroupe_id() {
		return groupe_id;
	}
	public void setGroupe_id(Integer groupe_id) {
		this.groupe_id = groupe_id;
	}
	@Override
	public String toString() {
		return "GroupeQuestionnaire [questionnaire_id=" + questionnaire_id
				+ ", groupe_id=" + groupe_id + "]";
	}
	

}
