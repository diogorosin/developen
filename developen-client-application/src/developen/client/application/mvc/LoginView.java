package developen.client.application.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import developen.client.application.action.LoginAction;
import developen.client.application.i18n.LoginTag;
import developen.client.application.i18n.PasswordTag;
import developen.client.application.i18n.SecurityTag;
import developen.client.application.i18n.SystemPersonTag;
import developen.client.application.search.SystemPersonSearch;
import developen.client.framework.action.CancelAction;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.AllwaysEnabledCondition;
import developen.client.framework.widget.DBPasswordField;
import developen.client.framework.widget.DBRowLayout;
import developen.client.framework.widget.DBTextField;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Controller;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonLayout;
import developen.common.framework.widget.ButtonLayoutAligment;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedLayout;
import developen.common.framework.widget.InternalFrame;
import developen.common.subject.mvc.SystemPerson;

public class LoginView extends InternalFrame implements CheckListener {


	private static final long serialVersionUID = -6528146745629365633L;
	
	private ExtendedLayout centerLayout;
	
	private ButtonLayout buttonLayout;
	
	private Button cancelButton;
	
	private Button loginButton;
	
	private CancelAction cancelAction;
	
	private developen.client.application.action.LoginAction loginAction;
	
	private DBTextField systemPersonField;
	
	private DBPasswordField passwordField;
	
	private SystemPersonSearch systemPersonSearch;
	

	public LoginView(Controller controller) {

		super(controller);

	}

	
	public LoginController getController(){

		return (LoginController) super.getController();

	}

	
	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		if (evt.getPropertyName().equals(LoginController.MODEL_STATE)){

			LoginState newValue = (LoginState) evt.getNewValue();

			if (newValue.equals(LoginState.LOGGEDIN)){

				setVisible(false);
				
				getDesktopPane().remove(this);
				
				dispose();

			} else 

				if (newValue.equals(LoginState.EDITING))

					if (getComponentAtTop() != null){

						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								
								getComponentAtTop().requestFocus();
								
							}
							
						});

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
		
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(getCenterLayout(), BorderLayout.CENTER);
		
		getContentPane().add(getButtonLayout(), BorderLayout.SOUTH);
		
		buildInterface();
		

	}
	

	public void buildInterface(){

	
		setSize(new Dimension(550,300));
		
		DBRowLayout l = new DBRowLayout();
		
		l.add(getSystemPersonField());
		
		l.add(getPasswordField());
		
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

		return new LoginTag();

	}

	
	public ImageIcon getInternalFrameIcon() {

		return new SecurityTag().getSmallIcon();

	}

	
	public DBTextField getSystemPersonField() {

		
		if (systemPersonField == null){

			systemPersonField = new DBTextField(new SystemPersonTag(), LoginController.SYSTEMPERSON_PROPERTY);
			
			systemPersonField.addCheckListener(this);
			
			systemPersonField.setColumns(27);
			
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
			
			passwordField.setColumns(10);
			
			passwordField.setCondition(new AllwaysEnabledCondition());
			
			getController().addView(passwordField);

		}
		
		return passwordField;

	}

	
	public ExtendedLayout getCenterLayout() {

		
		if (centerLayout == null)
			
			centerLayout = new ExtendedLayout();
		
		return centerLayout;
		

	}

	
	protected ButtonLayout getButtonLayout() {

		
		if (buttonLayout == null){
			
			buttonLayout = new ButtonLayout();
			
			buttonLayout.add(getLoginButton(), ButtonLayoutAligment.RIGHT);
			
			buttonLayout.add(getCancelButton(), ButtonLayoutAligment.RIGHT);
			
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