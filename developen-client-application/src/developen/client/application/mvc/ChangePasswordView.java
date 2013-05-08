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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import developen.client.application.i18n.ChangePasswordTag;
import developen.client.application.i18n.ConfirmNewPasswordTag;
import developen.client.application.i18n.NewPasswordTag;
import developen.client.application.i18n.SecurityTag;
import developen.client.application.i18n.SystemPersonTag;
import developen.client.application.search.SystemPersonSearch;
import developen.client.framework.action.CancelAction;
import developen.client.framework.action.ConfirmAction;
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

public class ChangePasswordView extends InternalFrame implements CheckListener{

	
	private static final long serialVersionUID = -8458587377058365473L;
	
	private ExtendedLayout centerLayout;
	
	private ButtonLayout buttonLayout;
	
	private Button cancelButton;
	
	private Button confirmButton;
	
	private CancelAction cancelAction;
	
	private ConfirmAction confirmAction;
	
	private DBTextField systemPersonField;
	
	private DBPasswordField newPasswordField;
	
	private DBPasswordField confirmNewPasswordField;
	
	private SystemPersonSearch systemPersonSearch;
	

	public ChangePasswordView(Controller controller) {

		super(controller);

	}
	

	public ChangePasswordController getController(){

		return (ChangePasswordController) super.getController();

	}
	

	public void modelPropertyChanged(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("ModelState")){

			if (evt.getNewValue().equals(ChangePasswordState.CANCELED) 
					
					|| evt.getNewValue().equals(ChangePasswordState.CONFIRMED)){

				setVisible(false);
				
				getDesktopPane().remove(this);
				
				dispose();

			} else {

				if (evt.getNewValue().equals(ChangePasswordState.EDITING)){

					if (getComponentAtTop() != null && getComponentAtTop().isFocusable())
						
						getComponentAtTop().requestFocus();

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

		getActionMap().put(ChangePasswordAction.CANCEL, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					getController().cancel();
					
				} catch (Exception exception) {
					
					Messenger.show(exception);
					
				}	
				
			}
			
		});
		
		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				
				ChangePasswordAction.CANCEL);
		
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(getCenterLayout(), BorderLayout.CENTER);
		
		getContentPane().add(getButtonLayout(), BorderLayout.SOUTH);
		
		buildInterface();
		

	}

	
	public void buildInterface(){

		
		setSize(new Dimension(650,300));
		
		DBRowLayout l = new DBRowLayout(150);
		
		l.add(getSystemPersonField());
		
		l.add(getNewPasswordField());
		
		l.add(getConfirmNewPasswordField());
		
		getCenterLayout().add(l);
		

	}

	
	public void onCheck(CheckEvent event) throws Exception {

		
		if (event.getCheckable() == getSystemPersonField()){

			try{

				getController().changeSystemPersonProperty(((SystemPerson)getSystemPersonField().getSearch().findBy()));

			} catch (ManyRecordsFoundException e) {

				getSystemPersonField().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		} else { 

			if (event.getCheckable() == getNewPasswordField()) {

				getController().changeNewPasswordProperty(String.valueOf(getNewPasswordField().getPassword()));

			} else {

				if (event.getCheckable() == getConfirmNewPasswordField())

					getController().changeConfirmNewPasswordProperty(String.valueOf(getConfirmNewPasswordField().getPassword()));

			}

		}

		
	}
	

	public Tag getInternalFrameTitle() {

		return new ChangePasswordTag();

	}

	
	public ImageIcon getInternalFrameIcon() {

		return new SecurityTag().getSmallIcon();

	}

	
	public DBTextField getSystemPersonField() {

	
		if (systemPersonField == null){

			systemPersonField = new DBTextField(new SystemPersonTag(), ChangePasswordController.SYSTEMPERSON_PROPERTY){

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent evt) {

					if (evt.getPropertyName().equals("ModelState")) {

						setEnabled(false);

					} else 

						if (evt.getPropertyName().equals(getPropertyName()))

							setText(evt.getNewValue()==null ? "" : evt.getNewValue().toString());

				}

			};
			
			systemPersonField.addCheckListener(this);
			
			systemPersonField.setColumns(27);
			
			systemPersonField.setSearch(getSystemPersonSearch());
			
			getController().addView(systemPersonField);

		}
		
		return systemPersonField;

		
	}

	
	public DBPasswordField getNewPasswordField() {

		
		if (newPasswordField == null){

			newPasswordField = new DBPasswordField(new NewPasswordTag(), ChangePasswordController.NEW_PASSWORD_PROPERTY);
			
			newPasswordField.addCheckListener(this);
			
			newPasswordField.setColumns(10);
			
			newPasswordField.setCondition(new AllwaysEnabledCondition());
			
			getController().addView(newPasswordField);

		}
		
		return newPasswordField;

		
	}

	
	public DBPasswordField getConfirmNewPasswordField() {

		
		if (confirmNewPasswordField == null){

			confirmNewPasswordField = new DBPasswordField(new ConfirmNewPasswordTag(), ChangePasswordController.CONFIRM_NEW_PASSWORD_PROPERTY){
				
				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent evt) {

					super.modelPropertyChanged(evt);
					
					setEnabled(getController().getModel().getNewPassword() != null 
							
							&& !getController().getModel().getNewPassword().isEmpty());
					
				}
				
			};
			
			confirmNewPasswordField.addCheckListener(this);
			
			confirmNewPasswordField.setColumns(10);
			
			getController().addView(confirmNewPasswordField);

		}
		
		return confirmNewPasswordField;
		

	}

	
	public ExtendedLayout getCenterLayout() {

		
		if (centerLayout == null)
			
			centerLayout = new ExtendedLayout();
		
		return centerLayout;
		

	}
	

	protected ButtonLayout getButtonLayout() {

		
		if (buttonLayout == null){
			
			buttonLayout = new ButtonLayout();
			
			buttonLayout.add(getConfirmButton(), ButtonLayoutAligment.RIGHT);
			
			buttonLayout.add(getCancelButton(), ButtonLayoutAligment.RIGHT);
			
		}
		
		return buttonLayout;
		

	}

	
	protected Button getConfirmButton() {

		
		if (confirmButton == null)
			
			confirmButton = new Button(getConfirmAction());
		
		return confirmButton;
		

	}
	

	public ConfirmAction getConfirmAction(){

	
		if (confirmAction == null){
			
			confirmAction = new ConfirmAction() {
				
				private static final long serialVersionUID = 1L;
				
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						getController().confirm();
						
					} catch (Exception exception) {
						
						Messenger.show(exception);
						
					}
					
				}
				
			};
			
			getController().addView(confirmAction);
			
		}
		
		return confirmAction;
		

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
