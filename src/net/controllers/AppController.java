package net.controllers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import net.models.ActifUser;
import net.models.Groupe;
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
	private static Groupe sessionGroupe_Id=null;
	
	public static Groupe getSessionGroupe_Id() {
		return sessionGroupe_Id;
	}

	public static void setSessionGroupe_Id(Groupe sessionGroupe_Id) {
		AppController.sessionGroupe_Id = sessionGroupe_Id;
	}

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
		StatistiquesController statistiquesController = new StatistiquesController(vAccueil);
		vAccueil.init();
		accueilController.init();
		groupeController.init();
		qcmController.init();
		statistiquesController.init();
		accueilController.vAccueil.getTabGestion().setEnabled(false);
		
	}
	
}