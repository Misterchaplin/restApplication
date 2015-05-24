package net.controllers;

import java.util.ArrayList;
import java.util.Collections;

import net.models.ActifUser;
import net.models.CollectionQuestionnaireGroupe;
import net.models.Groupe;
import net.models.Questionnaire;
import net.models.Utilisateur;
import net.technics.Http;
import net.technics.Utils;
import net.vues.VAccueil;
import net.vues.VLogin;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.wb.swt.SWTResourceManager;
/**
 * Classe LoginController permettant de gérer l'onglet login
 * 
 * 
 */
public class LoginController implements SelectionListener {
	private static Integer messageQuestionnaire=0;
	public static VLogin vLogin;

	public LoginController(VLogin vLogin) {
		this.vLogin = vLogin;
	}

	/**
	 * Fonction init permettant d'initialiser LoginController
	 * 
	 * 
	 */
	public void init() {
		vLogin.getShell().getDisplay().addFilter(SWT.KeyDown, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				System.out.println("fff");
				String res = "";
				switch (arg0.character) {
					case SWT.CR :
					res = "Touche Entree";
					break;
					case SWT.ESC :
					res = "Touche Echap";
					break;
					default :
				}
				System.out.println(res);
			
			}
		});
		
		/**
		 * Si on appuie sur le bouton connexion 
		 * 
		 * 
		 */
		vLogin.getBtnConnexion().addSelectionListener(new SelectionListener() {
						
		@SuppressWarnings("unchecked")
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			Utilisateur user= new Utilisateur(); // Instanciation d'un nouvel utilisateur
			user.setLogin(LoginController.vLogin.getTxtLogin().getText());
			user.setPassword(LoginController.vLogin.getTxtPassword().getText());
			
			ActifUser actif = Http.getConnectUser(user); 
			AppController.setActiveUser(actif); // Ajout de l'utilisateur actif
				
			if(actif.getconnected()!=true){
				vLogin.getLblInformation().setText("Erreur dans l'authentification."); // S'il n'est pas connecté affichage d'une erreur
			}
			else{// Sinon
				vLogin.getShell().close(); // fermeture du shell de login
				AccueilController.vAccueil.getTabGestion().setEnabled(true); // et activation de la vue VAccueil
				AccueilController.vAccueil.getItemConnexion().setEnabled(false);
				AccueilController.vAccueil.getItemLogin().setEnabled(true);
				AccueilController.vAccueil.getItemConnexion().setText("Bienvenue: "+user.getLogin());// Affichage du message de bienvenue
				AccueilController.vAccueil.getItemLogin().setImage(SWTResourceManager.getImage("./bin/net/images/logout.png"));// bouton de déconnexion
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
				// Appel de la fonction pour remplier le tableViewer avec les groupes et questionnaires
				remplirTableViewer();
				if(messageQuestionnaire==0){
					// remplissage des combobox
					Utils.remplirComboQuestionnaire();
					Utils.remplirComboGroupe();
					Utils.remplirComboGroupeStat();
				}
				
			}						
		}
						
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
		}
		});
	}				
	
	/**
	 * Après s'etre connecté, remplissage du tableViewer avec les groupes et les questionnaires
	 * 
	 * 
	 */
	public static void remplirTableViewer(){
		ArrayList<CollectionQuestionnaireGroupe> questionnairesGroupes = new ArrayList<CollectionQuestionnaireGroupe>();
        Questionnaire[] lesQuestionnaires = Http.getAllQuestionnaires();
       
        if(lesQuestionnaires!=null && lesQuestionnaires.length>0){
	        for(Questionnaire unQuestionnaire:lesQuestionnaires){
	        	//System.out.println("C'est un questionnaire: "+unQuestionnaire);
	        	Groupe[] lesGroupes = Http.getGroupesToQuestionnaire(unQuestionnaire.getId()); 
	        	if(lesGroupes!=null && lesGroupes.length>0){
		        	for (Groupe unGroupe : lesGroupes) {	
		        		//System.out.println("C'est un groupe: "+unGroupe);
		        		Utilisateur[] lesUsers = Http.getUtilisateursToGroupe(unGroupe.getId());
		        		for (Utilisateur aUtilisateur : lesUsers) {
		        			//System.out.println("C'est un utilisateur: "+aUtilisateur);
							if(aUtilisateur.getId()==AppController.getActiveUser().getWho()){
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
		        	}
				}
	        }
        }
	    else{ // S'il est vide affichage messageBox
	    	 MessageBox messageBox = new MessageBox(AccueilController.vAccueil.getAccueil().getShell(), SWT.ICON_WARNING | SWT.OK);
	    	 messageQuestionnaire++;
	         messageBox.setText("Warning");
	         messageBox.setMessage("Vous n'avez crée aucun questionnaire");
	         messageBox.open();
	    }
        
       AccueilController.vAccueil.getTableViewer().setInput(questionnairesGroupes);
		
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
