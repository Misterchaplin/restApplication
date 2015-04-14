package net.technics;

import java.util.ArrayList;
import java.util.List;

import net.controllers.AppController;
import net.models.CollectionQuestionnaireGroupe;
import net.models.Groupe;
import net.models.Questionnaire;
import net.models.Realisation;
import net.models.Utilisateur;

public class Utils {
	
	public static Questionnaire[] getQuestionnaireToAUtilisateur(){
		List<Questionnaire> myQestionnaires = new ArrayList<Questionnaire>();
		
		 Questionnaire[] lesQuestionnaires = Http.getAllQuestionnaires();
		 
		 if(lesQuestionnaires!=null && lesQuestionnaires.length>0){
		        for(Questionnaire unQuestionnaire:lesQuestionnaires){
		        	Realisation[] lesRealisations = Http.getQuestionnaireToRealisation(AppController.getActiveUser().getWho());     	 
		        	for (Realisation uneRealisation : lesRealisations) {	
		        		if(uneRealisation.getUtilisateur_id()==AppController.getActiveUser().getWho()){
		        			myQestionnaires.add(unQuestionnaire);
		        		}
					}
		        }
	        }
		 Questionnaire[] tabQuestionnaie = myQestionnaires.toArray(new Questionnaire[myQestionnaires.size()]);
		return tabQuestionnaie;
	}
}
