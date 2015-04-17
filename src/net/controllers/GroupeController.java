package net.controllers;

import net.http.TestHttp;
import net.models.Groupe;
import net.models.GroupeQuestionnaire;
import net.models.GroupeUtilisateur;
import net.models.Questionnaire;
import net.technics.Http;
import net.technics.Utils;
import net.vues.VAccueil;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
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
		
		
		
		vAccueil.getBtnAjouterGroupe().addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void widgetSelected(SelectionEvent e) {
	
				//on cr�er groupe et le remplit
				Groupe groupe = new Groupe();
				groupe.setLibelle(vAccueil.getTxtLibelle().getText());
				groupe.setCode(vAccueil.getTxtCode().getText());
				
				//R�cup�ration du questionnaire s�l�ctionn�
				IStructuredSelection selection = (IStructuredSelection) vAccueil.getCbvQuestionnaireGroupe().getSelection();
	            Questionnaire element = (Questionnaire)selection.getFirstElement();
				
				if(groupe.getLibelle()!= "" && groupe.getCode()!=""){
					Groupe InsertGroupe=Http.postGroupe(groupe);
					//Association de groupe avec utilisateur
					GroupeUtilisateur groupeUser= new GroupeUtilisateur();
					groupeUser.setGroupe_id(InsertGroupe.getId());
					groupeUser.setUtilisateur_id(AppController.getActiveUser().getWho());
					GroupeUtilisateur insertGu=Http.postGroupeUtilisateurs(groupeUser);
					
					if(insertGu!=null){
						//Si un questionnaire est choisi
						if(element instanceof Questionnaire){
							//On instancie un groupe pour questionnaire
							GroupeQuestionnaire groupQuest= new GroupeQuestionnaire();
							groupQuest.setQuestionnaire_id(element.getId());
							groupQuest.setGroupe_id(InsertGroupe.getId());
							GroupeQuestionnaire insertGroupeQuestionnaires=Http.postGroupeQuestionnaires(groupQuest);
							
							if(insertGu!=null){
								vAccueil.getLblInformation().setText("Ajout r�ussie");
								vAccueil.getCbvQcm().setInput(null);
								vAccueil.getTxtLibelle().setText("");
								vAccueil.getTxtCode().setText("");
								vAccueil.getCbvQuestionnaireGroupe().setSelection(null, false);
								Utils.remplirComboGroupe();
							}
							else{
								vAccueil.getLblInformation().setText("Erreur pendant l'ajout");	
							}
						}
						else{
							vAccueil.getLblInformation().setText("Ajout r�ussie");
							vAccueil.getCbvQcm().setInput(null);
							vAccueil.getTxtLibelle().setText("");
							vAccueil.getTxtCode().setText("");
							vAccueil.getCbvQuestionnaireGroupe().setSelection(null, false);
							Utils.remplirComboGroupe();
						}
					}else{
						vAccueil.getLblInformation().setText("Erreur pendant l'ajout");	
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