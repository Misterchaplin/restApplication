package net.controllers;

import net.models.CollectionQuestionnaireGroupe;
import net.models.GroupeQuestionnaire;
import net.vues.VAccueil;
import net.vues.VLogin;
import net.technics.Http;
import net.technics.Utils;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class AccueilController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;
	protected Display display;
	private CollectionQuestionnaireGroupe selectedQuestionnaire;

	public AccueilController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		vAccueil.getTabGestion().setVisible(true);
		/*vAccueil.getTabGestion().getTabList()[0].setEnabled(false);
		vAccueil.getTabGestion().getTabList()[1].setEnabled(false);
		vAccueil.getTabGestion().getTabList()[2].setEnabled(false);*/
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
					/*vLogin.getShell().addKeyListener(new KeyListener() {
						
						@Override
						public void keyPressed(KeyEvent arg0) {
							System.out.println("fff");
								String res = "";
								switch (arg0.character) {
									case SWT.CR :
									res = "Touche Entree";
									break;
									case SWT.ESC :
									res = "Touche Echap";
									break;
									default :
								}
								System.out.println(res);
							}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub
							
						}
					}); 
					while (!vLogin.getShell().isDisposed())
						if (!display.readAndDispatch())
							display.sleep();
	
						display.dispose();*/
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});	
		
		vAccueil.getBtnSupprimerAccueil().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				try {
					selectedQuestionnaire=selectionTableViewer();
					String idGrpQuest = selectedQuestionnaire.getQuestionnaire_id()+ "_" +selectedQuestionnaire.getGroupe_id();
					Integer idQuestionnaire = selectedQuestionnaire.getQuestionnaire_id();
					
					GroupeQuestionnaire[] test = Http.getCIMGrpQst(idGrpQuest);
					 
					Shell shell = new Shell(display);
				    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING |SWT.YES | SWT.NO);
				    messageBox.setMessage("Etes-vous sur de vouloir supprimer "+selectedQuestionnaire.getQuestionnaire_libelle()+" ?");
				   
				    int rc = messageBox.open();
				    
				    if (rc == SWT.YES){
				    	
				    	for (GroupeQuestionnaire gq : test) {
							Http.deleteCIMGroupeQuestionnaire(gq.getId(),idGrpQuest,idQuestionnaire);
						}
						
						vAccueil.getLblInformation().setText("Suppression de "+selectedQuestionnaire.getQuestionnaire_libelle()+ " du groupe "+selectedQuestionnaire.getGroupe_libelle());
						
						Utils.updateTableViewer();
				    }
					
				} catch (NullPointerException npe1) {
					vAccueil.getLblInformation().setText("Veuillez saisir un questionnaire !");
				}
				
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
			
		});	
		
		vAccueil.getBtnModifierAccueil().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					selectedQuestionnaire=selectionTableViewer();
					
					Shell shell = new Shell(display);
				    MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION|SWT.YES | SWT.NO);
				    messageBox.setMessage("Voulez-vous modifier le questionnaire : "+selectedQuestionnaire.getQuestionnaire_libelle()
				    		+ " ?\n\nPour modifier le groupe : "+selectedQuestionnaire.getGroupe_libelle()+"\ncliquer non.");
				   
				    int rc = messageBox.open();
				    if (rc == SWT.YES){
				    	vAccueil.getTabGestion().setSelection(1);
				    	QcmController qcmController = new QcmController(vAccueil);
				    	/*qcmController.setUpdateQcmQuestionnaire(selectedQuestionnaire.getQuestionnaire_id());
				    	qcmController.setUpdateQcmGroupe(selectedQuestionnaire.getGroupe_id());*/
				    	AppController.setSession_Id(Http.getQuestionnaire(selectedQuestionnaire.getQuestionnaire_id()));
				    	AppController.setSessionGroupe_Id(Http.getGroupe(selectedQuestionnaire.getGroupe_id()));
				    	vAccueil.getBtnAjouterQuestion().setVisible(true);
				    	vAccueil.getBtnAjouterQcm().setText("Modifier");
				    	vAccueil.getBtnNouveauQuestionnaire().setVisible(true);
						vAccueil.getLblMerciDe().setVisible(true);
						vAccueil.getLblMerciDe().setText("Pour ajouter un nouveau questionnaire vous devez d'abord appuyer sur terminer.");
						qcmController.initUpdate();
				    }
				    if (rc == SWT.NO){
				    	vAccueil.getTabGestion().setSelection(2);
				    	GroupeController groupeController = new GroupeController(vAccueil);
				    	groupeController.setUpdateGroupe(selectedQuestionnaire.getGroupe_id());
				    	groupeController.setUpdateQcmQuestionnaire(selectedQuestionnaire.getQuestionnaire_id());
				    	groupeController.init();
				    	
				    }
				
				} catch (NullPointerException npe2) {
					vAccueil.getLblInformation().setText("Veuillez saisir un questionnaire !");
				}
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});	
		
	}
	
	public CollectionQuestionnaireGroupe selectionTableViewer(){
		StructuredSelection selection = (StructuredSelection) vAccueil.getTableViewer().getSelection();
		CollectionQuestionnaireGroupe selectedQuestionnaire = (CollectionQuestionnaireGroupe) selection.getFirstElement();
		
		return selectedQuestionnaire;
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