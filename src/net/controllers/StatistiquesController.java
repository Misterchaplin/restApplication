package net.controllers;

import java.util.ArrayList;
import java.util.List;

import net.http.TestHttp;
import net.models.CollectionQuestionnaireGroupe;
import net.models.CollectionUtilisateurScore;
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
import org.eclipse.jface.viewers.ColumnLabelProvider;
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
				System.out.println(groupe);
			}
		});
		
		
		
		vAccueil.getBtnStatValider().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StatistiquesController.vAccueil.getUserStat().setLabelProvider(new ColumnLabelProvider(){

		            @Override
		            public String getText(Object element) {
		            	CollectionUtilisateurScore p = (CollectionUtilisateurScore)element;

		                return p.getNom()+" "+p.getPrenom();
		            }

		        });
				StatistiquesController.vAccueil.getQuestStat().setLabelProvider(new ColumnLabelProvider(){

		            @Override
		            public String getText(Object element) {
		            	CollectionUtilisateurScore p = (CollectionUtilisateurScore)element;

		                return String.valueOf(p.getScore()+"%");
		            }

		        });
				IStructuredSelection selection = (IStructuredSelection) vAccueil.getCbvStatistiquesQuestionnaire().getSelection();
				Questionnaire questionnaire = (Questionnaire)selection.getFirstElement();
				
				if(questionnaire!=null){
		            Question[] laQuestion =Http.getQuestionByQuestionnaire(questionnaire.getId());
		            Integer nbQuestion = laQuestion.length;
		            // Tous les utilisateurs du groupe
		            try {
		            	List<CollectionUtilisateurScore> collUserScore = new ArrayList<CollectionUtilisateurScore>();
						Realisation[] lesRealisations = Http.getRealisationWithQuestionnaire(questionnaire.getId());
						for (Realisation realisation : lesRealisations) {
							System.out.println(realisation);
							Utilisateur leUser=Http.getUtilisateurs(realisation.getUtilisateur_id());
							CollectionUtilisateurScore cus=new CollectionUtilisateurScore();
							cus.setLogin(leUser.getLogin());
							cus.setNom(leUser.getNom());
							cus.setPrenom(leUser.getPrenom());
							Integer pourcentageReponse = (int) (100*realisation.getScore()/nbQuestion);
							cus.setScore(pourcentageReponse);
							cus.setDate(realisation.getDate());
							collUserScore.add(cus);
						}
						 StatistiquesController.vAccueil.getTableViewerStat().setInput(collUserScore);
		            } catch (NullPointerException e2) {
						vAccueil.getLblInformation().setText("Pas encore de statistique pour ce questionnaire");
					}
				}
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
