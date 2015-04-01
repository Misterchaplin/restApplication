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
import org.eclipse.wb.swt.TableViewerColumnSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class VAccueil {

	protected Shell accueil;
	private ToolItem itemConnexion;
	private ToolItem itemLogin;
	private Group grpInformation;
	private Label lblInformation;
	private TableColumnLayout tLayout;
	private Text txtQcm;
	private Text txtQuestionQcm;
	private Text txtQcm1;
	private Text txtQcm3;
	private Text txtQcm2;
	private Text txtQcm4;
	private Table table;
	public Table getTable() {
		return table;
	}

	private Text txtLibelle;
	
	public Text getTxtQcm() {
		return txtQcm;
	}

	public Text getTxtLibelle() {
		return txtLibelle;
	}

	private Text txtCode;
	private Button btnAjouterGroupe;
	private Button btnAjouterQcm;
	private TableViewerColumn tableViewerColumn;
	private ComboViewer cbvQuestionnaireGroupe;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Combo cbQuestionnaireGroupe;
	
	public Combo getCbQuestionnaireGroupe() {
		return cbQuestionnaireGroupe;
	}

	public ComboViewer getCbvQuestionnaireGroupe() {
		return cbvQuestionnaireGroupe;
	}

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
	public Button getBtnAjouterGroupe() {
		return btnAjouterGroupe;
	}

	public Table getTvAccueil() {
		return getTvAccueil();
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
		
		Composite cpAccueil = new Composite(tabGestion, SWT.NONE);
		tbtmAccueil.setControl(cpAccueil);
		
		Group grpListeDesQuestionnaires = new Group(cpAccueil, SWT.NONE);
		grpListeDesQuestionnaires.setText("Liste des questionnaires");
		grpListeDesQuestionnaires.setBounds(10, 29, 440, 330);
		
		TableViewer tableViewer = new TableViewer(grpListeDesQuestionnaires, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setToolTipText("");
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		table.setBounds(10, 22, 420, 298);
		
		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnQuestionnaires = tableViewerColumn.getColumn();
		tblclmnQuestionnaires.setWidth(206);
		tblclmnQuestionnaires.setText("Questionnaires");
		
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnGroupe = tableViewerColumn_1.getColumn();
		tblclmnGroupe.setWidth(241);
		tblclmnGroupe.setText("Groupe");
		
		TabItem tbtmQcm = new TabItem(tabGestion, SWT.NONE);
		tbtmQcm.setText("QCM");
		
		Composite cpQcm = new Composite(tabGestion, SWT.NONE);
		tbtmQcm.setControl(cpQcm);
		
		Group grpAjouterQcm = new Group(cpQcm, SWT.NONE);
		grpAjouterQcm.setText("Ajouter QCM");
		grpAjouterQcm.setBounds(10, 28, 435, 319);
		
		Label lblNomQcm = new Label(grpAjouterQcm, SWT.NONE);
		lblNomQcm.setBounds(10, 42, 64, 15);
		lblNomQcm.setText("Nom QCM :");
		
		txtQcm = new Text(grpAjouterQcm, SWT.BORDER);
		txtQcm.setBounds(80, 36, 136, 21);
		
		Label lblQuestionQcm = new Label(grpAjouterQcm, SWT.NONE);
		lblQuestionQcm.setBounds(10, 84, 55, 15);
		lblQuestionQcm.setText("Question :");
		
		txtQuestionQcm = new Text(grpAjouterQcm, SWT.BORDER);
		txtQuestionQcm.setBounds(80, 78, 316, 21);
		
		Group grpReponse = new Group(grpAjouterQcm, SWT.NONE);
		grpReponse.setText("Reponse");
		grpReponse.setBounds(6, 137, 390, 168);
		
		txtQcm1 = new Text(grpReponse, SWT.BORDER);
		txtQcm1.setBounds(10, 43, 117, 21);
		
		txtQcm3 = new Text(grpReponse, SWT.BORDER);
		txtQcm3.setBounds(10, 110, 117, 21);
		
		txtQcm2 = new Text(grpReponse, SWT.BORDER);
		txtQcm2.setBounds(232, 43, 102, 21);
		
		txtQcm4 = new Text(grpReponse, SWT.BORDER);
		txtQcm4.setBounds(232, 110, 102, 21);
		
		Button btnCkGroupe1 = new Button(grpReponse, SWT.CHECK);
		btnCkGroupe1.setText("1");
		btnCkGroupe1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCkGroupe1.setBounds(133, 43, 35, 21);
		
		Button btnCkGroupe3 = new Button(grpReponse, SWT.CHECK);
		btnCkGroupe3.setText("3");
		btnCkGroupe3.setBounds(133, 112, 35, 16);
		
		Button btnCkGroupe2 = new Button(grpReponse, SWT.CHECK);
		btnCkGroupe2.setBounds(343, 48, 37, 16);
		btnCkGroupe2.setText("2");
		
		Button btnCkGroupe4 = new Button(grpReponse, SWT.CHECK);
		btnCkGroupe4.setLocation(344, 112);
		btnCkGroupe4.setSize(36, 16);
		btnCkGroupe4.setText("4");
		
		Label lblGroupeQcm = new Label(grpAjouterQcm, SWT.NONE);
		lblGroupeQcm.setBounds(244, 42, 55, 15);
		lblGroupeQcm.setText("Groupe :");
		
		ComboViewer comboViewer = new ComboViewer(grpAjouterQcm, SWT.NONE);
		Combo cbQcm = comboViewer.getCombo();
		cbQcm.setBounds(305, 36, 91, 23);
		
		btnAjouterQcm = new Button(cpQcm, SWT.NONE);
		btnAjouterQcm.setBounds(460, 124, 75, 25);
		btnAjouterQcm.setText("Ajouter");
		
		TabItem tbtmGroupe = new TabItem(tabGestion, SWT.NONE);
		tbtmGroupe.setText("Groupe");
		
		Composite cpGroupe = new Composite(tabGestion, SWT.NONE);
		tbtmGroupe.setControl(cpGroupe);
		
		Group grpAjouterUnGroupe = new Group(cpGroupe, SWT.NONE);
		grpAjouterUnGroupe.setText("Ajouter un groupe");
		grpAjouterUnGroupe.setBounds(10, 26, 398, 260);
		
		Label lblNewLabel = new Label(grpAjouterUnGroupe, SWT.NONE);
		lblNewLabel.setBounds(45, 68, 55, 15);
		lblNewLabel.setText("Libelle :");
		
		Label lblNewLabel_1 = new Label(grpAjouterUnGroupe, SWT.NONE);
		lblNewLabel_1.setBounds(45, 109, 80, 15);
		lblNewLabel_1.setText("Questionnaire :");
		
		Label lblNewLabel_2 = new Label(grpAjouterUnGroupe, SWT.NONE);
		lblNewLabel_2.setBounds(45, 143, 55, 15);
		lblNewLabel_2.setText("Code :");
		
		txtLibelle = new Text(grpAjouterUnGroupe, SWT.BORDER);
		txtLibelle.setBounds(131, 65, 138, 21);
		
		txtCode = new Text(grpAjouterUnGroupe, SWT.BORDER);
		txtCode.setBounds(131, 140, 138, 21);
		
		btnAjouterGroupe = new Button(grpAjouterUnGroupe, SWT.NONE);
		
		
		btnAjouterGroupe.setBounds(131, 192, 75, 25);
		btnAjouterGroupe.setText("Ajouter");
		
		cbvQuestionnaireGroupe = new ComboViewer(grpAjouterUnGroupe, SWT.NONE);
		cbvQuestionnaireGroupe.setContentProvider(ArrayContentProvider.getInstance());
		cbQuestionnaireGroupe = cbvQuestionnaireGroupe.getCombo();
		
		cbQuestionnaireGroupe.setBounds(131, 101, 138, 23);
		
		/*createColumnNom(tbAccueil, "Libelle", 1);
		createColumnDate(tbAccueil, "Date", 1);
		createColumnGroupe(tbAccueil, "Groupe", 1);*/
	}

	public TableViewerColumn getTableViewerColumn() {
		return tableViewerColumn;
	}

	public Button getBtnAjouterQcm() {
		return btnAjouterQcm;
	}
}
