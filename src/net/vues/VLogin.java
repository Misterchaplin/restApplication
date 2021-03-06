package net.vues;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class VLogin {

	protected Shell shlLogin;
	private Text txtPassword;
	private Text txtLogin;
	private Group grpConnexion;
	private Button btnConnexion;
	private Group grpInformation;
	private Label lblInformation;
	private Display display;
	
	
	public Shell getShell() {
		return shlLogin;
	}

	public Text getTxtPassword() {
		return txtPassword;
	}

	public Text getTxtLogin() {
		return txtLogin;
	}

	public Group getGrpConnexion() {
		return grpConnexion;
	}

	public Button getBtnConnexion() {
		return btnConnexion;
	}

	public Group getGrpInformation() {
		return grpInformation;
	}

	public Label getLblInformation() {
		return lblInformation;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VLogin window = new VLogin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		createContents();
	}


	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		shlLogin.open();
		shlLogin.layout();
		while (!shlLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlLogin = new Shell( SWT.SHELL_TRIM & (~SWT.RESIZE));
		shlLogin.setSize(454, 316);
		shlLogin.setText("Login");
		
		grpConnexion = new Group(shlLogin, SWT.NONE);
		grpConnexion.setVisible(true);
		grpConnexion.setText("Connexion");
		grpConnexion.setBounds(10, 71, 414, 180);
		
		Label label = new Label(grpConnexion, SWT.NONE);
		label.setText("Login :");
		label.setBounds(53, 56, 55, 15);
		
		Label label_1 = new Label(grpConnexion, SWT.NONE);
		label_1.setText("Mot de passe :");
		label_1.setBounds(53, 88, 87, 15);
		
		txtLogin = new Text(grpConnexion, SWT.BORDER);
		txtLogin.setText("test");
		txtLogin.setBounds(146, 53, 130, 21);
		
		txtPassword = new Text(grpConnexion, SWT.BORDER);
		txtPassword.setText("test");
		txtPassword.setBounds(146, 85, 130, 21);
		
		btnConnexion = new Button(grpConnexion, SWT.NONE);

		btnConnexion.setText("Se connecter");
		btnConnexion.setBounds(167, 112, 109, 25);
		
		grpInformation = new Group(shlLogin, SWT.NONE);
		grpInformation.setLocation(10, 10);
		grpInformation.setSize(414, 55);
		grpInformation.setText("Information :");
		
		lblInformation = new Label(grpInformation, SWT.NONE);
		lblInformation.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblInformation.setBounds(10, 21, 394, 24);

	}




}
