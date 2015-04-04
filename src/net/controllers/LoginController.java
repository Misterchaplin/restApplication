package net.controllers;

import java.io.IOException;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.Groupe;
import net.models.Questionnaire;
import net.models.Utilisateur;
import net.vues.VLogin;

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
			
			TestHttp test=new TestHttp();
			String baseUrl="http://127.0.0.1/rest-QCM/";
								
			try {
				String reponse = test.postClassic(baseUrl+"user/connect", user);
				System.out.println(reponse);
				TestGson gsonLogin=new TestGson();
				ActifUser actif=gsonLogin.jsonToActif(reponse);
				AppController.setActiveUser(actif);
				
				System.out.println(AppController.getActiveUser());
				if(actif.getconnected()==true){
					vLogin.getShell().close();
					AccueilController.vAccueil.getItemConnexion().setEnabled(false);
					AccueilController.vAccueil.getTabGestion().setVisible(true);
					TestHttp send = new TestHttp();
					//String baseUrl="http://127.0.0.1/rest-QCM/";
					try {
						String qcm = send.get(baseUrl+"questionnaires");
						
						TestGson gsonQuestionnaire=new TestGson();
						
						Questionnaire[]  d= gsonQuestionnaire.jsonToAllQuestionnaire(qcm);
						
						for (Questionnaire questionnaire : d) {
							qcm=send.get(baseUrl+"questionnaires/groupe/"+questionnaire.getId());
							Groupe[]  g= gsonQuestionnaire.jsonToAllGroupe(qcm);
							
							for (Groupe groupe : g) {
								qcm=send.get(baseUrl+"groupes/utilisateur/"+groupe.getId());
								Utilisateur[]  u= gsonQuestionnaire.jsonToAllUtilisateur(qcm);
								
								for (Utilisateur utilisateur : u) {
									if (utilisateur.getId().equals(AppController.getActiveUser().getWho())) {
										System.out.println(questionnaire.getLibelle());
										TableItem item = new TableItem(AccueilController.vAccueil.getTable(), SWT.NONE);
										item.setText(new String[] {questionnaire.getLibelle(), groupe.getLibelle()});
									}
								}
							}
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					vLogin.getLblInformation().setText("Erreur dans l'authentification.");
				}
																	
			} catch (IOException e) {
				e.printStackTrace();
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
