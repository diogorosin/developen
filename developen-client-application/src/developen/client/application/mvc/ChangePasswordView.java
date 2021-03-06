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
import developen.client.commercial.search.SystemPersonSearch;
import developen.client.framework.action.CancelAction;
import developen.client.framework.action.ConfirmAction;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.AllwaysEnabledCondition;
import developen.client.framework.widget.DBPasswordField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.SystemPersonTag;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonPanel;
import developen.common.framework.widget.ButtonPanelAligment;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.InternalFrame;

public class ChangePasswordView extends InternalFrame implements CheckListener{

	
	private static final long serialVersionUID = -8458587377058365473L;
	
	private ExtendedPanel centerLayout;
	
	private ButtonPanel buttonLayout;
	
	private Button cancelButton;
	
	private Button confirmButton;
	
	private CancelAction cancelAction;
	
	private ConfirmAction confirmAction;
	
	private DBSearchField systemPersonField;
	
	private DBPasswordField newPasswordField;
	
	private DBPasswordField confirmNewPasswordField;
	
	

	public ChangePasswordView(ChangePasswordController controller) {

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

		
		setSize(new Dimension(700,300));
		
		DBRowPanel l = new DBRowPanel(150);
		
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

	
	public DBSearchField getSystemPersonField() {

	
		if (systemPersonField == null){

			SystemPersonSearch systemPersonSearch = new SystemPersonSearch(true);
			
			systemPersonSearch.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeSystemPersonProperty((SystemPerson)event.getSelectedRows().get(0));

				}
				
			});

			systemPersonField = new DBSearchField(new SystemPersonTag(), ChangePasswordController.SYSTEMPERSON_PROPERTY);
			
			systemPersonField.addCheckListener(this);
			
			systemPersonField.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));
			
			systemPersonField.setSearch(systemPersonSearch);
			
			systemPersonField.setCondition(new NeverEnabledCondition());
			
			getController().addView(systemPersonField);

		}
		
		return systemPersonField;

		
	}

	
	public DBPasswordField getNewPasswordField() {

		
		if (newPasswordField == null){

			newPasswordField = new DBPasswordField(new NewPasswordTag(), ChangePasswordController.NEW_PASSWORD_PROPERTY);
			
			newPasswordField.addCheckListener(this);
			
			newPasswordField.setPreferredSize(new Dimension(150,UIConstants.DEFAULT_FIELD_HEIGHT));
			
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
			
			confirmNewPasswordField.setPreferredSize(new Dimension(150,UIConstants.DEFAULT_FIELD_HEIGHT));
			
			getController().addView(confirmNewPasswordField);

		}
		
		return confirmNewPasswordField;
		

	}

	
	public ExtendedPanel getCenterLayout() {

		
		if (centerLayout == null)
			
			centerLayout = new ExtendedPanel();
		
		return centerLayout;
		

	}
	

	protected ButtonPanel getButtonLayout() {

		
		if (buttonLayout == null){
			
			buttonLayout = new ButtonPanel();
			
			buttonLayout.add(getConfirmButton(), ButtonPanelAligment.RIGHT);
			
			buttonLayout.add(getCancelButton(), ButtonPanelAligment.RIGHT);
			
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

	
}
