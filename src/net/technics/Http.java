package net.technics;

import java.io.IOException;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.Groupe;
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

}