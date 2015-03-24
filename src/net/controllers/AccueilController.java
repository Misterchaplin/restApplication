package net.controllers;

import net.models.ActifUser;
import net.vues.VAccueil;
import net.vues.VLogin;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class AccueilController implements SelectionListener {
	public static VAccueil vAccueil;

	public AccueilController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
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
					
					System.out.println(AppController.getActiveUser());
					/*if(AppController.getActiveUser().getconnected()==true){
						vAccueil.getItemConnexion().setEnabled(false);
					}*/
				
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