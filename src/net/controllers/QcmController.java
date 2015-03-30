package net.controllers;

import net.vues.VAccueil;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class QcmController implements SelectionListener {
	public static VAccueil vAccueil;
	private String qcm;

	public QcmController(VAccueil vAccueil) {
		this.vAccueil = vAccueil;
	}

	public void init() {
		
		vAccueil.getBtnAjouterQcm().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vAccueil.getTxtQcm().setText("aaaa");
				
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