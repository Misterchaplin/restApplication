package net.controllers;

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
		
		vAccueil.getBtnAjouterGroupe().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vAccueil.getTxtLibelle().setText("aaaa");
				
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