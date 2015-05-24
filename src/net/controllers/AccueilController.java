package net.controllers;

import net.models.CollectionQuestionnaireGroupe;
import net.models.GroupeQuestionnaire;
import net.vues.VAccueil;
import net.vues.VLogin;
import net.technics.Http;
import net.technics.Utils;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
/**
 * Classe AccueilController permettant de gérer l'onglet accueil
 * 
 * 
 */
public class AccueilController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;
	protected Display display;
	private CollectionQuestionnaireGroupe selectedQuestionnaire;

	public AccueilController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	/**
	 * Fonction init permettant d'initialiser AccueilController
	 * 
	 * 
	 */
	public void init() {
		vAccueil.getItemLogin().setImage(null); // enléve l'image
		vAccueil.getTabGestion().setVisible(true);// Affiche TabGestion

		// Si on appuie sur onglet connexion
		vAccueil.getItemConnexion().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// si on se connecte
				if (vAccueil.getItemConnexion().getText().equals("Connexion")) {
					VLogin vLogin = new VLogin(); // Instanciation de la vue Vlogin
					LoginController loginController = new LoginController(vLogin); // Instanciation de la vue
					vLogin.init();
					loginController.init(); // Initialisation de loginController
					vLogin.open(); // Ouverture de la vue
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});	
		
		/**
		 * Si on appuie sur le bouton de déconnexion
		 * 
		 * 
		 */
		vAccueil.getItemLogin().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				System.out.println(Http.getDisconnectUser());// déconnexion de l'utilisateur
				vAccueil.getAccueil().setVisible(false); // 
				
				//Prog.main(null);

				new Prog().main(null);
				System.exit(0);// Ferme le programme et relance à nouveau l'application
			
				
			}
		});
		
		
		/**
		 * Si on appuie sur le bouton supprimer
		 * 
		 * 
		 */
		vAccueil.getBtnSupprimerAccueil().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				try {
					selectedQuestionnaire=selectionTableViewer();
					String idGrpQuest = selectedQuestionnaire.getQuestionnaire_id()+ "_" +selectedQuestionnaire.getGroupe_id(); // récuperation de l'id du questionnaire
					Integer idQuestionnaire = selectedQuestionnaire.getQuestionnaire_id(); // id du groupe 
					
					GroupeQuestionnaire[] test = Http.getCIMGrpQst(idGrpQuest); // recuperation de la cim groupeQuestionnaire
					 
					Shell shell = new Shell(display); // affichage de la messageBox
				    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING |SWT.YES | SWT.NO);
				    messageBox.setMessage("Etes-vous sur de vouloir supprimer "+selectedQuestionnaire.getQuestionnaire_libelle()+" ?");
				   
				    int rc = messageBox.open();
				    
				    if (rc == SWT.YES){ // SI on appuie sur oui, supression de la cim
				    	
				    	for (GroupeQuestionnaire gq : test) {
							Http.deleteCIMGroupeQuestionnaire(gq.getId(),idGrpQuest,idQuestionnaire);
						}
						
						vAccueil.getLblInformation().setText("Suppression de "+selectedQuestionnaire.getQuestionnaire_libelle()+ " du groupe "+selectedQuestionnaire.getGroupe_libelle());
						
						Utils.updateTableViewer();
				    }
					
				} catch (NullPointerException npe1) {
					vAccueil.getLblInformation().setText("Veuillez saisir un questionnaire !"); // Vérification de la selection de quelque chose 
				}
				
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
			
		});	
		
		/**
		 * Si on appuie sur le bouton modifier groupe
		 * 
		 * 
		 */
		vAccueil.getBtnModifierGroupeAccueil().addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
        		selectedQuestionnaire=selectionTableViewer();
        		vAccueil.getTabGestion().setSelection(2);
		    	GroupeController groupeController = new GroupeController(vAccueil);
		    	groupeController.setUpdateGroupe(selectedQuestionnaire.getGroupe_id());
		    	groupeController.setUpdateQcmQuestionnaire(selectedQuestionnaire.getQuestionnaire_id());
		    	groupeController.init();
        		}
        		catch (NullPointerException npe2) {
					vAccueil.getLblInformation().setText("Veuillez saisir un questionnaire !");
					vAccueil.getTabGestion().setSelection(0);
				}
        	}
        });
		
		/**
		 * Si on appuie sur le bouton modifier questionnaire
		 * 
		 * 
		 */
		vAccueil.getBtnModifierAccueil().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					selectedQuestionnaire=selectionTableViewer();
					
					
				    	vAccueil.getTabGestion().setSelection(1); // Affichage de l'onglet QCM
				    	QcmController qcmController = new QcmController(vAccueil);
				    	AppController.setSession_Id(Http.getQuestionnaire(selectedQuestionnaire.getQuestionnaire_id()));
				    	AppController.setSessionGroupe_Id(Http.getGroupe(selectedQuestionnaire.getGroupe_id()));
				    	vAccueil.getBtnAjouterQuestion().setVisible(true);
				    	vAccueil.getBtnAjouterQcm().setText("Modifier");
				    	vAccueil.getBtnNouveauQuestionnaire().setVisible(true);
						vAccueil.getLblMerciDe().setVisible(true);
						vAccueil.getLblMerciDe().setText("Pour ajouter un nouveau questionnaire vous devez d'abord appuyer sur terminer.");
						qcmController.initUpdate();
				    
				 
				
				} catch (NullPointerException npe2) {
					vAccueil.getLblInformation().setText("Veuillez saisir un questionnaire !");
					vAccueil.getTabGestion().setSelection(0);
				}
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});	
		
	}
	/**
	 * 
	 * @return renvoi le questionnaire selectionné
	 * 
	 */
	public CollectionQuestionnaireGroupe selectionTableViewer(){
		StructuredSelection selection = (StructuredSelection) vAccueil.getTableViewer().getSelection();
		CollectionQuestionnaireGroupe selectedQuestionnaire = (CollectionQuestionnaireGroupe) selection.getFirstElement();
		
		return selectedQuestionnaire;
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