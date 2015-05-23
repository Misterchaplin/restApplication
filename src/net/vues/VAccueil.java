package net.vues;

import net.controllers.AccueilController;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

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
	private Text txtLibelle;
	private Text txtCode;
	private Button btnAjouterGroupe;
	private Button btnAjouterQcm;
	private TableViewerColumn tableViewerColumnQuestionnaire;
	private ComboViewer cbvQuestionnaireGroupe;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Combo cbQuestionnaireGroupe;
	private TabFolder tabGestion;
	private TableViewer tableViewer;
	private TableViewerColumn firstNameCol;
	private TableViewerColumn lastNameCol;
	private TabItem tbtmQcm;
	private ComboViewer cbvQcm;
	private ComboViewer cbvStatistiquesGroupe;
	private ComboViewer cbvStatistiquesQuestionnaire;
	private Combo cbQcm;
	private Button btnCkGroupe3;
	private Button btnCkGroupe2;
	private Button btnCkGroupe4;
	private Button btnCkGroupe1;
	private Button btnNouveauQuestionnaire;
	private Button btnSupprimerAccueil;
	private Button btnModifierAccueil;
	private Label lblCurrentQuestion;
	private Label lblLastQuestion;
	private Label lblMerciDe;
	private Button btnSuivant;
	private Button btnPrecedent;
	private Button btnAjouterQuestion;
	private Button btnModifierGroupe;
	private Button btnModifierGroupeAccueil;
	private TabItem tbtmStatistiques;
	private Composite cpStatistiques;
	private Label lblStatQuestionnaire;
	private Combo cbStatistiquesQuestionnaire;
	private Table tableStat;
	private Combo cbStatistiquesGroupe;
	private Button btnStatValider;
	private TableViewer tableViewerStat;
	private TableColumn columnStat;
	private TableViewerColumn userStat;
	public TableViewerColumn getUserStat() {
		return userStat;
	}
	
	public Button getBtnModifierGroupeAccueil() {
		return btnModifierGroupeAccueil;
	}


	public TableViewerColumn getQuestStat() {
		return questStat;
	}

	private TableViewerColumn questStat;
	
	
	public TableColumn getColumnStat() {
		return columnStat;
	}


	public TableViewer getTableViewer_1() {
		return tableViewerStat;
	}


	public Button getBtnStatValider() {
		return btnStatValider;
	}


	public ComboViewer getCbvStatistiquesQuestionnaire() {
		return cbvStatistiquesQuestionnaire;
	}

	
	public ComboViewer getCbvStatistiquesGroupe() {
		return cbvStatistiquesGroupe;
	}
	
	public Combo getCbStatistiquesQuestionnaire() {
		return cbStatistiquesQuestionnaire;
	}

	public Combo getCbStatistiquesGroupe() {
		return cbStatistiquesGroupe;
	}

	public Button getBtnModifierGroupe() {
		return btnModifierGroupe;
	}

	public Label getLblMerciDe() {
		return lblMerciDe;
	}

	public Button getBtnModifierAccueil() {
		return btnModifierAccueil;
	}

	public Button getBtnSupprimerAccueil() {
		return btnSupprimerAccueil;
	}

	public Table getTable() {
		return table;
	}
	
	public Text getTxtQcm() {
		return txtQcm;
	}

	public Text getTxtLibelle() {
		return txtLibelle;
	}
	
	public Text getTxtCode() {
		return txtCode;
	}
	
	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public TabFolder getTabGestion() {
		return tabGestion;
	}

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
	/**
	 * 
	 */
	protected void createContents() {
		accueil = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		accueil.setSize(626, 517);
		accueil.setText("QCM");
		accueil.setLayout(new FormLayout());

		ToolBar menuConnexion = new ToolBar(accueil, SWT.FLAT | SWT.RIGHT);
		FormData fd_menuConnexion = new FormData();
		fd_menuConnexion.left = new FormAttachment(100, -203);
		fd_menuConnexion.right = new FormAttachment(100, -10);
		fd_menuConnexion.top = new FormAttachment(0);
		menuConnexion.setLayoutData(fd_menuConnexion);

		itemConnexion = new ToolItem(menuConnexion, SWT.NONE);
		itemConnexion.setText("Connexion");
		
		grpInformation = new Group(accueil, SWT.NONE);
		fd_menuConnexion.bottom = new FormAttachment(grpInformation, -1);
		
		itemLogin = new ToolItem(menuConnexion, SWT.NONE);
		

		grpInformation.setText("Information :");
		FormData fd_grpInformation = new FormData();
		fd_grpInformation.top = new FormAttachment(0, 34);
		fd_grpInformation.left = new FormAttachment(0, 10);
		fd_grpInformation.right = new FormAttachment(100, -10);
		grpInformation.setLayoutData(fd_grpInformation);
		
		lblInformation = new Label(grpInformation, SWT.NONE);
		lblInformation.setBounds(10, 10, 490, 15);
		
		tabGestion = new TabFolder(accueil, SWT.NONE);
		fd_grpInformation.bottom = new FormAttachment(tabGestion, -1);
		FormData fd_tabGestion = new FormData();
		fd_tabGestion.left = new FormAttachment(0, 10);
		fd_tabGestion.right = new FormAttachment(100, -10);
		fd_tabGestion.top = new FormAttachment(0, 81);
		fd_tabGestion.bottom = new FormAttachment(100);
		tabGestion.setLayoutData(fd_tabGestion);
		
		TabItem tbtmAccueil = new TabItem(tabGestion, SWT.NONE);
		tbtmAccueil.setImage(SWTResourceManager.getImage("./images/home.png"));
		tbtmAccueil.setText("Accueil");
		
		Composite cpAccueil = new Composite(tabGestion, SWT.NONE);
		tbtmAccueil.setControl(cpAccueil);
		
		Group grpListeDesQuestionnaires = new Group(cpAccueil, SWT.NONE);
		grpListeDesQuestionnaires.setText("Liste des questionnaires");
		grpListeDesQuestionnaires.setBounds(10, 29, 410, 316);
		
		
		tableViewer = new TableViewer(grpListeDesQuestionnaires, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(10, 22, 391, 267);
		
		TableLayout tlayout = new TableLayout();
	    tlayout.addColumnData( new ColumnWeightData( 50, 250, true ));
	    tlayout.addColumnData( new ColumnWeightData( 50, 250, true ));
	    tableViewer.getTable().setLayout( tlayout );
	    tableViewer.setContentProvider( new ArrayContentProvider());
	    
	    TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Questionnaires");
        column.setWidth(223);
        firstNameCol = new TableViewerColumn(tableViewer, column);
   
        column = new TableColumn(tableViewer.getTable(), SWT.NONE);
        column.setText("Groupes");
        column.setWidth(223);
        lastNameCol = new TableViewerColumn(tableViewer, column);
		btnModifierAccueil = new Button(cpAccueil, SWT.NONE);
        btnModifierAccueil.setBounds(444, 154, 130, 28);
        formToolkit.adapt(btnModifierAccueil, true, true);
        btnModifierAccueil.setText("Modifier QCM");
        
        btnSupprimerAccueil = new Button(cpAccueil, SWT.NONE);
     
        btnSupprimerAccueil.setBounds(464, 207, 95, 28);
        formToolkit.adapt(btnSupprimerAccueil, true, true);
        btnSupprimerAccueil.setText("Supprimer");
        
        btnModifierGroupeAccueil = new Button(cpAccueil, SWT.NONE);
        btnModifierGroupeAccueil.setBounds(444, 180, 126, 28);
        btnModifierGroupeAccueil.setText("Modifier Groupe");
     
               
				
		tbtmQcm = new TabItem(tabGestion, SWT.NONE);
		tbtmQcm.setImage(SWTResourceManager.getImage("./images/qcm.png"));
		tbtmQcm.setText("QCM");
		
		Composite cpQcm = new Composite(tabGestion, SWT.NONE);
		tbtmQcm.setControl(cpQcm);
		
		Group grpAjouterQcm = new Group(cpQcm, SWT.NONE);
		grpAjouterQcm.setText("Ajouter QCM");
		grpAjouterQcm.setBounds(10, 10, 435, 337);
		
		Label lblNomQcm = new Label(grpAjouterQcm, SWT.NONE);
		lblNomQcm.setBounds(10, 51, 64, 15);
		lblNomQcm.setText("Nom QCM :");
		
		txtQcm = new Text(grpAjouterQcm, SWT.BORDER);
		txtQcm.setBounds(80, 45, 136, 21);
		
		Label lblQuestionQcm = new Label(grpAjouterQcm, SWT.NONE);
		lblQuestionQcm.setBounds(10, 92, 64, 15);
		lblQuestionQcm.setText("Question :");
		
		txtQuestionQcm = new Text(grpAjouterQcm, SWT.BORDER);
		txtQuestionQcm.setBounds(80, 89, 316, 21);
		
		Group grpReponse = new Group(grpAjouterQcm, SWT.NONE);
		grpReponse.setText("Reponse");
		grpReponse.setBounds(10, 124, 390, 168);
		
		txtQcm1 = new Text(grpReponse, SWT.BORDER);
		txtQcm1.setBounds(10, 43, 117, 21);
		
		txtQcm3 = new Text(grpReponse, SWT.BORDER);
		txtQcm3.setBounds(10, 110, 117, 21);
		
		txtQcm2 = new Text(grpReponse, SWT.BORDER);
		txtQcm2.setBounds(232, 43, 102, 21);
		
		txtQcm4 = new Text(grpReponse, SWT.BORDER);
		txtQcm4.setBounds(232, 110, 102, 21);
		
		btnCkGroupe1 = new Button(grpReponse, SWT.CHECK);
		btnCkGroupe1.setText("1");
		btnCkGroupe1.setBounds(133, 43, 35, 21);
		
		btnCkGroupe3 = new Button(grpReponse, SWT.CHECK);
		btnCkGroupe3.setText("3");
		btnCkGroupe3.setBounds(133, 112, 35, 16);
		
		btnCkGroupe2 = new Button(grpReponse, SWT.CHECK);
		btnCkGroupe2.setBounds(343, 48, 37, 16);
		btnCkGroupe2.setText("2");
		
		btnCkGroupe4 = new Button(grpReponse, SWT.CHECK);
		btnCkGroupe4.setLocation(344, 112);
		btnCkGroupe4.setSize(36, 16);
		btnCkGroupe4.setText("4");
		
		Label lblGroupeQcm = new Label(grpAjouterQcm, SWT.NONE);
		lblGroupeQcm.setBounds(244, 51, 55, 15);
		lblGroupeQcm.setText("Groupe :");
		
		cbvQcm = new ComboViewer(grpAjouterQcm, SWT.NONE | SWT.READ_ONLY);
		cbvQcm.setContentProvider(ArrayContentProvider.getInstance());
		cbQcm = cbvQcm.getCombo();
		cbQcm.setBounds(305, 45, 91, 23);
		
		Label lblQuestion = new Label(grpAjouterQcm, SWT.NONE);
		lblQuestion.setBounds(10, 24, 75, 15);
		
		lblQuestion.setText("Question n\u00B0 :");
		
		lblCurrentQuestion = new Label(grpAjouterQcm, SWT.NONE);
		lblCurrentQuestion.setBounds(91, 24, 10, 15);	
		lblCurrentQuestion.setText("0");
		
		Label lblSlashQuestion = new Label(grpAjouterQcm, SWT.NONE);
		lblSlashQuestion.setBounds(106, 24, 10, 15);
		lblSlashQuestion.setText("/");
		
		lblLastQuestion = new Label(grpAjouterQcm, SWT.NONE);
		lblLastQuestion.setBounds(116, 24, 21, 15);
		lblLastQuestion.setText("0");
		
		lblMerciDe = new Label(grpAjouterQcm, SWT.NONE);
		lblMerciDe.setBounds(10, 290, 448, 14);
		lblMerciDe.setFont(SWTResourceManager.getFont(".Helvetica Neue DeskInterface", 8, SWT.NORMAL));
		lblMerciDe.setText("* Merci de cliquer sur le bouton 'terminer' lorsque la création du QCM est achevé.");
		lblMerciDe.setVisible(false);
		
		btnSuivant = new Button(grpAjouterQcm, SWT.NONE);
		btnSuivant.setBounds(320, 24, 75, 15);
		formToolkit.adapt(btnSuivant, true, true);
		btnSuivant.setText("Suivant");
		btnSuivant.setVisible(false);
		
		btnPrecedent = new Button(grpAjouterQcm, SWT.NONE);
		btnPrecedent.setBounds(224, 24, 75, 15);
		formToolkit.adapt(btnPrecedent, true, true);
		btnPrecedent.setText("Pr\u00E9c\u00E9dent");
		btnPrecedent.setVisible(false);
		
		btnAjouterQcm = new Button(cpQcm, SWT.NONE);
		btnAjouterQcm.setBounds(451, 124, 94, 25);
		btnAjouterQcm.setText("Ajouter");
		
		btnNouveauQuestionnaire = new Button(cpQcm, SWT.NONE);
		btnNouveauQuestionnaire.setText("Terminer");
		btnNouveauQuestionnaire.setBounds(451, 185, 94, 25);
		formToolkit.adapt(btnNouveauQuestionnaire, true, true);
		
		btnAjouterQuestion = new Button(cpQcm, SWT.NONE);
		btnAjouterQuestion.setBounds(451, 155, 94, 25);
		formToolkit.adapt(btnAjouterQuestion, true, true);
		btnAjouterQuestion.setText("Ajouter Question");
		btnAjouterQuestion.setVisible(false);
		
		TabItem tbtmGroupe = new TabItem(tabGestion, SWT.NONE);
		tbtmGroupe.setImage(SWTResourceManager.getImage("./images/group.png"));
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
		
		cbvQuestionnaireGroupe = new ComboViewer(grpAjouterUnGroupe, SWT.NONE | SWT.READ_ONLY);
		cbvQuestionnaireGroupe.setContentProvider(ArrayContentProvider.getInstance());
		cbQuestionnaireGroupe = cbvQuestionnaireGroupe.getCombo();
		cbQuestionnaireGroupe.setBounds(131, 101, 138, 23);
		
		btnModifierGroupe = new Button(grpAjouterUnGroupe, SWT.NONE);
		btnModifierGroupe.setVisible(false);
		
		btnModifierGroupe.setBounds(200, 194, 80, 21);
		formToolkit.adapt(btnModifierGroupe, true, true);
		btnModifierGroupe.setText("Modifier");
		
		tbtmStatistiques = new TabItem(tabGestion, SWT.NONE);
		tbtmStatistiques.setImage(SWTResourceManager.getImage("./images/stats.png"));
		tbtmStatistiques.setText("Statistiques");
		
		cpStatistiques = new Composite(tabGestion, SWT.NONE);
		tbtmStatistiques.setControl(cpStatistiques);

		
		Group grpStatistiques = new Group(cpStatistiques, SWT.NONE);
		grpStatistiques.setText("Affichier les statisiques");
		grpStatistiques.setBounds(10, 33, 471, 307);
		
		cbvStatistiquesGroupe = new ComboViewer(grpStatistiques, SWT.NONE | SWT.READ_ONLY);
		cbvStatistiquesGroupe.setContentProvider(ArrayContentProvider.getInstance());
		cbStatistiquesGroupe = cbvStatistiquesGroupe.getCombo();
		
		cbStatistiquesGroupe.setBounds(10, 38, 91, 22);

		
		Label lblStatGroup = new Label(grpStatistiques, SWT.NONE);
		lblStatGroup.setBounds(10, 23, 91, 14);

		lblStatGroup.setText("Choisir Groupe:");
		
		lblStatQuestionnaire = new Label(grpStatistiques, SWT.NONE);
		lblStatQuestionnaire.setBounds(117, 23, 101, 14);
		lblStatQuestionnaire.setText("Questionnaire :");
		
		cbvStatistiquesQuestionnaire = new ComboViewer(grpStatistiques, SWT.NONE | SWT.READ_ONLY);
		cbvStatistiquesQuestionnaire.setContentProvider(ArrayContentProvider.getInstance());
		cbStatistiquesQuestionnaire = cbvStatistiquesQuestionnaire.getCombo();
		cbStatistiquesQuestionnaire.setBounds(115, 38, 91, 22);
		
		
		btnStatValider = new Button(grpStatistiques, SWT.NONE);
		btnStatValider.setBounds(212, 38, 75, 22);
		btnStatValider.setText("Valider");
		
		tableViewerStat = new TableViewer(grpStatistiques, SWT.BORDER | SWT.FULL_SELECTION);
		tableStat = tableViewerStat.getTable();
		tableStat.setHeaderVisible(true);
		tableStat.setLinesVisible(true);
		tableStat.setBounds(10, 76, 437, 191);
		
		TableLayout tlayout2 = new TableLayout();
	    tlayout2.addColumnData( new ColumnWeightData( 50, 250, true ));
	    tlayout2.addColumnData( new ColumnWeightData( 50, 250, true ));
	    tableViewerStat.getTable().setLayout( tlayout2 );
	    tableViewerStat.setContentProvider( new ArrayContentProvider());
	 
	    columnStat = new TableColumn(tableViewerStat.getTable(), SWT.NONE);
        columnStat.setText("Utilisateurs");
        columnStat.setWidth(217);
        userStat = new TableViewerColumn(tableViewerStat, columnStat);
        
        columnStat = new TableColumn(tableViewerStat.getTable(), SWT.NONE);
        columnStat.setText("Pourcentage de bonnes r�ponses");
        columnStat.setWidth(216);
        questStat = new TableViewerColumn(tableViewerStat, columnStat);
		

	}

	public TableViewer getTableViewerStat() {
		return tableViewerStat;
	}


	public Button getBtnAjouterQuestion() {
		return btnAjouterQuestion;
	}

	public Button getBtnSuivant() {
		return btnSuivant;
	}

	public Button getBtnPrecedent() {
		return btnPrecedent;
	}

	public Label getLblCurrentQuestion() {
		return lblCurrentQuestion;
	}

	public Label getLblLastQuestion() {
		return lblLastQuestion;
	}

	public Button getBtnNouveauQuestionnaire() {
		return btnNouveauQuestionnaire;
	}

	public Button getBtnCkGroupe3() {
		return btnCkGroupe3;
	}

	public Button getBtnCkGroupe2() {
		return btnCkGroupe2;
	}

	public Button getBtnCkGroupe4() {
		return btnCkGroupe4;
	}

	public Button getBtnCkGroupe1() {
		return btnCkGroupe1;
	}

	public Text getTxtQcm1() {
		return txtQcm1;
	}

	public Text getTxtQcm3() {
		return txtQcm3;
	}

	public Text getTxtQcm2() {
		return txtQcm2;
	}

	public Text getTxtQcm4() {
		return txtQcm4;
	}

	public Text getTxtQuestionQcm() {
		return txtQuestionQcm;
	}

	public Combo getCbQcm() {
		return cbQcm;
	}

	public ComboViewer getCbvQcm() {
		return cbvQcm;
	}

	public TabItem getTbtmQcm() {
		return tbtmQcm;
	}

	public TableViewerColumn getFirstNameCol() {
		return firstNameCol;
	}

	public TableViewerColumn getLastNameCol() {
		return lastNameCol;
	}

	public TableViewerColumn getTableViewerColumnQuestionnaire() {
		return tableViewerColumnQuestionnaire;
	}

	public Button getBtnAjouterQcm() {
		return btnAjouterQcm;
	}
	

}
