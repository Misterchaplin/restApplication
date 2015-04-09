package net.models;

public class Reponse {
	private Integer id;
	private String libelle;
	private Integer question_id;
	private Boolean good;
	
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
	public Integer getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
	public Boolean getGood() {
		return good;
	}
	public void setGood(Boolean good) {
		this.good = good;
	}
	
	@Override
	public String toString() {
		return "Reponse [id=" + id + ", libelle=" + libelle + ", question_id="
				+ question_id + ", good=" + good + "]";
	}
	
	
	
}
