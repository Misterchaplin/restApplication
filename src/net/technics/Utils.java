package net.technics;

import java.util.ArrayList;
import java.util.List;

import net.controllers.AppController;
import net.models.Groupe;
import net.models.Questionnaire;
import net.models.Utilisateur;

public class Utils {
	
	public static Questionnaire[] getQuestionnaireToAUtilisateur(){
		List<Questionnaire> myQestionnaires = new ArrayList<Questionnaire>();
		
		 Questionnaire[] lesQuestionnaires = Http.getAllQuestionnaires();
		 
		 if(lesQuestionnaires!=null && lesQuestionnaires.length>0){
			 for(Questionnaire unQuestionnaire:lesQuestionnaires){
		        Groupe[] lesGroupes = Http.getGroupesToQuestionnaire(unQuestionnaire.getId());     	 
		        for (Groupe unGroupe : lesGroupes) {	
		        	Utilisateur[] lesUsers = Http.getUtilisateursToGroupe(unGroupe.getId());
		        	for (Utilisateur aUtilisateur : lesUsers) {
			        	if(aUtilisateur.getId().equals(AppController.getActiveUser().getWho())){
			        		if(!myQestionnaires.contains(unQuestionnaire)){
			        			myQestionnaires.add(unQuestionnaire);
			        		}
			        		
			        	}
					}
		        }
			 }
		}
		 Questionnaire[] tabQuestionnaie = myQestionnaires.toArray(new Questionnaire[myQestionnaires.size()]);
		return tabQuestionnaie;
	}
	
	
	public static Groupe[] getGroupeToAUtilisateur(){
		List<Groupe> myGroupes = new ArrayList<Groupe>();
		
		Groupe[] lesGroupes = Http.getAllGroupes();
		 
		 if(lesGroupes!=null && lesGroupes.length>0){
			 for(Groupe unGroupe:lesGroupes){
		        Utilisateur[] lesUsers = Http.getUtilisateursToGroupe(unGroupe.getId());
		        for (Utilisateur aUtilisateur : lesUsers) {
			        if(aUtilisateur.getId().equals(AppController.getActiveUser().getWho())){
			        	if(!myGroupes.contains(unGroupe)){
			        		myGroupes.add(unGroupe);
			        	}
			        		
			        }
				}
		     }
		}
		Groupe[] tabGroupe = myGroupes.toArray(new Groupe[myGroupes.size()]);
		return tabGroupe;
	}
		
}
