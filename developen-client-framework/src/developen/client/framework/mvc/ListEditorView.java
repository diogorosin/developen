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
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonLayout;
import developen.common.framework.widget.ButtonLayoutAligment;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedLayout;
import developen.common.framework.widget.InternalFrame;


public abstract class ListEditorView extends InternalFrame implements CheckListener {


	private static final long serialVersionUID = -4370820176822925904L;

	private ExtendedLayout northLayout;

	private ExtendedLayout centerLayout;

	private ButtonLayout buttonLayout;

	private Button cancelButton;

	private Button deleteButton;

	private Button saveButton;

	private SaveAction saveAction;

	private DeleteAction deleteAction;

	private CancelAction cancelAction;


	public ListEditorView(ListEditorController controller) {

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

		getActionMap().put(ListEditorAction.CLEAR_OR_CANCEL, new AbstractAction() {

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

				ListEditorAction.CLEAR_OR_CANCEL);

		getActionMap().put(ListEditorAction.SAVE, new AbstractAction() {

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

				ListEditorAction.SAVE);

		getActionMap().put(ListEditorAction.DELETE, new AbstractAction() {

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

				ListEditorAction.DELETE);

		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(getNorthLayout(), BorderLayout.NORTH);

		getContentPane().add(getCenterLayout(), BorderLayout.CENTER);

		getContentPane().add(getButtonLayout(), BorderLayout.SOUTH);

		buildInterface();


	}


	public abstract void buildInterface();


	public void modelPropertyChanged(PropertyChangeEvent evt) {


		if (evt.getPropertyName().equals("ModelState")){

			ListEditorState newValue = (ListEditorState) evt.getNewValue();

			if (newValue.equals(ListEditorState.CANCELED)){

				setVisible(false);

				getDesktopPane().remove(this);

				dispose();

			} else {

				if (newValue.equals(ListEditorState.CLEAN)){

					if (ListEditorView.this.getComponentAtTop() != null 

							&& ListEditorView.this.getComponentAtTop().isFocusable())

						getComponentAtTop().requestFocus();

				}

			}

		}


	}


	public ListEditorController getController(){

		return (ListEditorController) super.getController();

	}


	public ImageIcon getInternalFrameIcon(){

		return new EntryTag().getSmallIcon();

	}


	public ExtendedLayout getNorthLayout() {


		if (northLayout == null)

			northLayout = new ExtendedLayout();

		return northLayout;


	}


	public ExtendedLayout getCenterLayout() {


		if (centerLayout == null)

			centerLayout = new ExtendedLayout();

		return centerLayout;


	}


	protected ButtonLayout getButtonLayout() {


		if (buttonLayout == null){

			buttonLayout = new ButtonLayout();

			buttonLayout.add(getSaveButton(), ButtonLayoutAligment.RIGHT);

			buttonLayout.add(getDeleteButton(), ButtonLayoutAligment.RIGHT);

			buttonLayout.add(getCancelButton(), ButtonLayoutAligment.RIGHT);

		}

		return buttonLayout;


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

						setEnabled(e.getNewValue().equals(ListEditorState.EDITING));

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

						setEnabled(e.getNewValue().equals(ListEditorState.EDITING) ||

								e.getNewValue().equals(ListEditorState.INCLUDING));

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


}