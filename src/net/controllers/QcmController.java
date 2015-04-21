package net.controllers;

import java.rmi.UnexpectedException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class QcmController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;
	private Integer maxPage=0;
	private Integer page=1;
	private Integer pageIndex=0;
	private Integer countQuestion=0;
	//private Questionnaire session_id=null;
	private int nbTrueAnswer;
	private Groupe[] lesGroupes = null;
	private List<Reponse> lesReponses = new ArrayList<Reponse>();
	private Question[] lesQuestions = null;
	private Reponse[] lesReponse=null;
	private Questionnaire leQuestionnaire=null;
	private Groupe leGroupe=null;
	private Question laQuestion=null;
	private boolean checkQuestGroupe=true;
	private Questionnaire insertQuestionnaire = null;
	private Integer updateQcmGroupe=null;
	private Integer updateQcmQuestionnaire = null;

	public Integer getUpdateQcmGroupe() {
		return updateQcmGroupe;
	}

	public void setUpdateQcmGroupe(Integer updateQcmGroupe) {
		this.updateQcmGroupe = updateQcmGroupe;
	}

	public Integer getUpdateQcmQuestionnaire() {
		return updateQcmQuestionnaire;
	}

	public void setUpdateQcmQuestionnaire(Integer updateQcmQuestionnaire) {
		this.updateQcmQuestionnaire = updateQcmQuestionnaire;
	}

	public QcmController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		
		
		vAccueil.getBtnPrecedent().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(page>1){
					page--;
					pageIndex--;
					initQuestionUpdate(pageIndex);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vAccueil.getBtnSuivant().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(page<maxPage){
					page++;
					pageIndex++;
				//	System.out.println("lindex "+pageIndex);
					initQuestionUpdate(pageIndex);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		vAccueil.getBtnAjouterQcm().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(vAccueil.getBtnAjouterQcm().getText()=="Modifier"){
					UpdateQcm();
				}else{
					AddQcm();
				}
			}
			
		
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});		
		
		vAccueil.getBtnNouveauQuestionnaire().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//String libelleQuest = vAccueil.getTxtQcm().getText();
				vAccueil.getBtnAjouterQuestion().setVisible(false);
				vAccueil.getLblMerciDe().setVisible(false);
				AppController.setSession_Id(null);
				initAddQcm();
				//AddQcm();
				if(updateQcmGroupe!=null){
					//vAccueil.getLblInformation().setText("Le questionnaire "+libelleQuest+" a Ã©tÃ© crÃ©e avec succÃ©s ! ");
					updateQcmGroupe=null;
					updateQcmQuestionnaire=null;
				}
				vAccueil.getLblInformation().setText("Vous pouvez crï¿½er un nouveau formulaire.");
			}
		});
		
		vAccueil.getBtnAjouterQuestion().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(updateQcmQuestionnaire!=null){
					vAccueil.getBtnAjouterQcm().setText("Ajouter");
					addQuestionToUpdate();
				}	
			}
		});
	}
	
	public void initUpdate(){
		if(AppController.getSession_Id()!= null){
			initAddQcm();
			initQuestionnaireUpdate();			
			initGroupeUpdate();
			initQuestionUpdate(0);
			vAccueil.getBtnAjouterQcm().setText("Modifier");
			
		}
	}
	
	public void initReponse(Integer idQuestion){

		lesReponse = Http.getReponsesByQuestion(idQuestion);
		/*for (Reponse reponse : lesReponse) {
			System.out.println("Une rï¿½ponse : "+reponse.getLibelle() + " Check : "+reponse.getGood());
		}*/
		vAccueil.getTxtQcm1().setText(lesReponse[0].getLibelle());
		vAccueil.getTxtQcm2().setText(lesReponse[1].getLibelle());
		vAccueil.getTxtQcm3().setText(lesReponse[2].getLibelle());
		vAccueil.getTxtQcm4().setText(lesReponse[3].getLibelle());
		
		vAccueil.getBtnCkGroupe1().setSelection((lesReponse[0].getGood()) != 0? true : false);
		vAccueil.getBtnCkGroupe2().setSelection((lesReponse[1].getGood()) != 0? true : false);
		vAccueil.getBtnCkGroupe3().setSelection((lesReponse[2].getGood()) != 0? true : false);
		vAccueil.getBtnCkGroupe4().setSelection((lesReponse[3].getGood()) != 0? true : false);
	}
	
	
	public void initQuestionUpdate(Integer index){
		lesQuestions=null;
		lesQuestions = Http.getQuestionByQuestionnaire(leQuestionnaire.getId());
		if(lesQuestions.length>1){
			maxPage=lesQuestions.length;
			vAccueil.getBtnPrecedent().setVisible(true);
			vAccueil.getBtnSuivant().setVisible(true);
		}
		vAccueil.getLblCurrentQuestion().setText(String.valueOf(page));
		vAccueil.getLblLastQuestion().setText(String.valueOf(lesQuestions.length));
		vAccueil.getTxtQuestionQcm().setText(lesQuestions[index].getLibelle());
		initReponse(lesQuestions[index].getId());
	}
	
	public void initQuestionnaireUpdate(){
		leQuestionnaire = Http.getQuestionnaire(AppController.getSession_Id().getId());
		vAccueil.getTxtQcm().setText(leQuestionnaire.getLibelle());
	}
	
	public void initGroupeUpdate(){
		lesGroupes = Http.getAllGroupes();
		leGroupe=Http.getGroupe(AppController.getSessionGroupe_Id().getId());
		Integer count=0;
		vAccueil.getCbvQcm().setInput(lesGroupes);
		
		for (Groupe groupe : lesGroupes) {
			
			if(groupe.getId().equals(leGroupe.getId())){
				vAccueil.getCbQcm().select(count);
			}
			else{
				count++;
			}
		}
	}
	
	/**
	 * On remet tout ï¿½ zï¿½ro
	 */
	public void initAddQcm(){
		vAccueil.getTxtQcm().setEnabled(true);
		vAccueil.getCbQcm().setEnabled(true);
		vAccueil.getTxtQcm().setText("");
		vAccueil.getTxtQuestionQcm().setText("");
		vAccueil.getTxtQcm1().setText("");
		vAccueil.getTxtQcm2().setText("");
		vAccueil.getTxtQcm3().setText("");
		vAccueil.getTxtQcm4().setText("");
		vAccueil.getBtnCkGroupe1().setSelection(false);
		vAccueil.getBtnCkGroupe2().setSelection(false);
		vAccueil.getBtnCkGroupe3().setSelection(false);
		vAccueil.getBtnCkGroupe4().setSelection(false);
		//session_id=null;
		
		countQuestion=0;
		vAccueil.getLblCurrentQuestion().setText(String.valueOf(countQuestion));
		vAccueil.getLblLastQuestion().setText(String.valueOf(countQuestion));
		vAccueil.getCbvQcm().setSelection(null, false);
		vAccueil.getBtnAjouterQcm().setText("Ajouter");
		vAccueil.getBtnPrecedent().setVisible(false);
		vAccueil.getBtnSuivant().setVisible(false);
	
	}
	
	/**
	 * Initialise certaine donnï¿½es pour ajouter un question au questionnaire
	 * dont au prï¿½alable on a cliquï¿½ sur modifier
	 */
	public void addQuestionToUpdate(){
		leQuestionnaire.setId(updateQcmQuestionnaire);
		//session_id=leQuestionnaire;
		AppController.setSession_Id(leQuestionnaire);
		//System.out.println(AppController.getSession_Id());
		lesQuestions = Http.getQuestionByQuestionnaire(leQuestionnaire.getId());
		maxPage=lesQuestions.length;
		maxPage++;
		vAccueil.getLblLastQuestion().setText(String.valueOf(maxPage));
		vAccueil.getLblCurrentQuestion().setText(String.valueOf(maxPage));
		
		vAccueil.getTxtQuestionQcm().setText("");
		vAccueil.getTxtQcm().setEnabled(false);
		vAccueil.getCbQcm().setEnabled(false);
		vAccueil.getTxtQcm1().setText("");
		vAccueil.getTxtQcm2().setText("");
		vAccueil.getTxtQcm3().setText("");
		vAccueil.getTxtQcm4().setText("");
		vAccueil.getBtnCkGroupe1().setSelection(false);
		vAccueil.getBtnCkGroupe2().setSelection(false);
		vAccueil.getBtnCkGroupe3().setSelection(false);
		vAccueil.getBtnCkGroupe4().setSelection(false);
		vAccueil.getBtnPrecedent().setVisible(false);
		vAccueil.getBtnSuivant().setVisible(false);
	//	System.out.println(AppController.getSession_Id());
	}
	
	
	/**
	 * Fonction gï¿½nï¿½ral d'ajout
	 */
	public void AddQcm(){
		checkQuestGroupe=true;
		checkQuestGroupe = beginInsert();
		
		
		if(checkQuestGroupe==true && (nbTrueAnswer==1 || nbTrueAnswer==2)){
			if(AppController.getSession_Id()==null){
				insertQuestionnaire = Http.postQuestionnarie(leQuestionnaire);
				//Association entre groupe et questionnaire
				GroupeQuestionnaire eventGroupeQuestionnaire = addGroupeQuestionnaire(insertQuestionnaire);	
			}
			/*else{
				leQuestionnaire.setId(AppController.getSession_Id().getId());
				Questionnaire updateQuestionnaire = Http.putQuestionnarie(leQuestionnaire);
				if(updateQuestionnaire!=null){
					GroupeQuestionnaire eventGroupeQuestionnaire = updateGroupeQuestionnaire(updateQuestionnaire);
					//System.out.println(eventGroupeQuestionnaire);
				}
			}*/
			
			
			if(AppController.getSession_Id()==null){
				//Insertion d'une question appartenant ï¿½ un questionnaire
				laQuestion.setQuestionnaire_id(insertQuestionnaire.getId());
			}else{
				//Sinon on prend l'id du questionnaire prï¿½cï¿½dent
				laQuestion.setQuestionnaire_id(AppController.getSession_Id().getId());
			}
			
			Question insertQuestion=addQuestion();
			//Insertion des reponses de la question
			boolean insertCheckReponse=addReponse(insertQuestion);
			
			if(AppController.getSession_Id()==null){
				//Si tout est correct alors on associe les prï¿½cï¿½dents ajout ï¿½ l'utilisateur (s'il la relation n'existe pas encore)
				if(insertCheckReponse==true){				
					boolean guCheck=ifGroupeWithUser();
					boolean insertGrood=false;
					if(guCheck==false){
						GroupeUtilisateur insertGroupeUtilisateur = Http.postGroupeUtilisateurs(createGroupeUtilisateur());
						insertGrood=true;
						
					}
					else{
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
			lesReponses.clear();
		}

	}
	
	

	public void UpdateQcm(){
		checkQuestGroupe=true;
		checkQuestGroupe = beginInsert();
				
		if(checkQuestGroupe==true && (nbTrueAnswer==1 || nbTrueAnswer==2)){
			leQuestionnaire.setId(AppController.getSession_Id().getId());
			Questionnaire updateQuestionnaire = Http.putQuestionnarie(leQuestionnaire);
			
			GroupeQuestionnaire eventGroupeQuestionnaire = updateGroupeQuestionnaire(updateQuestionnaire);
			
			laQuestion.setQuestionnaire_id(AppController.getSession_Id().getId());
			Question updateQuestion=updateQuestion();
			//Insertion des reponses de la question
			boolean updateCheckReponse=updateReponse(updateQuestion);
			
			if(updateCheckReponse==true){	
				lesReponses.clear();
				Utils.updateTableViewer();
				vAccueil.getCbvQuestionnaireGroupe().setInput(null);
				Utils.remplirComboQuestionnaire();
			}
			
		}else{
			vAccueil.getLblInformation().setText("Un ou plusieurs champs sont manquants");
			lesReponses.clear();
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
		vAccueil.getLblMerciDe().setVisible(true);
		vAccueil.getBtnNouveauQuestionnaire().setVisible(true);
		AppController.setSession_Id(insertQuestionnaire);
		lesReponses.clear();
		vAccueil.getTxtQuestionQcm().setText("");
		vAccueil.getTxtQcm().setEnabled(false);
		vAccueil.getCbQcm().setEnabled(false);
		vAccueil.getTxtQcm1().setText("");
		vAccueil.getTxtQcm2().setText("");
		vAccueil.getTxtQcm3().setText("");
		vAccueil.getTxtQcm4().setText("");
		vAccueil.getBtnCkGroupe1().setSelection(false);
		vAccueil.getBtnCkGroupe2().setSelection(false);
		vAccueil.getBtnCkGroupe3().setSelection(false);
		vAccueil.getBtnCkGroupe4().setSelection(false);
		vAccueil.getLblInformation().setText("Ajout rÃ©ussie");
		countQuestion++;
		vAccueil.getLblCurrentQuestion().setText(String.valueOf(countQuestion));
		vAccueil.getLblLastQuestion().setText(String.valueOf(countQuestion));
		Utils.updateTableViewer();
		vAccueil.getCbvQuestionnaireGroupe().setInput(null);
		Utils.remplirComboQuestionnaire();
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
	
	/**
	 * Ajout ou mise Ã  jour des rÃ©ponses
	 * @param insertQuestion
	 * @return
	 */
	public boolean addReponse(Question insertQuestion){
		boolean insertCheckReponse=true;
		for (Reponse reponse : lesReponses) {
			
			reponse.setQuestion_id(insertQuestion.getId());
			Reponse insertReponse = Http.postReponse(reponse);
			//System.out.println("id de la question " +insertReponse.getQuestion_id());
			if(insertReponse.getId().equals(null)){
				insertCheckReponse=false;
			}
		}
		return insertCheckReponse;
	}
	
	
	/**
	 * Ajout ou mise Ã  jour des rÃ©ponses
	 * @param insertQuestion
	 * @return
	 */
	public boolean updateReponse(Question updateQuestion){
		boolean updateCheckReponse=true;
		Reponse[] reponsesQuestion=Http.getReponsesByQuestion(updateQuestion.getId());
		Integer nbReponse=0;
		for (Reponse reponse : lesReponses) {
			reponse.setQuestion_id(updateQuestion.getId());
			reponse.setId(reponsesQuestion[nbReponse].getId());
			
			Reponse updateReponse = Http.putReponse(reponse);
			if(updateReponse.getId().equals(null)){
				updateCheckReponse=false;
			}
			nbReponse++;
		}
		return updateCheckReponse;
	}
	
	
	/**
	 * Ajout d'une question
	 * @return L'id de l'insertion
	 */
	public Question addQuestion(){
		Question insertQuestion = Http.postQuestion(laQuestion);
		return insertQuestion;
	}
	
	/**
	 * Mise à jour d'une question
	 * @return L'id de l'élément modifié
	 */
	public Question updateQuestion(){
		page=Integer.valueOf(vAccueil.getLblCurrentQuestion().getText());
		//L'index commencant à 0 et non à 1 on décremante
		page--;
		//Récupérer la question à mettre à jour
		lesQuestions=Http.getQuestionByQuestionnaire(leQuestionnaire.getId());
		Integer nbRound=0;
		for (Question aQuestion : lesQuestions) {
			if(page==nbRound){
				laQuestion.setId(aQuestion.getId());
			}
			nbRound++;
		}
		//System.out.println(laQuestion.getId()+" "+laQuestion.getLibelle());
		Question UpdateQuestion = Http.putQuestion(laQuestion);
		return UpdateQuestion;
	}
	
	public GroupeQuestionnaire addGroupeQuestionnaire(Questionnaire questionnaire){
		GroupeQuestionnaire groupeQuestionnaire = new GroupeQuestionnaire();
		groupeQuestionnaire.setGroupe_id(leGroupe.getId());
		groupeQuestionnaire.setQuestionnaire_id(questionnaire.getId());
		GroupeQuestionnaire updateGroupeQuestionnaire = Http.postGroupeQuestionnaires(groupeQuestionnaire);
		if(updateGroupeQuestionnaire.equals(null)){
			System.out.println("Erreur dans l'insertion des groupes questionnaire");
		}
		return updateGroupeQuestionnaire;
	}
	
	/**
	 * Mettre à jour l'association entre groupe et questionnaire
	 * @param questionnaire
	 * @return
	 */
	public GroupeQuestionnaire updateGroupeQuestionnaire(Questionnaire questionnaire){
		GroupeQuestionnaire updateGroupeQuestionnaire=null;
		GroupeQuestionnaire groupeQuestionnaire = new GroupeQuestionnaire();  
		groupeQuestionnaire.setGroupe_id(leGroupe.getId());
		groupeQuestionnaire.setQuestionnaire_id(questionnaire.getId());
		updateGroupeQuestionnaire = Http.postGroupeQuestionnaires(groupeQuestionnaire);
		System.out.println("Insértion : "+groupeQuestionnaire);
		
		GroupeQuestionnaire[] gq=Http.getGroupesByQuestionnaire(groupeQuestionnaire.getQuestionnaire_id());
		Integer count=0;
		for (GroupeQuestionnaire groupeQuestionnaire2 : gq) {
			System.out.println("La boucle : "+groupeQuestionnaire2.getGroupe_id()+" "+groupeQuestionnaire2.getQuestionnaire_id());
			if(groupeQuestionnaire2.getGroupe_id().equals(AppController.getSessionGroupe_Id().getId())){
				groupeQuestionnaire.setId(groupeQuestionnaire2.getId());
				//Http.delGroupeQuestionnare(groupeQuestionnaire);
				System.out.println("Dans if : "+groupeQuestionnaire2.getId()+" "+groupeQuestionnaire2.getGroupe_id()+" "+groupeQuestionnaire2.getQuestionnaire_id());
				count++;
			}
			/*else{
				if(groupeQuestionnaire2.)
				Http.delGroupeQuestionnare(groupeQuestionnaire2);
			}*/
		}
		if(count>1){
			updateGroupeQuestionnaire = Http.delGroupeQuestionnare(groupeQuestionnaire);
		}
		return updateGroupeQuestionnaire;
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
	
	/*public void startUpdate(){
		if(AppController.getSession_Id()!= null){
			System.out.println("deux");
			initAddQcm();
			initQuestionnaireUpdate();			
			initGroupeUpdate();
			initQuestionUpdate(0);
			vAccueil.getBtnAjouterQcm().setText("Modifier");
			
		}
	}*/

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

}