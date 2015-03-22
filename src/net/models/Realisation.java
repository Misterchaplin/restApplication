package net.models;

import java.util.Date;

public class Realisation {
	private Integer id;
	private Integer utilisateur_id;
	private Integer questionnaire_id;
	private Integer score;
	private Date date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUtilisateur_id() {
		return utilisateur_id;
	}
	public void setUtilisateur_id(Integer utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}
	public Integer getQuestionnaire_id() {
		return questionnaire_id;
	}
	public void setQuestionnaire_id(Integer questionnaire_id) {
		this.questionnaire_id = questionnaire_id;
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
	
	@Override
	public String toString() {
		return "Realisation [id=" + id + ", utilisateur_id=" + utilisateur_id
				+ ", questionnaire_id=" + questionnaire_id + ", score=" + score
				+ ", date=" + date + "]";
	}
	
	
}
