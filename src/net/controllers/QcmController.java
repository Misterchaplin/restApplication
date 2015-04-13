package net.controllers;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.Groupe;
import net.models.GroupeQuestionnaire;
import net.models.GroupeUtilisateur;
import net.models.Question;
import net.models.Questionnaire;
import net.models.Reponse;
import net.models.Utilisateur;
import net.technics.Http;
import net.vues.VAccueil;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.ui.internal.forms.widgets.Paragraph;

public class QcmController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;
	protected Questionnaire session_id;

	public QcmController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		
		vAccueil.getCbvQcm().setLabelProvider(new LabelProvider() {
			 @Override
	            public String getText(Object element) {
	            	Groupe p = (Groupe)element;
	                return p.getLibelle();
	            }
	    });
		

	    Groupe[] groupes = Http.getAllGroupes();
	    vAccueil.getCbvQcm().setInput(groupes);
		
		vAccueil.getBtnAjouterQcm().addSelectionListener(new SelectionAdapter() {
			private int nbTrueAnswer;


			@Override
			public void widgetSelected(SelectionEvent e) {
				Questionnaire questionnaire = new Questionnaire();
				questionnaire.setLibelle(vAccueil.getTxtQcm().getText());
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				questionnaire.setDate(dateFormat.format(date)); 
				System.out.println(questionnaire.getDate());
				IStructuredSelection selection = (IStructuredSelection) vAccueil.getCbvQcm().getSelection();
		        Groupe element = (Groupe)selection.getFirstElement();
				Groupe groupe = new Groupe();
				if(selection.size()==1){
					groupe.setId(element.getId());
				}
				
				Question question = new Question();
				question.setLibelle(vAccueil.getTxtQuestionQcm().getText());

				Reponse reponse1 = new Reponse();
				reponse1.setLibelle(vAccueil.getTxtQcm1().getText());
				reponse1.setGood((vAccueil.getBtnCkGroupe1().getSelection()) ? 1 : 0);
				Reponse reponse2 = new Reponse();
				reponse2.setLibelle(vAccueil.getTxtQcm2().getText());
				reponse2.setGood((vAccueil.getBtnCkGroupe2().getSelection()) ? 1 : 0);
				Reponse reponse3 = new Reponse();
				reponse3.setLibelle(vAccueil.getTxtQcm3().getText());
				reponse3.setGood((vAccueil.getBtnCkGroupe3().getSelection()) ? 1 : 0);
				Reponse reponse4 = new Reponse();
				reponse4.setLibelle(vAccueil.getTxtQcm4().getText());
				reponse4.setGood((vAccueil.getBtnCkGroupe4().getSelection()) ? 1 : 0);
				
				List<Reponse> lesReponses = new ArrayList<Reponse>();
				lesReponses.add(reponse1);
				lesReponses.add(reponse2);
				lesReponses.add(reponse3);
				lesReponses.add(reponse4);
				
				boolean check=true;
				if(reponse1.getLibelle().isEmpty() || reponse2.getLibelle().isEmpty() || 
					reponse3.getLibelle().isEmpty() || reponse4.getLibelle().isEmpty()){
					check=false;
				}
				else{
					nbTrueAnswer=0;
					if(reponse1.getGood()==1){
						nbTrueAnswer++;
					}
					if(reponse2.getGood()==1){
						nbTrueAnswer++;
					}
					if(reponse3.getGood()==1){
						nbTrueAnswer++;
					}
					if(reponse4.getGood()==1){
						nbTrueAnswer++;
					}
				}

				if(questionnaire.getLibelle().isEmpty() || question.getLibelle().isEmpty()){
					check=false;
				}
				if(groupe.getId()==null){
					check=false;
				}
			
				
				if(check==true && (nbTrueAnswer==1 || nbTrueAnswer==2)){
					System.out.println("Mise en place des requêtes désormais possible");
					Questionnaire insertQuestionnaire = Http.postQuestionnarie(questionnaire);
					
					//Association entre groupe et questionnaire
					GroupeQuestionnaire groupeQuestionnaire = new GroupeQuestionnaire();
					groupeQuestionnaire.setGroupe_id(groupe.getId());
					groupeQuestionnaire.setQuestionnaire_id(insertQuestionnaire.getId());
					GroupeQuestionnaire insertGroupeQuestionnaire = Http.postGroupeQuestionnaires(groupeQuestionnaire);
					
					//Insertion d'une question appartenant à un questionnaire
					question.setQuestionnaire_id(insertQuestionnaire.getId());
					Question insertQuestion = Http.postQuestion(question);
					System.out.println(insertQuestion);
					
					//Insertion des reponses de la question
					boolean insertCheck=true;
					for (Reponse reponse : lesReponses) {
						reponse.setQuestion_id(insertQuestion.getId());
						Reponse insertReponse = Http.postReponse(reponse);
						System.out.println(reponse);
						if(insertReponse.getId().equals(null)){
							insertCheck=false;
						}
					}
					
					//Si tout est correct alors on associe les précédents ajout à l'utilisateur (s'il la relation n'existe pas encore)
					if(insertCheck==true){				
						GroupeUtilisateur groupeUtilisateur = new GroupeUtilisateur();
						groupeUtilisateur.setGroupe_id(groupe.getId());
						groupeUtilisateur.setUtilisateur_id(AppController.getActiveUser().getWho());
						
						GroupeUtilisateur[] getGroupeUtilisateur = Http.getGroupeUtilisateur(groupeUtilisateur.getUtilisateur_id());
						boolean guCheck = false;
						for (GroupeUtilisateur gu : getGroupeUtilisateur) {
							if((groupeUtilisateur.getGroupe_id()==gu.getGroupe_id()) && groupeUtilisateur.getUtilisateur_id()==gu.getUtilisateur_id()){
								guCheck=true;
							}
						}
						
						if(guCheck==false){
							GroupeUtilisateur insertGroupeUtilisateur = Http.postGroupeUtilisateurs(groupeUtilisateur);
							vAccueil.getLblInformation().setText("Ajout réussie");
							session_id=questionnaire;
							System.out.println(session_id);
						}
						else{
							vAccueil.getLblInformation().setText("Ajout réussie");
						}
					}
					
				}else{
					vAccueil.getLblInformation().setText("Un ou plusieurs champs sont manquants");
				}
	
			}
	
		
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

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