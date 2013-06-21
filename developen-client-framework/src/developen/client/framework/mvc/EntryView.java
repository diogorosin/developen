package developen.client.framework.mvc;

import java.awt.BorderLayout;
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

import developen.client.framework.action.CancelAction;
import developen.client.framework.action.DeleteAction;
import developen.client.framework.action.SaveAction;
import developen.client.framework.i18n.EntryTag;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonPanel;
import developen.common.framework.widget.ButtonPanelAligment;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.InternalFrame;


public abstract class EntryView extends InternalFrame implements CheckListener, SystemPersonListener {


	private static final long serialVersionUID = -4370820176822925904L;

	private ExtendedPanel northPanel;

	private ExtendedPanel centerPanel;

	private ButtonPanel buttonPanel;

	private Button cancelButton;

	private Button deleteButton;

	private Button saveButton;

	private SaveAction saveAction;

	private DeleteAction deleteAction;

	private CancelAction cancelAction;


	public EntryView(EntryController controller) {

		super(controller);

	}


	public void init() {


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

		getActionMap().put(EntryAction.CLEAR_OR_CANCEL, new AbstractAction() {

			private static final long serialVersionUID = -1862018329518595289L;

			public void actionPerformed(ActionEvent arg0) {

				try {

					if (getController().isClean())

						getController().cancel();

					else

						getController().clear();

				} catch (Exception exception) {

					Messenger.show(exception);

				}	

			}

		});

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 

				EntryAction.CLEAR_OR_CANCEL);

		getActionMap().put(EntryAction.SAVE, new AbstractAction() {

			private static final long serialVersionUID = -1862018329518595289L;

			public void actionPerformed(ActionEvent arg0) {

				try {

					getController().save();

				} catch (Exception exception) {

					Messenger.show(exception);

				}	

			}

		});

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

				KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), 

				EntryAction.SAVE);

		getActionMap().put(EntryAction.DELETE, new AbstractAction() {

			private static final long serialVersionUID = -1862018329518595289L;

			public void actionPerformed(ActionEvent arg0) {

				try {

					getController().delete();

				} catch (Exception exception) {

					Messenger.show(exception);

				}	

			}

		});

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

				KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), 

				EntryAction.DELETE);

		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(getNorthPanel(), BorderLayout.NORTH);

		getContentPane().add(getCenterPanel(), BorderLayout.CENTER);

		getContentPane().add(getButtonPanel(), BorderLayout.SOUTH);

		buildInterface();


	}


	public abstract void buildInterface();


	public void modelPropertyChanged(PropertyChangeEvent evt) {


		if (evt.getPropertyName().equals("ModelState")){

			EntryState newValue = (EntryState) evt.getNewValue();

			if (newValue.equals(EntryState.CANCELED)){

				setVisible(false);

				getDesktopPane().remove(this);

				dispose();

			} else {

				if (newValue.equals(EntryState.CLEAN)){

					if (EntryView.this.getComponentAtTop() != null 

							&& EntryView.this.getComponentAtTop().isFocusable())

						EntryView.this.getComponentAtTop().requestFocus();

				} else {

					if (newValue.equals(EntryState.INCLUDING)){

//						if (getController().getModel() instanceof Auditable){
//
//							Component component = this;
//
//							while (component != null){
//
//								if (component instanceof AuditProvider){
//
//									((Auditable)getController().getModel()).setCreatedBy(
//
//											((AuditProvider)component).getUser());
//									
//									break;
//
//								}
//
//								component = component.getParent();
//
//							}
//
//						}

					} else {

						if (newValue.equals(EntryState.EDITING)){

//							if (getController().getModel() instanceof Auditable){
//
//								Component component = this;
//
//								while (component != null){
//
//									if (component instanceof AuditProvider){
//
//										((Auditable)getController().getModel()).setModifiedBy(
//
//												((AuditProvider)component).getUser());
//
//										break;
//
//									}
//
//									component = component.getParent();
//
//								}
//
//							}

						}

					}

				}

			}

		}


	}


	public EntryController getController(){

		return (EntryController) super.getController();

	}

	
	public Tag getInternalFrameTitle(){
		
		return new EntryTag();
		
	}


	public ImageIcon getInternalFrameIcon(){

		return new EntryTag().getSmallIcon();

	}


	public ExtendedPanel getNorthPanel() {


		if (northPanel == null)

			northPanel = new ExtendedPanel();

		return northPanel;


	}


	public ExtendedPanel getCenterPanel() {


		if (centerPanel == null)

			centerPanel = new ExtendedPanel();

		return centerPanel;


	}


	protected ButtonPanel getButtonPanel() {


		if (buttonPanel == null){

			buttonPanel = new ButtonPanel();

			buttonPanel.add(getSaveButton(), ButtonPanelAligment.RIGHT);

			buttonPanel.add(getDeleteButton(), ButtonPanelAligment.RIGHT);

			buttonPanel.add(getCancelButton(), ButtonPanelAligment.RIGHT);

		}

		return buttonPanel;


	}


	protected Button getSaveButton() {


		if (saveButton == null)

			saveButton = new Button(getSaveAction());

		return saveButton;


	}


	protected Button getDeleteButton() {


		if (deleteButton == null)

			deleteButton = new Button(getDeleteAction());

		return deleteButton;


	}


	protected Button getCancelButton() {


		if (cancelButton == null)

			cancelButton = new Button(getCancelAction());

		return cancelButton;


	}


	public CancelAction getCancelAction(){


		if (cancelAction == null){

			cancelAction = new CancelAction() {

				private static final long serialVersionUID = 2692429442391537609L;

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


	public DeleteAction getDeleteAction(){


		if (deleteAction == null){

			deleteAction = new DeleteAction() {

				private static final long serialVersionUID = 2658419979483024893L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING));

				}

				public void actionPerformed(ActionEvent e) {

					try {

						getController().delete();

					} catch (Exception exception) {

						Messenger.show(exception);

					}

				}

			};

			getController().addView(deleteAction);

		}

		return deleteAction;


	}


	public SaveAction getSaveAction() {


		if (saveAction == null){

			saveAction = new SaveAction() {

				private static final long serialVersionUID = -4856209104015211823L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING) ||

								e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {

					try {

						getController().save();

					} catch (Exception exception) {

						Messenger.show(exception);

					}

				}

			};

			getController().addView(saveAction);

		}

		return saveAction;


	}


	public abstract JComponent getComponentAtTop(); 


	public void onLogout(SystemPersonEvent event) throws Exception{
		
		getController().cancel();

	}
	
	
	public void onLogin(SystemPersonEvent event){
		
		

	}

	
}