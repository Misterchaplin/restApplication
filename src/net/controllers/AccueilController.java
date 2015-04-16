package net.controllers;

import net.models.CollectionQuestionnaireGroupe;
import net.models.GroupeQuestionnaire;
import net.vues.VAccueil;
import net.vues.VLogin;
import net.technics.Http;
import net.technics.Utils;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class AccueilController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;
	protected Display display;

	public AccueilController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		vAccueil.getTabGestion().setVisible(true);
		/*vAccueil.getTabGestion().getTabList()[0].setEnabled(false);
		vAccueil.getTabGestion().getTabList()[1].setEnabled(false);
		vAccueil.getTabGestion().getTabList()[2].setEnabled(false);*/
		// onglet connexion
		vAccueil.getItemConnexion().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// si on se connecte
				if (vAccueil.getItemConnexion().getText().equals("Connexion")) {
					VLogin vLogin = new VLogin();
					LoginController loginController = new LoginController(vLogin);
					vLogin.init();
					loginController.init();
					vLogin.open();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});	
		
		vAccueil.getBtnSupprimerAccueil().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				Shell shell = new Shell(display);
				StructuredSelection selection = (StructuredSelection) vAccueil.getTableViewer().getSelection();
				CollectionQuestionnaireGroupe selectedQuestionnaire = (CollectionQuestionnaireGroupe) selection.getFirstElement();
				String idGrpQuest = selectedQuestionnaire.getQuestionnaire_id()+ "_" +selectedQuestionnaire.getGroupe_id();
				Integer idQuestionnaire = selectedQuestionnaire.getQuestionnaire_id();
				
				GroupeQuestionnaire[] test = Http.getCIMGrpQst(idGrpQuest);
				 
			    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING |SWT.YES | SWT.NO);
			    messageBox.setMessage("Etes-vous sur de vouloir supprimer "+selectedQuestionnaire.getQuestionnaire_libelle()+" ?");
			   
			    int rc = messageBox.open();
			    
			    if (rc == SWT.YES){
			    	
			    	for (GroupeQuestionnaire gq : test) {
						Http.deleteCIMGroupeQuestionnaire(gq.getId(),idGrpQuest,idQuestionnaire);
					}
					
					vAccueil.getLblInformation().setText("Suppression de "+selectedQuestionnaire.getQuestionnaire_libelle()+ " du groupe "+selectedQuestionnaire.getGroupe_libelle());
					
					Utils.updateTableViewer();
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