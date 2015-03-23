package net.controllers;

import net.vues.VAccueil;

public class AppController {

	public AppController(VAccueil vAccueil) {
		AccueilController accueilController = new AccueilController(vAccueil);
		vAccueil.init();
		accueilController.init();
	}
}