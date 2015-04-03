package net.controllers;

import java.io.IOException;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.ActifUser;
import net.models.Questionnaire;
import net.vues.VAccueil;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;

public class GroupeController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;

	public GroupeController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		

		TestHttp test = new TestHttp();
		String baseUrl="http://127.0.0.1/rest-QCM/";
		try {
			qcm=test.get(baseUrl+"questionnaires");
			
			//System.out.println(qcm);
			TestGson gsonQuestionnaire=new TestGson();
		/*	Questionnaire h= gsonQuestionnaire.jsonToQuestionnaire(qcm);
			System.out.println(h);*/
			Questionnaire[]  d= gsonQuestionnaire.jsonToAllQuestionnaire(qcm);
			//vAccueil.getCbvQuestionnaireGroupe().setContentProvider(ArrayContentProvider.getInstance());
			
			
			for (Questionnaire questionnaire : d) {
				//TableItem item = new TableItem(vAccueil.getTable(), SWT.NONE);
				//ComboViewer item = new ComboViewer(vAccueil.getTable(), SWT.NONE);
				//vAccueil.getCbQuestionnaireGroupe().setItems(new String[] {questionnaire.getLibelle()});
				//vAccueil.getCbQuestionnaireGroupe().setText(questionnaire.getLibelle());
				vAccueil.getCbvQuestionnaireGroupe().add(questionnaire.getLibelle());
				//item.setInput(new String[] {questionnaire.getLibelle()});
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vAccueil.getBtnAjouterGroupe().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vAccueil.getLblInformation().setText("Hello !!! ");
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