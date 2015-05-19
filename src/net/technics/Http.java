package net.technics;

import java.io.IOException;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.Groupe;
import net.models.GroupeQuestionnaire;
import net.models.GroupeUtilisateur;
import net.models.Question;
import net.models.Questionnaire;
import net.models.Realisation;
import net.models.Reponse;
import net.models.Utilisateur;

public class Http{
	private static TestHttp http = new TestHttp();
	private static TestGson gson = new TestGson();
	private static String baseUrl="http://127.0.0.1/rest-QCM/";
	private static String query;
	
	
	/**
	 * R�cup�re le questionnaire suivant son id 
	 * @param id
	 * @return
	 */
	public static Questionnaire getQuestionnaire(Integer id){
		try {
			query=http.get(baseUrl+"questionnaires/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Questionnaire  data= gson.jsonToQuestionnaire(query);
		
		return data;
	}
	
	
	/**
	 * R�cup�re le groupe suivant son id 
	 * @param id
	 * @return
	 */
	public static Groupe getGroupe(Integer id){
		try {
			query=http.get(baseUrl+"groupes/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Groupe  data= gson.jsonToGroupe(query);
		
		return data;
	}
	
	/**
	 * R�cup�re les quesitonnaire suivant l'id du groupe
	 * @param id
	 * @return
	 */
	public static Questionnaire[] getQuestionnaireToGroupe(Integer id){
		try {
			query=http.get(baseUrl+"groupes/questionnaire/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Questionnaire[] data= gson.jsonToAllQuestionnaire(query);
		
		return data;
	}
	
	/**
	 * R�cup�re la question suivant l'id du questionnaire
	 * @param id
	 * @return
	 */
	public static Question[] getQuestionByQuestionnaire(Integer id){
		try {
			query=http.get(baseUrl+"questions/questionbyquestionnaire/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question[]  data= gson.jsonToAllQuestion(query);
		
		return data;
	}
	
	/**
	 * Ensemble des utilisateurs
	 * @return
	 */
	public static Utilisateur[] getAllUtilisateurs(){
		try {
			query=http.get(baseUrl+"utilisateur");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utilisateur[]  data= gson.jsonToAllUtilisateur(query);
		
		return data;
	}
	
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
		Groupe[] data= gson.jsonToAllGroupe(query);
		
		return data;
	}
	
	/**
	 * R�ponses d'une question
	 * @return
	 */
	public static Reponse[] getReponsesByQuestion(Integer id){
		try {
			query=http.get(baseUrl+"reponses/reponsebyquestion/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reponse[] data= gson.jsonToAllReponse(query);
		
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
	 * Ensemble des GroupeQuestionnaire du questionnaire
	 * @param id
	 * @return
	 */
	public static GroupeQuestionnaire[] getGroupesByQuestionnaire(Integer id){
		try {
			query=http.get(baseUrl+"groupequestionnaires/groupebyquestionnaire/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroupeQuestionnaire[] data= gson.jsonToAllGroupeQuestionnaire(query);
		
		return data;
	}
	
	
	
	/**
	 * Recup�rer cim avec id_questionnaire et id_groupe
	 * @param "id_questionnaire"_"idGroupe"
	 * @return
	 */
	public static GroupeQuestionnaire[] getCIMGrpQst(String id){
		try {
			query=http.get(baseUrl+"groupequestionnaires/GrpQst/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroupeQuestionnaire[] data= gson.jsonToAllGroupeQuestionnaire(query);
		
		return data;
	}
	
	public static GroupeQuestionnaire getOneCIMGrpQst(String id){
		try {
			query=http.get(baseUrl+"groupequestionnaires/GrpQst/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroupeQuestionnaire data= gson.jsonToGroupeQuestionnaire(query);
		
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
	
	/**
	 * Ensemble des groupes appartenant � l'utilisateur
	 * @param id
	 * @return
	 */
	public static Utilisateur getUtilisateurs(Integer id){
		try {
			query=http.get(baseUrl+"users/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utilisateur data= gson.jsonToUtilisateur(query);
		
		return data;
	}
	
	/**
	 * Ensemble des questionnaires appartenant � l'utilisateur
	 * @param id
	 * @return
	 */
	public static Utilisateur[] getUtilisateursToQuestionnaire(Integer id){
		try {
			query=http.get(baseUrl+"questionnaires/utilisateur/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utilisateur[] data= gson.jsonToAllUtilisateur(query);
		
		return data;
	}
	
	/**
	 * Ensemble des questionnaires appartenant � l'utilisateur
	 * @param id
	 * @return
	 */
	public static Realisation[] getRealisationWithQuestionnaire(Integer id){
		try {
			query=http.get(baseUrl+"realisations/realisationwithquestionnaire/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Realisation[] data= gson.jsonToAllRealisation(query);
		
		return data;
	}
	
	public static GroupeUtilisateur[] getGroupeUtilisateur(Integer id){
		try {
			query=http.get(baseUrl+"groupeutilisateurs/user/"+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroupeUtilisateur[] data= gson.jsonToAllGroupeUtilisateur(query);
		
		return data;
	}
	
	/**
	 * Retourne le score de l'utilisateur pour un questionnaire
	 * @param param contient l'id utilisateur et questionnaire
	 * @return Instance de la r�alisation
	 */
	public static Realisation[] getScore(String param){
		try {
			query=http.get(baseUrl+"realisations/score/"+param);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Realisation[] data= gson.jsonToAllRealisation(query);
		
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
	
	public static ActifUser getDisconnectUser(){
		try {
			query=http.get(baseUrl+"user/disconnect/");
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
	
	/**
	 * Envoie instance de groupe questionnaire pour mise � jour
	 * @param groupequestionnaire
	 * @return
	 */
	public static GroupeQuestionnaire putGroupeQuestionnaires(GroupeQuestionnaire groupequestionnaire){
		try {
			query = http.put(baseUrl+"groupequestionnaires/"+groupequestionnaire.getId(), groupequestionnaire);
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
	
	public static Questionnaire postQuestionnarie(Questionnaire questionnaire){
		try {
			query = http.post(baseUrl+"questionnaires/", questionnaire);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Questionnaire data=gson.jsonToQuestionnaire(query);
		
		return data;
	}
	
	public static Questionnaire putQuestionnarie(Questionnaire questionnaire){
		try {
			query = http.put(baseUrl+"questionnaires/"+questionnaire.getId(), questionnaire);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Questionnaire data=gson.jsonToQuestionnaire(query);
		
		return data;
	}
	
	public static Question postQuestion(Question question){
		try {
			query = http.post(baseUrl+"questions/", question);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question data=gson.jsonToQuestion(query);
		
		return data;
	}
	
	public static Question putQuestion(Question question){
		try {
			query = http.put(baseUrl+"questions/"+question.getId(), question);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question data=gson.jsonToQuestion(query);
		
		return data;
	}
	
	public static Reponse postReponse(Reponse reponse){
		try {
			query = http.post(baseUrl+"reponses/", reponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reponse data=gson.jsonToReponse(query);
		return data;
	}
	
	
	public static Reponse putReponse(Reponse reponse){
		try {
			query = http.put(baseUrl+"reponses/"+reponse.getId(), reponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reponse data=gson.jsonToReponse(query);
		return data;
	}
	
	
	public static Groupe putGroupe(Groupe groupe){
		try {
			query = http.put(baseUrl+"groupes/"+groupe.getId(), groupe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Groupe data=gson.jsonToGroupe(query);
		return data;
	}
	
	public static GroupeQuestionnaire[] putGroupeQuestionnaire(GroupeQuestionnaire groupe){
		try {
			System.out.println("in put");
			query = http.put(baseUrl+"groupequestionnaires/"+groupe.getId(), groupe);
			System.out.println("after resquete "+query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroupeQuestionnaire[] data=gson.jsonToAllGroupeQuestionnaire(query);
		return data;
	}
	
	/**
	 * Supprimer cim entre questionnaire et groupe
	 * @param id
	 * @return
	 */
	public static void deleteCIMGroupeQuestionnaire(Integer id, String idGrpQuest, Integer idQuestionnaire){
		try {
			// v�rifie s'il y a plusieurs CIM
			query=http.get(baseUrl+"groupequestionnaires/VerifPlusieursGroupe/"+idGrpQuest);
			GroupeQuestionnaire[] querygq=gson.jsonToAllGroupeQuestionnaire(query);
			
			// Si oui on supprime uniquement la s�lectionn�
			System.out.println(querygq.length);
			if(querygq.length>1){
				query=http.delete(baseUrl+"groupequestionnaires/"+id);
			}
			// Si il n'y en a qu'une on supprime cim + questionnaire
			else{
				query=http.delete(baseUrl+"groupequestionnaires/"+id);
				query=http.delete(baseUrl+"questionnaires/"+idQuestionnaire);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	/**
	 * Supprimer cim entre questionnaire et groupe
	 * @param id
	 * @return
	 */
	public static GroupeQuestionnaire delGroupeQuestionnare(GroupeQuestionnaire gq){
		try {
			query=http.delete(baseUrl+"groupequestionnaires/"+gq.getId());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GroupeQuestionnaire data=gson.jsonToGroupeQuestionnaire(query);
		return data;
	}

}
