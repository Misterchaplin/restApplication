package net.controllers;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.models.Groupe;
import net.models.GroupeQuestionnaire;
import net.models.GroupeUtilisateur;
import net.models.Question;
import net.models.Questionnaire;
import net.models.Reponse;
import net.technics.Http;
import net.technics.Utils;
import net.vues.VAccueil;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class QcmController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;
	private Questionnaire session_id=null;
	private int nbTrueAnswer;
	private List<Reponse> lesReponses = new ArrayList<Reponse>();
	private Questionnaire leQuestionnaire=null;
	private Groupe leGroupe=null;
	private Question laQuestion=null;
	private boolean checkQuestGroupe=true;
	private Questionnaire insertQuestionnaire = null;
	
	public QcmController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		
		vAccueil.getBtnAjouterQcm().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkQuestGroupe = beginInsert();
				
				if(checkQuestGroupe==true && (nbTrueAnswer==1 || nbTrueAnswer==2)){
					if(session_id==null){
						insertQuestionnaire = Http.postQuestionnarie(leQuestionnaire);
						//Association entre groupe et questionnaire
						GroupeQuestionnaire eventGroupeQuestionnaire = addOrUpdateGroupeQuestionnaire(insertQuestionnaire);
						
					}
					if(session_id==null){
						//Insertion d'une question appartenant à un questionnaire
						laQuestion.setQuestionnaire_id(insertQuestionnaire.getId());
					}else{
						//Sinon on prend l'id du questionnaire précédent
						laQuestion.setQuestionnaire_id(session_id.getId());
					}
					
					Question insertQuestion=addOrUpdateQuestion();
					//Insertion des reponses de la question
					boolean insertCheckReponse=addOrUpdateReponse(insertQuestion);
					
					if(session_id==null){
						//Si tout est correct alors on associe les précédents ajout à l'utilisateur (s'il la relation n'existe pas encore)
						if(insertCheckReponse==true){				
							boolean guCheck=ifGroupeWithUser();
							boolean insertGrood=false;
							if(guCheck==false){
								GroupeUtilisateur insertGroupeUtilisateur = Http.postGroupeUtilisateurs(createGroupeUtilisateur());
								vAccueil.getLblInformation().setText("Ajout réussie");
								Utils.updateTableViewer();
								insertGrood=true;
								
							}
							else{
								vAccueil.getLblInformation().setText("Ajout réussie");
								Utils.updateTableViewer();
								insertGrood=true;
							}
							
							if(insertGrood==true){
								endInsert();
							}
						}
					}else{
						endInsert();
					}
				}else{
					vAccueil.getLblInformation().setText("Un ou plusieurs champs sont manquants");
				}
	
			}
			
			public boolean beginInsert(){
				Integer stateAnswer=createReponse();
				
				if(stateAnswer!=0){
					leQuestionnaire=createQuestionnaire();
					leGroupe=createGroupe();
					laQuestion=createQuestion();
					if(leQuestionnaire.getLibelle().isEmpty() || laQuestion.getLibelle().isEmpty()){
						checkQuestGroupe=false;
					}
					if(leGroupe.getId()==null){
						checkQuestGroupe=false;
					}
				}
				return checkQuestGroupe;
			}
			
			public void endInsert(){
				vAccueil.getBtnNouveauQuestionnaire().setVisible(true);
				session_id=insertQuestionnaire;
				System.out.println(session_id);
				vAccueil.getTxtQuestionQcm().setText("");
				vAccueil.getTxtQcm1().setText("");
				vAccueil.getTxtQcm2().setText("");
				vAccueil.getTxtQcm3().setText("");
				vAccueil.getTxtQcm4().setText("");
				vAccueil.getBtnCkGroupe1().setSelection(false);
				vAccueil.getBtnCkGroupe2().setSelection(false);
				vAccueil.getBtnCkGroupe3().setSelection(false);
				vAccueil.getBtnCkGroupe4().setSelection(false);
			}
			
			public boolean ifGroupeWithUser(){
				GroupeUtilisateur leGroupUser=createGroupeUtilisateur();
				GroupeUtilisateur[] getGroupeUtilisateur = Http.getGroupeUtilisateur(leGroupUser.getUtilisateur_id());
				boolean guCheck = false;
				for (GroupeUtilisateur gu : getGroupeUtilisateur) {
					if((leGroupUser.getGroupe_id()==gu.getGroupe_id()) && leGroupUser.getUtilisateur_id()==gu.getUtilisateur_id()){
						guCheck=true;
					}
				}
				
				return guCheck;
			}
			
			public GroupeUtilisateur createGroupeUtilisateur(){
				GroupeUtilisateur groupeUtilisateur = new GroupeUtilisateur();
				groupeUtilisateur.setGroupe_id(leGroupe.getId());
				groupeUtilisateur.setUtilisateur_id(AppController.getActiveUser().getWho());
				return groupeUtilisateur;
			}
			
			public boolean addOrUpdateReponse(Question insertQuestion){
				boolean insertCheckReponse=true;
				for (Reponse reponse : lesReponses) {
					reponse.setQuestion_id(insertQuestion.getId());
					Reponse insertReponse = Http.postReponse(reponse);
					if(insertReponse.getId().equals(null)){
						insertCheckReponse=false;
					}
				}
				return insertCheckReponse;
			}
			
			public Question addOrUpdateQuestion(){
				Question insertQuestion = Http.postQuestion(laQuestion);
				return insertQuestion;
			}
			
			public GroupeQuestionnaire addOrUpdateGroupeQuestionnaire(Questionnaire questionnaire){
				GroupeQuestionnaire groupeQuestionnaire = new GroupeQuestionnaire();
				groupeQuestionnaire.setGroupe_id(leGroupe.getId());
				groupeQuestionnaire.setQuestionnaire_id(questionnaire.getId());
				GroupeQuestionnaire insertGroupeQuestionnaire = Http.postGroupeQuestionnaires(groupeQuestionnaire);
				if(insertGroupeQuestionnaire.equals(null)){
					System.out.println("Erreur dans l'insertion des reponses");
				}
				return insertGroupeQuestionnaire;
			}
			
			public Groupe createGroupe(){
				IStructuredSelection selection = (IStructuredSelection) vAccueil.getCbvQcm().getSelection();
		        Groupe element = (Groupe)selection.getFirstElement();
				Groupe groupe = new Groupe();
				if(selection.size()==1){
					groupe.setId(element.getId());
				}
				return groupe;
			}
			
			public Questionnaire createQuestionnaire(){
				Questionnaire questionnaire = new Questionnaire();
				questionnaire.setLibelle(vAccueil.getTxtQcm().getText());
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				questionnaire.setDate(dateFormat.format(date));
				
				return questionnaire;
			}
			
			public Question createQuestion(){
				Question question = new Question();
				question.setLibelle(vAccueil.getTxtQuestionQcm().getText());
				return question;
			}
			
			public int createReponse(){
				Reponse reponse1 = new Reponse();
				reponse1.setLibelle(vAccueil.getTxtQcm1().getText());
				reponse1.setGood((vAccueil.getBtnCkGroupe1().getSelection()) ? 1 : 0);
				Reponse reponse2 = new Reponse();
				reponse2.setLibelle(vAccueil.getTxtQcm2().getText());
				reponse2.setGood((vAccueil.getBtnCkGroupe2().getSelection()) ? 1 : 0);
				Reponse reponse3 = new Reponse();
				reponse3.setLibelle(vAccueil.getTxtQcm3().getText());
				reponse3.setGood((vAccueil.getBtnCkGroupe3().getSelection()) ? 1 : 0);
				Reponse reponse4 = new Reponse();
				reponse4.setLibelle(vAccueil.getTxtQcm4().getText());
				reponse4.setGood((vAccueil.getBtnCkGroupe4().getSelection()) ? 1 : 0);
				
				
				lesReponses.add(reponse1);
				lesReponses.add(reponse2);
				lesReponses.add(reponse3);
				lesReponses.add(reponse4);
				
				nbTrueAnswer=0;
				boolean check=true;
				if(reponse1.getLibelle().isEmpty() || reponse2.getLibelle().isEmpty() || 
					reponse3.getLibelle().isEmpty() || reponse4.getLibelle().isEmpty()){
					check=false;
				}
				else{
					
					if(reponse1.getGood()==1){
						nbTrueAnswer++;
					}
					if(reponse2.getGood()==1){
						nbTrueAnswer++;
					}
					if(reponse3.getGood()==1){
						nbTrueAnswer++;
					}
					if(reponse4.getGood()==1){
						nbTrueAnswer++;
					}
				}
				return nbTrueAnswer;
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