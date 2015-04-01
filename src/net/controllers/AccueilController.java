package net.controllers;

import java.io.IOException;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.Questionnaire;
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
		TestHttp test = new TestHttp();
		String baseUrl="http://127.0.0.1/rest-QCM/";
		try {
			qcm=test.get(baseUrl+"questionnaires/groupe");
			
			System.out.println(qcm);
			TestGson gsonQuestionnaire=new TestGson();
		/*	Questionnaire h= gsonQuestionnaire.jsonToQuestionnaire(qcm);
			System.out.println(h);*/
			Questionnaire[]  d= gsonQuestionnaire.jsonToAllQuestionnaire(qcm);
			for (Questionnaire questionnaire : d) {
				System.out.println(questionnaire);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
		qcm=test.get(baseUrl+"questionnaires");
		
	//	System.out.println(qcm);
		TestGson gsonQuestionnaire=new TestGson();
	/*	Questionnaire h= gsonQuestionnaire.jsonToQuestionnaire(qcm);
		System.out.println(h);*/
		Questionnaire[]  d= gsonQuestionnaire.jsonToAllQuestionnaire(qcm);
		for (Questionnaire questionnaire : d) {
			TableItem item = new TableItem(vAccueil.getTable(), SWT.NONE);
			item.setText(new String[] {questionnaire.getLibelle(), "ici groupe"});
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		
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
					
				//	vAccueil.getTvAccueil().sett
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