package net.controllers;

import net.models.ActifUser;
import net.vues.VAccueil;

public class AppController {
	private static ActifUser activeUser = new ActifUser();

	public static ActifUser getActiveUser() {
		return activeUser;
	}

	public static void setActiveUser(ActifUser activeUser) {
		AppController.activeUser = activeUser;
	}

	public AppController(VAccueil vAccueil) {
		AccueilController accueilController = new AccueilController(vAccueil);
		GroupeController groupeController = new GroupeController(vAccueil);
		QcmController qcmController  = new QcmController(vAccueil);
		vAccueil.init();
		accueilController.init();
		groupeController.init();
		qcmController.init();
	}
	
}