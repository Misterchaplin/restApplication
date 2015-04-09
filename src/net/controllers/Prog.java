package net.controllers;

import net.vues.VAccueil;

public class Prog {

	public static VAccueil vAccueil;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// ajout de vSprint pour tester la partie sprint sans avoir à se
			// connecter ne pas effacer svp...

			vAccueil = new VAccueil();
			// ceci est un test icidzazfafzaffafa
			new AppController(vAccueil);
			// VOverview vOverview = new VOverview();
			// new AppController(vOverview);
			vAccueil.open();
			// vOverview.open();
			// vSprint.open();
			// vAddUserStorie.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
