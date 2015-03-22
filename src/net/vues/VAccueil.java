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

public class VAccueil {

	protected Shell accueil;
	private ToolItem itemConnexion;
	private ToolItem itemMonProfil;
	private ToolItem itemLogin;
	private Text txtMdp;
	private Text txtLogin;
	private Group grpConnexion;
	private Button btnConnect;

	public Button getBtnConnect() {
		return btnConnect;
	}

	public Group getGrpConnexion() {
		return grpConnexion;
	}

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

		itemConnexion = new ToolItem(menuConnexion, SWT.NONE);
		itemConnexion.setText("Connexion");
		
		grpConnexion = new Group(accueil, SWT.NONE);
		grpConnexion.setText("Connexion");
		grpConnexion.setVisible(false);
		FormData fd_grpConnexion = new FormData();
		fd_grpConnexion.bottom = new FormAttachment(menuConnexion, 257, SWT.BOTTOM);
		fd_grpConnexion.top = new FormAttachment(menuConnexion, 77);
		fd_grpConnexion.left = new FormAttachment(0, 88);
		fd_grpConnexion.right = new FormAttachment(0, 424);
		grpConnexion.setLayoutData(fd_grpConnexion);
		
		Label lblLogin = new Label(grpConnexion, SWT.NONE);
		lblLogin.setBounds(53, 56, 55, 15);
		lblLogin.setText("Login :");
		
		Label lblMdp = new Label(grpConnexion, SWT.NONE);
		lblMdp.setBounds(53, 88, 76, 15);
		lblMdp.setText("Mot de passe :");
		
		txtMdp = new Text(grpConnexion, SWT.BORDER);
		txtMdp.setBounds(135, 82, 130, 21);
		
		txtLogin = new Text(grpConnexion, SWT.BORDER);
		txtLogin.setBounds(135, 50, 130, 21);
		
		btnConnect = new Button(grpConnexion, SWT.NONE);
		btnConnect.setBounds(178, 145, 87, 25);
		btnConnect.setText("Se connecter");

	}

	public Text getTxtMdp() {
		return txtMdp;
	}

	public Text getTxtLogin() {
		return txtLogin;
	}
}
