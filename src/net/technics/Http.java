package net.technics;

import java.io.IOException;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.Groupe;
import net.models.GroupeQuestionnaire;
import net.models.GroupeUtilisateur;
import net.models.Questionnaire;
import net.models.Utilisateur;

public class Http{
	private static TestHttp http = new TestHttp();
	private static TestGson gson = new TestGson();
	private static String baseUrl="http://127.0.0.1/rest-QCM/";
	private static String query;
	
	/**
	 * Ensemble des questionnaires
	 * @return
	 */
	public static Questionnaire[] getAllQuestionnaires(){
		try {
			query=http.get(baseUrl+"questionnaires");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Questionnaire[]  data= gson.jsonToAllQuestionnaire(query);
		
		return data;
	}
	
	
	/**
	 * Ensemble des groupes
	 * @return
	 */
	public static Groupe[] getAllGroupes(){
		try {
			query=http.get(baseUrl+"groupes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Groupe[]  data= gson.jsonToAllGroupe(query);
		
		return data;
	}
	
	/**
	 * Ensemble des Groupes du questionnaire
	 * @param id
	 * @return
	 */
	public static Groupe[] getGroupesToQuestionnaire(Integer id){
		try {
			query=http.get(baseUrl+"questionnaires/groupe/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Groupe[] data= gson.jsonToAllGroupe(query);
		
		return data;
	}
	
	/**
	 * Ensemble des groupes appartenant � l'utilisateur
	 * @param id
	 * @return
	 */
	public static Utilisateur[] getUtilisateursToGroupe(Integer id){
		try {
			query=http.get(baseUrl+"groupes/utilisateur/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utilisateur[] data= gson.jsonToAllUtilisateur(query);
		
		return data;
	}
	
	public static ActifUser getConnectUser(Utilisateur user){
		try {
			query=http.postClassic(baseUrl+"user/connect/", user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ActifUser data=gson.jsonToActif(query);
		
		return data;
	}
	
	public static Groupe postGroupe(Groupe groupe){
		try {
			query = http.post(baseUrl+"groupes/", groupe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Groupe data=gson.jsonToGroupe(query);
		
		return data;
	}
	
	public static GroupeQuestionnaire postGroupeQuestionnaires(GroupeQuestionnaire groupequestionnaire){
		try {
			query = http.post(baseUrl+"groupequestionnaires/", groupequestionnaire);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroupeQuestionnaire data=gson.jsonToGroupeQuestionnaire(query);
		
		return data;
	}
	
	public static GroupeUtilisateur postGroupeUtilisateurs(GroupeUtilisateur groupeutilisateur){
		try {
			query = http.post(baseUrl+"groupeutilisateurs/", groupeutilisateur);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroupeUtilisateur data=gson.jsonToGroupeUtilisateur(query);
		
		return data;
	}

}
