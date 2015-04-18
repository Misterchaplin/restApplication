package net.controllers;

import net.models.ActifUser;
import net.models.Questionnaire;
import net.vues.VAccueil;

public class AppController {
	private static ActifUser activeUser = new ActifUser();

	public static ActifUser getActiveUser() {
		return activeUser;
	}

	public static void setActiveUser(ActifUser activeUser) {
		AppController.activeUser = activeUser;
	}
	
	private static Questionnaire session_Id=null;

	public static Questionnaire getSession_Id() {
		return session_Id;
	}

	public static void setSession_Id(Questionnaire session_Id) {
		AppController.session_Id = session_Id;
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