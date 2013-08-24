package developen.client.application.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;

import developen.client.application.action.EntryAction;
import developen.client.application.i18n.AdministratorTag;
import developen.client.application.i18n.DevelOpenCloudTag;
import developen.client.application.i18n.HelpTag;
import developen.client.application.i18n.IdiomTag;
import developen.client.application.i18n.ModulesTag;
import developen.client.application.i18n.ParameterizationTag;
import developen.client.application.i18n.PreferencesTag;
import developen.client.application.i18n.SecurityTag;
import developen.client.application.widget.LoggedInCondition;
import developen.client.framework.widget.DBComboBox;
import developen.client.framework.widget.DesktopPane;
import developen.client.framework.widget.MimeTypeProvider;
import developen.common.commercial.i18n.SystemCompanyTag;
import developen.common.commercial.mvc.Idiom;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.commercial.mvc.SystemPersonSystemCompany;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;
import developen.common.framework.utils.I18N;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Action;
import developen.common.framework.widget.EnglishUSARadioButtonMenuItem;
import developen.common.framework.widget.Frame;
import developen.common.framework.widget.InternalFramePosition;
import developen.common.framework.widget.Menu;
import developen.common.framework.widget.MenuBar;
import developen.common.framework.widget.PortugueseBrazilRadioButtonMenuItem;
import developen.common.framework.widget.ToolBar;


public abstract class ClientView extends Frame implements MimeTypeProvider {


	private static final long serialVersionUID = 4721868847647105877L;

	protected Object[] menuHierarchy;
	
	private HashMap<Class<? extends Model>, Action> mimeType;

	protected DesktopPane desktop;
	
	protected MenuBar menu;
	
	protected ToolBar toolBar;

	protected Menu modulesMenu;
	
	protected Menu administratorMenu;
	
	protected Menu administratorSecurityMenu;
	
	protected Menu administratorParameterizationMenu;
	
	protected Menu preferencesMenu;
	
	protected Menu preferencesSecurityMenu;
	
	protected Menu preferencesIdiomMenu;
	
	protected Menu helpMenu;
	
	protected DBComboBox companyComboBox;

	protected LoginModel loginModel;
	
	protected LoginController loginController;
	
	protected LoginView loginView;
	

	public ClientView(ClientController controller){

		super(controller);

	}

	
	protected HashMap<Class<? extends Model>, Action> getMimeTypes() {

		
		if (mimeType==null)
			
			mimeType = new HashMap<Class<? extends Model>, Action>();
			
		return mimeType;
		
		
	}

	
	public Action getEntryActionOfMimeType(Class<? extends Model> model){
		
		
		if (model==null)
			
			return null;
		
		return getMimeTypes().get(model);
		
		
	}
	

	public void setEntryActionOfMimeType(Class<? extends Model> model, Action action){
		
		getMimeTypes().put(model, action);
		
	}

	
	public Object[] getMenuHierarchy() {

		
		if (menuHierarchy == null){

			menuHierarchy = new Object[]{
					new DevelOpenCloudTag(),

					new Object[]{
						new ModulesTag(),


					},

					new Object[]{
						new AdministratorTag(),

						new Object[]{
							new SecurityTag(),

							getSystemPersonEntryAction()

						},

						new Object[]{
							new ParameterizationTag(),

							getSystemCompanyEntryAction()

						}

					}

			};

		}
		
		return menuHierarchy;

		
	}
	

	public void init() {

		
		setTitle(getCaption().toString());
		
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				
				try {
					
					getController().exit();
					
				} catch (Exception exception) {
					
					Messenger.show(exception);
					
				}
				
			}
			
		});
		
		setIconImage(new DevelOpenCloudTag().getLargeIcon().getImage());
		
		setLayout(new BorderLayout());
		
		setJMenuBar(getMenu());
		
		add(getToolBar(), BorderLayout.PAGE_START);
		
		add(getDesktop(), BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(1024, 600));
		

	}

	
	public abstract Tag getCaption();

	
	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		if (evt.getPropertyName().equals("ModelState")){

			ClientState newValue = (ClientState) evt.getNewValue();

			if (newValue.equals(ClientState.EXITED))

				System.exit(0);

			else

				if (newValue.equals(ClientState.LOGGED_OUT))

					executeLoginDialog();

		} else 

			if (evt.getPropertyName().equals(ClientController.SYSTEM_PERSON_PROPERTY)){

				if (evt.getNewValue() != null){

					SystemPerson systemPerson = (SystemPerson) evt.getNewValue();

					Idiom idiom = systemPerson.getIdiom();
					
					I18N.setLanguage(new Locale(idiom.getIdentifier().substring(0, 2), idiom.getIdentifier().substring(3, 5).toUpperCase()));

					DefaultComboBoxModel<SystemCompany> model = (DefaultComboBoxModel<SystemCompany>) getCompanyComboBox().getModel();
					
					model.removeAllElements();

					for (SystemPersonSystemCompany company : systemPerson.getSystemCompanies())
						
						model.addElement(company.getIdentifier().getSystemCompany());

				} else {

					DefaultComboBoxModel<SystemCompany> model = (DefaultComboBoxModel<SystemCompany>) getCompanyComboBox().getModel();
					
					model.removeAllElements();

				}

			}

		
	}

	
	public void executeLoginDialog(){

		
		loginModel = new LoginModel();
		
		loginController = new LoginController();
		
		loginView = new LoginView(loginController);
		
		loginController.setModel(loginModel);
		
		loginController.addView(loginView);
		
		loginController.addLoginListener(new LoginListener() {

			public void onSuccess(LoginEvent event) throws Exception {

				getController().login(event.getSystemPerson());

			}

			public void onFailure(LoginEvent event) throws Exception {

				getController().exit();

			}

		});

		getDesktop().add(loginView);

		loginView.setLocation(InternalFramePosition.CENTER);
		
		loginView.setVisible(true);

		try {

			loginController.clear();

		} catch (Exception e) {

			Messenger.show(e);

		}

		
	}

	
	public ClientController getController(){

		return (ClientController) super.getController();

	}

	
	protected DBComboBox getCompanyComboBox(){

		
		if (companyComboBox == null){

			try {

				companyComboBox = new DBComboBox(new SystemCompanyTag(), new SystemCompany[]{}, ClientController.SYSTEM_COMPANY_PROPERTY);
				
				companyComboBox.setPreferredSize(new Dimension(300,24));
				
				companyComboBox.setCondition(new LoggedInCondition());
				
				companyComboBox.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {

						getController().changeSystemCompanyProperty((SystemCompany)((DBComboBox)e.getSource()).getSelectedItem());

					}
					
				});
				
				getController().addView(companyComboBox);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}

		}
		
		return companyComboBox;
		

	}

	
	protected DesktopPane getDesktop() {

		
		if (desktop == null)
			
			desktop = new DesktopPane();
		
		return desktop;
		

	}

	
	protected ToolBar getToolBar(){

	
		if (toolBar == null) {

			toolBar = new ToolBar();
			
			toolBar.add(getCompanyComboBox());
			
			toolBar.addSeparator();
			
			toolBar.add(getHelpAction());
			
			toolBar.addSeparator();
			
			toolBar.add(getLogoutAction());
			
			toolBar.add(getExitAction());

		}
		
		return toolBar;
		

	}

	
	protected MenuBar getMenu(){

	
		if (menu == null){

			menu = new MenuBar();
			
			menu.add(getModulesMenu());
			
			menu.add(getAdministratorMenu());
			
			menu.add(getPreferencesMenu());
			
			menu.add(getHelpMenu());

		}
		
		return menu;
		

	}

	
	protected Menu getModulesMenu(){

		
		if (modulesMenu==null){

			modulesMenu = new Menu(new ModulesTag());
			
			modulesMenu.add(getLogoutAction());
			
			modulesMenu.add(getExitAction());

		}
		
		return modulesMenu;
		

	}

	
	protected Menu getAdministratorMenu(){

	
		if (administratorMenu==null){

			administratorMenu = new Menu(new AdministratorTag());
			
			administratorMenu.add(getAdministratorSecurityMenu());
			
			administratorMenu.add(getAdministratorParameterizationMenu());

		}
		
		return administratorMenu;
		

	};


	protected Menu getAdministratorParameterizationMenu() {

	
		if (administratorParameterizationMenu==null){

			administratorParameterizationMenu = new Menu(new ParameterizationTag());
			
			administratorParameterizationMenu.add(getSystemCompanyEntryAction());

		}
		
		return administratorParameterizationMenu;
		

	}

	
	protected Menu getAdministratorSecurityMenu() {

		
		if (administratorSecurityMenu==null){

			administratorSecurityMenu = new Menu(new SecurityTag());
			
			administratorSecurityMenu.add(getSystemPersonEntryAction());

		}
		
		return administratorSecurityMenu;
		

	}

	
	protected Menu getPreferencesMenu(){

		
		if (preferencesMenu==null){

			preferencesMenu = new Menu(new PreferencesTag());
			
			preferencesMenu.add(getPreferencesSecurityMenu());
			
			preferencesMenu.add(getPreferencesIdiomMenu());

		}
		
		return preferencesMenu;
		

	}

	
	protected Menu getPreferencesSecurityMenu() {

	
		if (preferencesSecurityMenu==null){

			preferencesSecurityMenu = new Menu(new SecurityTag());
			
			preferencesSecurityMenu.add(getChangePasswordAction());

		}
		
		return preferencesSecurityMenu;
		

	}
	

	protected Menu getPreferencesIdiomMenu() {

		
		if (preferencesIdiomMenu==null){

			preferencesIdiomMenu = new Menu(new IdiomTag());
			
			preferencesIdiomMenu.add(getPortugueseBrazilRadioButtonMenuItem());
			
			preferencesIdiomMenu.add(getEnglishUSARadioButtonMenuItem());

			ButtonGroup buttonGroup = new ButtonGroup();
			
			buttonGroup.add(getPortugueseBrazilRadioButtonMenuItem());
			
			buttonGroup.add(getEnglishUSARadioButtonMenuItem());

		}
		
		return preferencesIdiomMenu;
		

	}

	
	protected Menu getHelpMenu(){

	
		if (helpMenu==null){

			helpMenu = new Menu(new HelpTag());
			
			helpMenu.add(getHelpAboutAction());
			
			helpMenu.addSeparator();
			
			helpMenu.add(getHelpAction());

		}
		
		return helpMenu;
		

	}

	
	protected abstract Action getExitAction();
	

	protected abstract Action getLogoutAction();
	
	
	protected abstract PortugueseBrazilRadioButtonMenuItem getPortugueseBrazilRadioButtonMenuItem();
	
	
	protected abstract EnglishUSARadioButtonMenuItem getEnglishUSARadioButtonMenuItem();
	
	
	protected abstract Action getHelpAboutAction();
	
	
	protected abstract Action getHelpAction();

	
	protected abstract EntryAction getChangePasswordAction();
	
	
	protected abstract EntryAction getSystemCompanyEntryAction();

	
	protected abstract EntryAction getSystemPersonEntryAction();


}