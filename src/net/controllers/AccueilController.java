package net.controllers;

import java.io.IOException;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.Groupe;
import net.models.Questionnaire;
import net.models.Utilisateur;
import net.vues.VAccueil;
import net.vues.VLogin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;

public class AccueilController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;

	public AccueilController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		vAccueil.getTabGestion().setVisible(true);
			
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