package net.vues;

import org.eclipse.jface.layout.AbstractColumnLayout;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.internal.forms.widgets.SWTUtil;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;

public class VAccueil {

	protected Shell accueil;
	private ToolItem itemConnexion;
	private ToolItem itemLogin;
	private Group grpInformation;
	private Label lblInformation;
	private TableColumnLayout tLayout;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	
	public Group getGrpInformation() {
		return grpInformation;
	}

	public Label getLblInformation() {
		return lblInformation;
	}

	public Shell getAccueil() {
		return accueil;
	}

	public ToolItem getItemConnexion() {
		return itemConnexion;
	}

	public ToolItem getItemLogin() {
		return itemLogin;
	}


	/**
	 * Open the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void init() {
		createContents();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		accueil.open();
		accueil.layout();
		while (!accueil.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		accueil = new Shell();
		accueil.setSize(626, 517);
		accueil.setText("QCM");
		accueil.setLayout(new FormLayout());

		ToolBar menuConnexion = new ToolBar(accueil, SWT.FLAT | SWT.RIGHT);
		FormData fd_menuConnexion = new FormData();
		fd_menuConnexion.right = new FormAttachment(100, -10);
		fd_menuConnexion.top = new FormAttachment(0, 10);
		menuConnexion.setLayoutData(fd_menuConnexion);

		itemLogin = new ToolItem(menuConnexion, SWT.NONE);
		itemLogin.setEnabled(false);

		itemConnexion = new ToolItem(menuConnexion, SWT.NONE);
		itemConnexion.setText("Connexion");
		
		grpInformation = new Group(accueil, SWT.NONE);
		fd_menuConnexion.bottom = new FormAttachment(grpInformation, -1);
		grpInformation.setText("Information :");
		FormData fd_grpInformation = new FormData();
		fd_grpInformation.top = new FormAttachment(0, 34);
		fd_grpInformation.left = new FormAttachment(0, 10);
		fd_grpInformation.right = new FormAttachment(100, -10);
		grpInformation.setLayoutData(fd_grpInformation);
		
		lblInformation = new Label(grpInformation, SWT.NONE);
		lblInformation.setBounds(10, 21, 490, 15);
		
		TabFolder tabGestion = new TabFolder(accueil, SWT.NONE);
		fd_grpInformation.bottom = new FormAttachment(tabGestion, -1);
		FormData fd_tabGestion = new FormData();
		fd_tabGestion.left = new FormAttachment(0, 10);
		fd_tabGestion.right = new FormAttachment(100, -10);
		fd_tabGestion.top = new FormAttachment(0, 81);
		fd_tabGestion.bottom = new FormAttachment(100);
		tabGestion.setLayoutData(fd_tabGestion);
		
		TabItem tbtmAccueil = new TabItem(tabGestion, SWT.NONE);
		tbtmAccueil.setText("Accueil");
		
		Composite composite_2 = new Composite(tabGestion, SWT.NONE);
		tbtmAccueil.setControl(composite_2);
		
		TabItem tbtmQcm = new TabItem(tabGestion, SWT.NONE);
		tbtmQcm.setText("QCM");
		
		Composite composite = new Composite(tabGestion, SWT.NONE);
		tbtmQcm.setControl(composite);
		
		Group grpAjouterQcm = new Group(composite, SWT.NONE);
		grpAjouterQcm.setText("Ajouter QCM");
		grpAjouterQcm.setBounds(10, 28, 435, 319);
		
		Label lblNomQcm = new Label(grpAjouterQcm, SWT.NONE);
		lblNomQcm.setBounds(10, 42, 64, 15);
		lblNomQcm.setText("Nom QCM :");
		
		text = new Text(grpAjouterQcm, SWT.BORDER);
		text.setBounds(80, 36, 136, 21);
		
		Label lblQuestion = new Label(grpAjouterQcm, SWT.NONE);
		lblQuestion.setBounds(10, 84, 55, 15);
		lblQuestion.setText("Question :");
		
		text_1 = new Text(grpAjouterQcm, SWT.BORDER);
		text_1.setBounds(80, 78, 316, 21);
		
		Group grpReponse = new Group(grpAjouterQcm, SWT.NONE);
		grpReponse.setText("Reponse");
		grpReponse.setBounds(6, 137, 390, 168);
		
		text_2 = new Text(grpReponse, SWT.BORDER);
		text_2.setBounds(10, 43, 117, 21);
		
		text_3 = new Text(grpReponse, SWT.BORDER);
		text_3.setBounds(10, 110, 117, 21);
		
		text_4 = new Text(grpReponse, SWT.BORDER);
		text_4.setBounds(232, 43, 102, 21);
		
		text_5 = new Text(grpReponse, SWT.BORDER);
		text_5.setBounds(232, 110, 102, 21);
		
		Button btnCheck1 = new Button(grpReponse, SWT.CHECK);
		btnCheck1.setText("1");
		btnCheck1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCheck1.setBounds(133, 43, 35, 21);
		
		Button btnCheck3 = new Button(grpReponse, SWT.CHECK);
		btnCheck3.setText("3");
		btnCheck3.setBounds(133, 112, 35, 16);
		
		Button btnCheck2 = new Button(grpReponse, SWT.CHECK);
		btnCheck2.setBounds(343, 48, 37, 16);
		btnCheck2.setText("2");
		
		Button btnCheck4 = new Button(grpReponse, SWT.CHECK);
		btnCheck4.setLocation(344, 112);
		btnCheck4.setSize(36, 16);
		btnCheck4.setText("4");
		
		Label lblGroupe = new Label(grpAjouterQcm, SWT.NONE);
		lblGroupe.setBounds(244, 42, 55, 15);
		lblGroupe.setText("Groupe :");
		
		ComboViewer comboViewer = new ComboViewer(grpAjouterQcm, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.setBounds(305, 36, 91, 23);
		
		Button btnAjouter = new Button(composite, SWT.NONE);
		btnAjouter.setBounds(460, 124, 75, 25);
		btnAjouter.setText("Ajouter");
		
		TabItem tbtmGroupe = new TabItem(tabGestion, SWT.NONE);
		tbtmGroupe.setText("Groupe");
		
		Composite composite_1 = new Composite(tabGestion, SWT.NONE);
		tbtmGroupe.setControl(composite_1);
		
		/*createColumnNom(tbAccueil, "Libelle", 1);
		createColumnDate(tbAccueil, "Date", 1);
		createColumnGroupe(tbAccueil, "Groupe", 1);*/
	}
	public Table getTvAccueil() {
		return getTvAccueil();
	}
}
