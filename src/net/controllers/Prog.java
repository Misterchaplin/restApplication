package net.controllers;

import net.vues.VAccueil;

/**
 * Classe de démarrage de l'application 
 * 
 * 
 */
public class Prog {

	public static VAccueil vAccueil;

	/**
	 * Affichage de la vue vAccueil
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// Instanciation de la vue
			vAccueil = new VAccueil();
			new AppController(vAccueil);
			vAccueil.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
