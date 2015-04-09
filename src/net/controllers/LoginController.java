package net.controllers;

import java.io.IOException;
import java.util.ArrayList;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.CollectionQuestionnaireGroupe;
import net.models.Groupe;
import net.models.Questionnaire;
import net.models.Utilisateur;
import net.technics.Http;
import net.vues.VAccueil;
import net.vues.VLogin;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;

public class LoginController implements SelectionListener {
	public static VLogin vLogin;

	public LoginController(VLogin vLogin) {
		this.vLogin = vLogin;
	}

	public void init() {
		vLogin.getBtnConnexion().addSelectionListener(new SelectionListener() {
						
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			Utilisateur user= new Utilisateur();
			user.setLogin(LoginController.vLogin.getTxtLogin().getText());
			user.setPassword(LoginController.vLogin.getTxtPassword().getText());
			
			ActifUser actif = Http.getConnectUser(user);
			AppController.setActiveUser(actif);
				
			if(actif.getconnected()!=true){
				vLogin.getLblInformation().setText("Erreur dans l'authentification.");
			}
			else{
				vLogin.getShell().close();
				AccueilController.vAccueil.getItemConnexion().setEnabled(false);
				AccueilController.vAccueil.getItemConnexion().setText("Bienvenue: "+user.getLogin());
				AccueilController.vAccueil.getFirstNameCol().setLabelProvider(new ColumnLabelProvider(){

		            @Override
		            public String getText(Object element) {
		            	CollectionQuestionnaireGroupe p = (CollectionQuestionnaireGroupe)element;

		                return p.getQuestionnaire_libelle();
		            }

		        });
				
				AccueilController.vAccueil.getLastNameCol().setLabelProvider(new ColumnLabelProvider(){

			            @Override
			            public String getText(Object element) {
			            	CollectionQuestionnaireGroupe p = (CollectionQuestionnaireGroupe)element;

			                return p.getGroupe_libelle();
			            }

			        });
				 ArrayList<CollectionQuestionnaireGroupe> questionnairesGroupes = new ArrayList<CollectionQuestionnaireGroupe>();
			        Questionnaire[] lesQuestionnaires = Http.getAllQuestionnaires();
			       
			        
			        for(Questionnaire unQuestionnaire:lesQuestionnaires){
			        	Groupe[] lesGroupes = Http.getGroupesToQuestionnaire(unQuestionnaire.getId());     	 
			        	for (Groupe unGroupe : lesGroupes) {	
			        		CollectionQuestionnaireGroupe lesqg = new CollectionQuestionnaireGroupe();
							lesqg.setGroupe_id(unGroupe.getId());
			        		lesqg.setQuestionnaire_libelle(unQuestionnaire.getLibelle());
							lesqg.setGroupe_libelle(unGroupe.getLibelle());
							lesqg.setGroupe_code(unGroupe.getCode());
							lesqg.setQuestionnaire_id(unQuestionnaire.getId());
							lesqg.setQuestionnaire_domaine_id(unQuestionnaire.getDomaine_id());
							lesqg.setQuestionnaire_date(unQuestionnaire.getDate());
							questionnairesGroupes.add(lesqg);
						}
			        }
			        
			       AccueilController.vAccueil.getTableViewer().setInput(questionnairesGroupes);
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
