package net.technics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import net.controllers.AccueilController;
import net.controllers.AppController;
import net.controllers.LoginController;
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
				//System.out.println("Ceci est un groupe "+unGroupe);
		        Utilisateur[] lesUsers = Http.getUtilisateursToGroupe(unGroupe.getId());
		        for (Utilisateur aUtilisateur : lesUsers) {
		        	//System.out.println("Ceci est un utilisateur "+aUtilisateur);
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
	
	public static void updateTableViewer(){
		AccueilController.vAccueil.getTableViewer().setInput(null);
		LoginController.remplirTableViewer();
	}
	
	/**
	 * Ajoute les questionnaires dans l'onglet groupe
	*/
	public static void remplirComboQuestionnaire(){
		
		AccueilController.vAccueil.getCbvQuestionnaireGroupe().setLabelProvider(new LabelProvider() {
			 @Override
	            public String getText(Object element) {
	            	Questionnaire p = (Questionnaire)element;
	                return p.getLibelle();
	            }
	    });
		
	    Questionnaire[] questionnaires = Utils.getQuestionnaireToAUtilisateur();
	    AccueilController.vAccueil.getCbvQuestionnaireGroupe().setInput(questionnaires);
	}
	
	/**
	 * Ajoute les groupes dans qcm
	 */
	public static void remplirComboGroupe(){
		AccueilController.vAccueil.getBtnNouveauQuestionnaire().setVisible(false);
		AccueilController.vAccueil.getCbvQcm().setLabelProvider(new LabelProvider() {
			 @Override
	            public String getText(Object element) {
	            	Groupe p = (Groupe)element;
	                return p.getLibelle();
	            }
	    });
		

	    Groupe[] groupes = Utils.getGroupeToAUtilisateur();
	    AccueilController.vAccueil.getCbvQcm().setInput(groupes);
	}
		
}
