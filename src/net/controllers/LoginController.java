package net.controllers;

import java.io.IOException;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.Groupe;
import net.models.Questionnaire;
import net.models.Utilisateur;
import net.technics.Http;
import net.technics.ProductTvProvider;
import net.vues.VAccueil;
import net.vues.VLogin;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;

public class LoginController implements SelectionListener {
	public static VLogin vLogin;

	public LoginController(VLogin vLogin) {
		this.vLogin = vLogin;
	}

	public void init() {
		vLogin.getBtnConnexion().addSelectionListener(new SelectionListener() {
						
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			Utilisateur user= new Utilisateur();
			user.setLogin(LoginController.vLogin.getTxtLogin().getText());
			user.setPassword(LoginController.vLogin.getTxtPassword().getText());
			
			ActifUser actif = Http.getConnectUser(user);
			AppController.setActiveUser(actif);
				
			if(actif.getconnected()!=true){
				vLogin.getLblInformation().setText("Erreur dans l'authentification.");
			}
			else{
				vLogin.getShell().close();
				AccueilController.vAccueil.getItemConnexion().setEnabled(false);
				AccueilController.vAccueil.getTabGestion().setVisible(true);
		
				Questionnaire[]  d= Http.getAllQuestionnaires();
				for(Questionnaire questionnaire : d) {
					Groupe[]  g= Http.getGroupesToQuestionnaire(questionnaire.getId());
						
					for(Groupe groupe : g) {
						Utilisateur[]  u= Http.getUtilisateursToGroupe(groupe.getId());
							String i="1";
						for (Utilisateur utilisateur : u) {
							if(utilisateur.getId().equals(AppController.getActiveUser().getWho())) {
								/*TableItem item = new TableItem(AccueilController.vAccueil.getTable(), SWT.NONE);
								item.setText(new String[] {questionnaire.toString(), groupe.toString()});*/
							/*	AccueilController.vAccueil.getTableViewer().setContentProvider(new ArrayContentProvider());
								AccueilController.vAccueil.getTableViewer().setLabelProvider(new ProductTvProvider());
								AccueilController.vAccueil.getTableViewer().setInput(Http.getAllQuestionnaires());
								AccueilController.vAccueil.getTableViewer().refresh();*/
							}
						}
					}
				}
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
