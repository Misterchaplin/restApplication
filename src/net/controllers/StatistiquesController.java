package net.controllers;

import net.http.TestHttp;
import net.models.Groupe;
import net.models.GroupeQuestionnaire;
import net.models.GroupeUtilisateur;
import net.models.Question;
import net.models.Questionnaire;
import net.models.Realisation;
import net.models.Utilisateur;
import net.technics.Http;
import net.technics.Utils;
import net.vues.VAccueil;

import org.apache.http.client.HttpResponseException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class StatistiquesController implements SelectionListener {
	
	public static VAccueil vAccueil;
	
	
	public StatistiquesController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}
	
	
	
	public void init(){
		
		
		vAccueil.getCbStatistiquesGroupe().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection2 = (IStructuredSelection) vAccueil.getCbvStatistiquesGroupe().getSelection();
	         	Groupe groupe = (Groupe)selection2.getFirstElement();
	         	Utils.remplirComboQuestionnaireStat(groupe);
				
			}
		});
		
		vAccueil.getBtnStatValider().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) vAccueil.getCbvStatistiquesQuestionnaire().getSelection();
	            Questionnaire questionnaire = (Questionnaire)selection.getFirstElement();
	            Question[] laQuestion =Http.getQuestionByQuestionnaire(questionnaire.getId());
	            Integer nbQuestion = laQuestion.length;
	            System.out.println(nbQuestion);
	            // Tous les utilisateurs du groupe
				Utilisateur[] lesUsers = Http.getUtilisateursToQuestionnaire(questionnaire.getId());
				for (Utilisateur utilisateur : lesUsers) {
					System.out.println(utilisateur.getLogin());
					String param=questionnaire.getId()+"_"+utilisateur.getId();
					System.out.println("param : "+param);
					Realisation[] score=Http.getScore(param);
					for (Realisation unScore : score) {
						System.out.println("Le score : "+ unScore.getScore());
						Float pourcentageReponse = (float) (100*unScore.getScore()/nbQuestion);
						System.out.println("pourcentage : "+pourcentageReponse);
					}
					
				}
				
				
				//System.out.println(lesUsers);
		        //AccueilController.vAccueil.getTableViewer_1().setInput(lesUsers);
			}
		});
	}
	
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
