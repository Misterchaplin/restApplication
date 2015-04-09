package net.controllers;

import net.models.CollectionQuestionnaireGroupe;
import net.models.Questionnaire;
import net.vues.VAccueil;
import net.vues.VLogin;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

public class AccueilController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;

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
		
		vAccueil.getTable().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				StructuredSelection selection = (StructuredSelection) vAccueil.getTableViewer().getSelection();
				CollectionQuestionnaireGroupe selectedQuestionnaire = (CollectionQuestionnaireGroupe) selection.getFirstElement();
				System.out.println(selectedQuestionnaire.getQuestionnaire_id()+ "--" +selectedQuestionnaire.getGroupe_id());
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