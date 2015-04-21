package net.gson;

import net.models.ActifUser;
import net.models.Groupe;
import net.models.GroupeQuestionnaire;
import net.models.GroupeUtilisateur;
import net.models.Question;
import net.models.Questionnaire;
import net.models.Reponse;
import net.models.Utilisateur;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGson {
	private Gson gson;
	
	public TestGson(){
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd-MM-yyyy");
		gson= builder.create();
	}
	
	public String objectToJson(Object object){
		return gson.toJson(object);
	}
	
	public Utilisateur jsonToUser(String jsonString){
		return gson.fromJson(jsonString, Utilisateur.class);
	}
	
	public ActifUser jsonToActif(String jsonString){
		return gson.fromJson(jsonString, ActifUser.class);
	}
	
	public Questionnaire jsonToQuestionnaire(String jsonString){
		return gson.fromJson(jsonString, Questionnaire.class);
	}
	
	public Questionnaire[] jsonToAllQuestionnaire(String jsonString){
		return gson.fromJson(jsonString, Questionnaire[].class);
	}
	
	public Groupe jsonToGroupe(String jsonString){
		return gson.fromJson(jsonString, Groupe.class);
	}
	
	public Groupe[] jsonToAllGroupe(String jsonString){
		return gson.fromJson(jsonString, Groupe[].class);
	}
	
	public Utilisateur jsonToUtilisateur(String jsonString){
		return gson.fromJson(jsonString, Utilisateur.class);
	}
	
	public Utilisateur[] jsonToAllUtilisateur(String jsonString){
		return gson.fromJson(jsonString, Utilisateur[].class);
	}
	
	public GroupeQuestionnaire jsonToGroupeQuestionnaire (String jsonString){
		return gson.fromJson(jsonString, GroupeQuestionnaire.class);
	}
	
	public GroupeQuestionnaire[] jsonToAllGroupeQuestionnaire (String jsonString){
		return gson.fromJson(jsonString, GroupeQuestionnaire[].class);
	}
	
	public GroupeUtilisateur jsonToGroupeUtilisateur (String jsonString){
		return gson.fromJson(jsonString, GroupeUtilisateur.class);
	}
	
	public GroupeUtilisateur[] jsonToAllGroupeUtilisateur (String jsonString){
		return gson.fromJson(jsonString, GroupeUtilisateur[].class);
	}
	
	public Question jsonToQuestion(String jsonString){
		return gson.fromJson(jsonString, Question.class);
	}
	
	public Question[] jsonToAllQuestion(String jsonString){
		return gson.fromJson(jsonString, Question[].class);
	}
	
	public Reponse[] jsonToAllReponse(String jsonString){
		return gson.fromJson(jsonString, Reponse[].class);
	}
	
	public Reponse jsonToReponse(String jsonString){
		return gson.fromJson(jsonString, Reponse.class);
	}
	
	public String jsonToString(String jsonString){
		return gson.toJson(jsonString);
	}
	
	
	public static void main(String args[]){
		TestGson test=new TestGson();
		Utilisateur user=new Utilisateur();
		user.setLogin("mymail@mail.fr");
		user.setPassword("John");
		String str=test.objectToJson(user);
		System.out.println(str);
		
		String json="{id:5;name:'Jim',dateCreation:'17/03/2015'}";
		Utilisateur user2=test.jsonToUser(json);
		System.out.println(user2);
	}
}
