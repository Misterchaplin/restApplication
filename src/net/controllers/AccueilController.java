package net.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.http.TestHttp;
import net.models.Domaine;
import net.models.Utilisateur;
import net.vues.VAccueil;






import org.apache.http.client.ClientProtocolException;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.google.gson.reflect.TypeToken;

public class AccueilController implements SelectionListener {
	public static VAccueil vAccueil;

	public AccueilController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		// onglet connexion
		vAccueil.getItemConnexion().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// si on se connecte
				if (vAccueil.getItemConnexion().getText().equals("Connexion")) {
					vAccueil.getGrpConnexion().setVisible(true);
					vAccueil.getBtnConnect().addSelectionListener(new SelectionListener() {
						
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							Utilisateur user= new Utilisateur();
							user.setLogin(vAccueil.getTxtLogin().getText());
							user.setPassword(vAccueil.getTxtMdp().getText());
							//System.out.println(user);
							TestHttp test=new TestHttp();
							String baseUrl="http://localhost/rest-qcm/";
	
							try {
								//Domaine dom=test.getGson().fromJson(test.get(baseUrl+"domaines/2"), Domaine.class);
							//	dom.setLibelle(dom.getLibelle()+" modifi�");
							//	System.out.println(dom.getLibelle());
								String reponse=test.get(baseUrl+"domaines");
								ArrayList<Domaine> domaines = test.getGson().fromJson(reponse, new TypeToken<List<Domaine>>(){}.getType());
								//System.out.println(domaines);
								
								
								for (Domaine domaine : domaines) {
									if(domaine.getLibelle().startsWith("domaine1")){
										System.out.println(domaine.getId());
									}
								}
									
									
								
								//System.out.println(reponse);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}
					});
					// on ouvre la fenêtre seulement si elle n'est pas déjà
					// ouverte
					/*if (nbOpenedWindowConnection == 0) {
						nbOpenedWindowConnection = 1;
						VLogin vLogin = new VLogin();
						LoginController loginController = new LoginController(vLogin);
						vLogin.init();
						loginController.init();
						vLogin.open();
					}*/
				}
				// si on se déconnecte
				/*else {
					AppController.setActiveUser(null);
					vAccueil.getItemConnexion().setText("Connexion");
					vAccueil.getItemMonProfil().setEnabled(false);
					vAccueil.getBtnProducts().setEnabled(false);
					vAccueil.getBtnCollaborators().setEnabled(false);
					vAccueil.getItemMonProfil().setText("");
					vAccueil.getLblInformation().setText("");
					vAccueil.getItemLogin().setText("");
					vAccueil.getGrpProduits().setVisible(false);
					vAccueil.getGrpCollaborateurs().setVisible(false);
				}*/

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// onglet collaborateurs
		/*vAccueil.getBtnProducts().addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// on ouvre la fenêtre seulement si elle n'est pas déjà ouverte
				if (nbOpenedWindowProduit == 0) {
					nbOpenedWindowProduit = 1;
					VListProduits vListProduits = new VListProduits();
					ProduitController produitController = new ProduitController(vListProduits);
					vListProduits.init();
					produitController.init();
					vListProduits.open();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});*/
		
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
