package net.vues;

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
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.internal.forms.widgets.SWTUtil;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class VAccueil {

	protected Shell accueil;
	private ToolItem itemConnexion;
	private ToolItem itemMonProfil;
	private ToolItem itemLogin;
	private Group grpInformation;
	
	public Group getGrpInformation() {
		return grpInformation;
	}

	public Label getLblInformation() {
		return lblInformation;
	}

	private Label lblInformation;
	

	public Shell getAccueil() {
		return accueil;
	}

	public ToolItem getItemConnexion() {
		return itemConnexion;
	}

	public ToolItem getItemMonProfil() {
		return itemMonProfil;
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
		accueil.setSize(546, 487);
		accueil.setText("QCM");
		accueil.setLayout(new FormLayout());

		ToolBar menuConnexion = new ToolBar(accueil, SWT.FLAT | SWT.RIGHT);
		FormData fd_menuConnexion = new FormData();
		fd_menuConnexion.top = new FormAttachment(0, 10);
		fd_menuConnexion.right = new FormAttachment(100, -10);
		fd_menuConnexion.bottom = new FormAttachment(100, -415);
		menuConnexion.setLayoutData(fd_menuConnexion);

		itemLogin = new ToolItem(menuConnexion, SWT.NONE);
		itemLogin.setEnabled(false);

		itemMonProfil = new ToolItem(menuConnexion, SWT.NONE);
		itemMonProfil.setEnabled(false);
		itemMonProfil.setText("Profil");

		itemConnexion = new ToolItem(menuConnexion, SWT.NONE);
		itemConnexion.setText("Connexion");
		
		grpInformation = new Group(accueil, SWT.NONE);
		grpInformation.setText("Information :");
		FormData fd_grpInformation = new FormData();
		fd_grpInformation.bottom = new FormAttachment(100, -355);
		fd_grpInformation.top = new FormAttachment(menuConnexion, 14);
		fd_grpInformation.right = new FormAttachment(menuConnexion, 0, SWT.RIGHT);
		fd_grpInformation.left = new FormAttachment(0, 10);
		grpInformation.setLayoutData(fd_grpInformation);
		
		lblInformation = new Label(grpInformation, SWT.NONE);
		lblInformation.setBounds(10, 21, 490, 15);
		
		TabFolder tabGestion = new TabFolder(accueil, SWT.NONE);
		FormData fd_tabGestion = new FormData();
		fd_tabGestion.top = new FormAttachment(grpInformation, 6);
		fd_tabGestion.left = new FormAttachment(0, 10);
		fd_tabGestion.bottom = new FormAttachment(100, -10);
		fd_tabGestion.right = new FormAttachment(100, -26);
		tabGestion.setLayoutData(fd_tabGestion);
		
		TabItem tbtmNewItem = new TabItem(tabGestion, SWT.NONE);
		tbtmNewItem.setText("New Item");

	}
}
