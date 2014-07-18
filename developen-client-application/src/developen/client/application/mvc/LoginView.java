package developen.client.application.mvc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import developen.client.application.action.LoginAction;
import developen.client.application.i18n.AccessControlTag;
import developen.client.application.i18n.LoginTag;
import developen.client.application.i18n.PasswordTag;
import developen.client.application.i18n.PreferencesTag;
import developen.client.application.i18n.SecurityTag;
import developen.client.application.widget.LoggedInCondition;
import developen.client.commercial.search.SystemPersonSearch;
import developen.client.framework.action.CancelAction;
import developen.client.framework.action.SearchAction;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.AllwaysEnabledCondition;
import developen.client.framework.widget.DBComboBox;
import developen.client.framework.widget.DBPasswordField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.client.framework.widget.DBSearchableField;
import developen.common.commercial.i18n.SystemCompanyTag;
import developen.common.commercial.i18n.SystemPersonTag;
import developen.common.commercial.mvc.Idiom;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.commercial.mvc.SystemPersonSystemCompany;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Controller;
import developen.common.framework.utils.I18N;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonPanel;
import developen.common.framework.widget.ButtonPanelAligment;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.InternalFrame;

public class LoginView extends InternalFrame implements CheckListener {


	private static final long serialVersionUID = -6528146745629365633L;

	private ExtendedPanel centerLayout;

	private ExtendedPanel northLayout;

	private ButtonPanel buttonLayout;

	private Button cancelButton;

	private Button loginButton;

	private SearchAction searchAction;

	private CancelAction cancelAction;

	private developen.client.application.action.LoginAction loginAction;

	private DBSearchField systemPersonField;

	private DBPasswordField passwordField;

	private DBComboBox systemCompanyField;

	private SystemPersonSearch systemPersonSearch;


	public LoginView(Controller controller) {

		super(controller);

	}


	public LoginController getController(){

		return (LoginController) super.getController();

	}


	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent evt) {


		if (evt.getPropertyName().equals(LoginController.MODEL_STATE)){

			LoginState newValue = (LoginState) evt.getNewValue();

			if (newValue.equals(LoginState.LOGGED_IN)){

				setVisible(false);

				getDesktopPane().remove(this);

				dispose();

			} else 

				if (newValue.equals(LoginState.CLEAN))

					if (getComponentAtTop() != null){

						SwingUtilities.invokeLater(new Runnable() {

							public void run() {

								getComponentAtTop().requestFocus();

							}

						});

					}

		} else {

			if (evt.getPropertyName().equals(LoginController.SYSTEM_PERSON_PROPERTY)){

				DefaultComboBoxModel<SystemCompany> model = (DefaultComboBoxModel<SystemCompany>) getSystemCompanyField().getModel();

				model.removeAllElements();

				SystemPerson systemPerson = (SystemPerson) evt.getNewValue();

				if (systemPerson!=null){

					Idiom idiom = systemPerson.getPreference().getIdiom();

					I18N.setLanguage(new Locale(idiom.getIdentifier().substring(0, 2), idiom.getIdentifier().substring(3, 5).toUpperCase()));

					for (SystemPersonSystemCompany company : systemPerson.getSystemCompanies())

						model.addElement(company.getIdentifier().getSystemCompany());

					if (systemPerson.getSystemCompanies().size()==1 || systemPerson.getPreference().getLastLoggedSystemCompany()==null)

						getSystemCompanyField().setSelectedItem(systemPerson.getSystemCompanies().get(0));

					else

						getSystemCompanyField().setSelectedItem(systemPerson.getPreference().getLastLoggedSystemCompany());	

				}

			}

		}


	}


	public void init() {


		setIconifiable(false);

		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

		addInternalFrameListener(new InternalFrameAdapter() {

			public void internalFrameClosing(InternalFrameEvent arg0) {

				try {

					getController().cancel();

				} catch (Exception exception) {

					Messenger.show(exception);

				}

			}

		});

		getActionMap().put(developen.client.application.mvc.LoginAction.CANCEL, new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {

				try {

					getController().cancel();

				} catch (Exception exception) {

					Messenger.show(exception);

				}	

			}

		});

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),

				developen.client.application.mvc.LoginAction.CANCEL);

		getActionMap().put(developen.client.application.mvc.LoginAction.SEARCH, getSearchAction());

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

				KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), 

				developen.client.application.mvc.LoginAction.SEARCH);

		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(getNorthLayout(), BorderLayout.NORTH);

		getContentPane().add(getCenterLayout(), BorderLayout.CENTER);

		getContentPane().add(getButtonLayout(), BorderLayout.SOUTH);

		buildInterface();


	}


	public void buildInterface(){


		setSize(new Dimension(600,300));

		DBRowPanel l = new DBRowPanel();
		
		l.addSeparator(new LoginTag());

		l.add(getSystemPersonField());

		l.add(getPasswordField());

		l.addSeparator(new PreferencesTag());

		l.add(getSystemCompanyField());

		getCenterLayout().add(l);
		

	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getSystemPersonField()){

			try{

				getController().changeSystemPersonProperty((SystemPerson) getSystemPersonField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getSystemPersonField().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		} else 

			if (event.getCheckable() == getPasswordField())

				getController().changePasswordProperty(String.valueOf(getPasswordField().getPassword()));


	}


	public Tag getInternalFrameTitle() {

		return new AccessControlTag();

	}


	public ImageIcon getInternalFrameIcon() {

		return new SecurityTag().getSmallIcon();

	}


	public DBSearchField getSystemPersonField() {


		if (systemPersonField == null){

			systemPersonField = new DBSearchField(new SystemPersonTag(), LoginController.SYSTEM_PERSON_PROPERTY);

			systemPersonField.addCheckListener(this);

			systemPersonField.setPreferredSize(new Dimension(350, UIConstants.DEFAULT_FIELD_HEIGHT));

			systemPersonField.setCondition(new AllwaysEnabledCondition());

			systemPersonField.setSearch(getSystemPersonSearch());

			getController().addView(systemPersonField);

		}

		return systemPersonField;


	}


	public DBPasswordField getPasswordField() {


		if (passwordField == null){

			passwordField = new DBPasswordField(new PasswordTag(), LoginController.PASSWORD_PROPERTY);

			passwordField.addCheckListener(this);

			passwordField.setPreferredSize(new Dimension(150,UIConstants.DEFAULT_FIELD_HEIGHT));

			passwordField.setCondition(new AllwaysEnabledCondition());

			getController().addView(passwordField);

		}

		return passwordField;

	}


	public DBComboBox getSystemCompanyField(){


		if (systemCompanyField == null){

			try {

				systemCompanyField = new DBComboBox(new SystemCompanyTag(), new SystemCompany[]{}, ClientController.SYSTEM_COMPANY_PROPERTY);

				systemCompanyField.setPreferredSize(new Dimension(350,24));

				systemCompanyField.setCondition(new LoggedInCondition());

				systemCompanyField.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						getController().changeSystemCompanyProperty((SystemCompany)((DBComboBox)e.getSource()).getSelectedItem());

					}

				});

				getController().addView(systemCompanyField);

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return systemCompanyField;


	}


	public ExtendedPanel getNorthLayout() {


		if (northLayout == null)

			northLayout = new ExtendedPanel();

		return northLayout;


	}


	public ExtendedPanel getCenterLayout() {


		if (centerLayout == null)

			centerLayout = new ExtendedPanel();
		
		return centerLayout;


	}


	protected ButtonPanel getButtonLayout() {


		if (buttonLayout == null){

			buttonLayout = new ButtonPanel();

			buttonLayout.add(getLoginButton(), ButtonPanelAligment.RIGHT);

			buttonLayout.add(getCancelButton(), ButtonPanelAligment.RIGHT);

		}

		return buttonLayout;

	}


	protected Button getLoginButton() {


		if (loginButton == null)

			loginButton = new Button(getLoginAction());

		return loginButton;


	}


	public developen.client.application.action.LoginAction getLoginAction(){


		if (loginAction == null){

			loginAction = new LoginAction() {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {

					try {

						getController().login();

					} catch (Exception exception) {

						Messenger.show(exception);

					}

				}

			};

			getController().addView(loginAction);

		}

		return loginAction;


	}


	protected Button getCancelButton() {


		if (cancelButton == null)

			cancelButton = new Button(getCancelAction());

		return cancelButton;


	}


	public SearchAction getSearchAction(){


		if (searchAction == null){

			searchAction = new SearchAction() {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {

					JInternalFrame f = (JInternalFrame) e.getSource();

					Component c = f.getFocusOwner();

					if (c!=null)

						if (c instanceof DBSearchableField)

							((DBSearchableField) c).getSearch().openSearchView(getDesktopPane());

				}

			};

		}

		return searchAction;


	}


	public CancelAction getCancelAction(){


		if (cancelAction == null){

			cancelAction = new CancelAction() {

				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {

					try {

						getController().cancel();

					} catch (Exception exception) {

						Messenger.show(exception);

					}

				}

			};	

			getController().addView(cancelAction);

		}

		return cancelAction;

	}


	public JComponent getComponentAtTop(){

		return getSystemPersonField();

	}


	public SystemPersonSearch getSystemPersonSearch(){


		if (systemPersonSearch == null){

			systemPersonSearch = new SystemPersonSearch(true);

			systemPersonSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeSystemPersonProperty((SystemPerson)event.getSelectedRows().get(0));

				}

			});

		}

		return systemPersonSearch;


	}


}