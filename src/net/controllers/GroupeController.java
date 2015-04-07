package net.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.gson.TestGson;
import net.http.TestHttp;
import net.models.Groupe;
import net.models.GroupeQuestionnaire;
import net.models.GroupeUtilisateur;
import net.models.Questionnaire;
import net.technics.Http;
import net.vues.VAccueil;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class GroupeController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;

	public GroupeController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {

		final TestHttp test = new TestHttp();
		final String baseUrl="http://127.0.0.1/rest-QCM/";
		
		Questionnaire[] lesQuestionnaires = Http.getAllQuestionnaires();
			
		for (Questionnaire questionnaire : lesQuestionnaires) {
			vAccueil.getCbvQuestionnaireGroupe().add(questionnaire.getLibelle());
		}
		
		vAccueil.getBtnAjouterGroupe().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
	//////////////////Ajout d'un Groupe/////////////////////////
				//on créer groupe
				Groupe groupe = new Groupe();
				groupe.setLibelle(vAccueil.getTxtLibelle().getText());
				groupe.setCode(vAccueil.getTxtCode().getText());
				
				if(groupe.getLibelle()!= "" && groupe.getCode()!=""){
					String groupeInsert = null;
					try {
						//Insertion d'un nouveau groupe
						groupeInsert = test.post(baseUrl+"groupes/", groupe);
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					TestGson gsonGroupe=new TestGson();
					//On convertie en groupe l'id retourné
					Groupe  gr= gsonGroupe.jsonToGroupe(groupeInsert);
					
					if(vAccueil.getCbQuestionnaireGroupe().getText()!=""){
			////////////////On récupère l'id du questionnaire/////////////////////
						Integer resultWhatQuest=null;
						try {
							//On récupère tous le questionnaire
							qcm=test.get(baseUrl+"questionnaires/qcmwithlibelle/"+vAccueil.getCbQuestionnaireGroupe().getText());
							TestGson gsonAQuestionnaire=new TestGson();
							Questionnaire[] d= gsonAQuestionnaire.jsonToAllQuestionnaire(qcm);
							
							for (Questionnaire questionnaire : d) {
								resultWhatQuest=questionnaire.getId();
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				//On instancie un groupe pour questionnaire
						GroupeQuestionnaire groupQuest= new GroupeQuestionnaire();
						groupQuest.setQuestionnaire_id(resultWhatQuest);
						groupQuest.setGroupe_id(gr.getId());
				
						try {
							//Ajoute du groupe au questionnaire
							String groupeQuestInsert = test.post(baseUrl+"groupequestionnaires/", groupQuest);
							//TestGson gsonGroupeQuest=new TestGson();
							//GroupeQuestionnaire  grqu= gsonGroupeQuest.jsonToGroupeQuestionnaire(groupeQuestInsert);
						} catch (Exception e2) {
							// TODO: handle exception
						}
							
						//Association de groupe avec utilisateur
						GroupeUtilisateur groupeUser= new GroupeUtilisateur();
						groupeUser.setGroupe_id(gr.getId());
						groupeUser.setUtilisateur_id(AppController.getActiveUser().getWho());
						System.out.println(groupeUser);
						try {
							//Insertion du groupepour utilsiateur 
							String groupeUtilisateurInsert = test.post(baseUrl+"groupeutilisateurs/", groupeUser);
							vAccueil.getLblInformation().setText("Ajout réussie");
							//TestGson gsonGroupeUser=new TestGson();
							//GroupeUtilisateur  grut= gsonGroupeUser.jsonToGroupeUtilisateur(groupeUtilisateurInsert);
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
					else{
						vAccueil.getLblInformation().setText("Ajout réussie");
					}
				}
				else{
					vAccueil.getLblInformation().setText("Veuillez remplir le libelle et le code.");
				}
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