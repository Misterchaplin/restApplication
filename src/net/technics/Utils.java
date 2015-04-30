package net.technics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import net.controllers.AccueilController;
import net.controllers.AppController;
import net.controllers.LoginController;
import net.controllers.StatistiquesController;
import net.models.Groupe;
import net.models.Questionnaire;
import net.models.Utilisateur;

public class Utils {
	
	public static Questionnaire[] getQuestionnaireToAUtilisateur(){
		List<Questionnaire> myQestionnaires = new ArrayList<Questionnaire>();
		Questionnaire[] lesQuestionnaires = Http.getAllQuestionnaires();
		 
		 if(lesQuestionnaires!=null && lesQuestionnaires.length>0){
			 System.out.println("entré");
			 for(Questionnaire unQuestionnaire:lesQuestionnaires){
		        Groupe[] lesGroupes = Http.getGroupesToQuestionnaire(unQuestionnaire.getId());  
		        if(lesGroupes!=null && lesGroupes.length>0){
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
		}
		else{
			
		 }
		 
		Questionnaire[] tabQuestionnaie = myQestionnaires.toArray(new Questionnaire[myQestionnaires.size()]);
		return tabQuestionnaie;
	}
	
	public static Utilisateur[] getUtilisateurToAQuestionnaire(Questionnaire quest){
		Utilisateur[] lesUsers = Http.getUtilisateursToQuestionnaire(quest.getId());
		 
		return lesUsers;
	}
	
	public static Questionnaire[] getQuestionnaireToGroupe(Groupe groupe){
		Questionnaire[] lesQuestionnaires = Http.getQuestionnaireToGroupe(groupe.getId());
		 
		return lesQuestionnaires;
	}
	
	public static Groupe[] getGroupeContainsQuestionnaireToAUtilisateur(){
		List<Groupe> myGroupes = new ArrayList<Groupe>();
		List<Groupe> myEndGroupes = new ArrayList<Groupe>();
		Questionnaire[] lesQuestionnaires = Http.getAllQuestionnaires();
		 
		 if(lesQuestionnaires!=null && lesQuestionnaires.length>0){
			 for(Questionnaire unQuestionnaire:lesQuestionnaires){
		        Groupe[] lesGroupes = Http.getGroupesToQuestionnaire(unQuestionnaire.getId());     	 
		        for (Groupe unGroupe : lesGroupes) {	
		        	Utilisateur[] lesUsers = Http.getUtilisateursToGroupe(unGroupe.getId());
		        	for (Utilisateur aUtilisateur : lesUsers) {
			        	if(aUtilisateur.getId().equals(AppController.getActiveUser().getWho())){
			        		if(!in_array(myGroupes, unGroupe.getLibelle())){
			        			myGroupes.add(unGroupe);
			        		}
			        	}
					}
		        }
			 }
		}
		
		Groupe[] tabGroupe = myGroupes.toArray(new Groupe[myGroupes.size()]);
		return tabGroupe;
	}
	
	/**
	 * Retourne si needle est contenu dans la list haytack
	 * @param haystack
	 * @param needle
	 * @return true si est présente
	 */
	public static boolean in_array(List<Groupe> haystack, String needle) {
	    for(int i=0;i<haystack.size();i++) {
	        if(haystack.get(i).toString().equals(needle)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
	public static Groupe[] getGroupeToAUtilisateur(){
		List<Groupe> myGroupes = new ArrayList<Groupe>();
		Groupe[] lesGroupes = Http.getAllGroupes();
		 
		 if(lesGroupes!=null && lesGroupes.length>0){
			 for(Groupe unGroupe:lesGroupes){
				System.out.println("Ceci est un groupe "+unGroupe);
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
	
	
	/**
	 * Ajoute les questionnaires dans l'onglet Statistiques en référence au groupe choisi
	*/
	public static void remplirComboQuestionnaireStat(Groupe groupe){
		
		AccueilController.vAccueil.getCbvStatistiquesQuestionnaire().setLabelProvider(new LabelProvider() {
			 @Override
	            public String getText(Object element) {
	            	Questionnaire p = (Questionnaire)element;
	                return p.getLibelle();
	            }
	    });
		
		Questionnaire[] questionnaires = Utils.getQuestionnaireToGroupe(groupe);
		try {
			AccueilController.vAccueil.getCbvStatistiquesQuestionnaire().setInput(questionnaires);
		} catch (NullPointerException e) {
			StatistiquesController.vAccueil.getLblInformation().setText("Pas de questionnaire pour ce groupe");
		}
	}
	
	
	/**
	 * Ajoute les groupes dans l'onglet Statistiques
	 */
	public static void remplirComboGroupeStat(){
		AccueilController.vAccueil.getCbvStatistiquesGroupe().setLabelProvider(new LabelProvider() {
			 @Override
	            public String getText(Object element) {
	            	Groupe p = (Groupe)element;
	                return p.getLibelle();
	            }
	    });
		

	    Groupe[] groupes = Utils.getGroupeContainsQuestionnaireToAUtilisateur();
	    AccueilController.vAccueil.getCbvStatistiquesGroupe().setInput(groupes);
	}
		
}
