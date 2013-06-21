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
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import developen.client.framework.action.CancelAction;
import developen.client.framework.action.ConfirmAction;
import developen.client.framework.i18n.EntryTag;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.PropertyEditorState;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonPanel;
import developen.common.framework.widget.ButtonPanelAligment;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.InternalFrame;

public abstract class PropertyEditorView extends InternalFrame {


	private static final long serialVersionUID = -7289956168048606628L;
	
	private ExtendedPanel centerLayout;
	
	private ExtendedPanel northLayout;
	
	private ButtonPanel buttonLayout;

	private Button confirmButton;
	
	private Button cancelButton;

	private ConfirmAction confirmAction;
	
	private CancelAction cancelAction;

	
	public PropertyEditorView(PropertyEditorController controller) {

		
		super(controller);
		
		setIconifiable(false);
		

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

		getActionMap().put(PropertyEditorAction.CANCEL, new AbstractAction() {
			
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
				
				PropertyEditorAction.CANCEL);

		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(getNorthLayout(), BorderLayout.NORTH);
		
		getContentPane().add(getCenterLayout(), BorderLayout.CENTER);
		
		getContentPane().add(getButtonLayout(), BorderLayout.SOUTH);
		
		buildInterface();
		

	}

	
	public ImageIcon getInternalFrameIcon() {

		return new EntryTag().getSmallIcon();

	}

	
	public PropertyEditorController getController(){

		return (PropertyEditorController) super.getController();

	}

	
	public void setController(PropertyEditorController controller){

		super.setController(controller);

	}

	
	public abstract void buildInterface();
	

	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		if (evt.getPropertyName().equals(PropertyEditorController.MODEL_STATE_PROPERTY)){

			PropertyEditorState newValue = (PropertyEditorState) evt.getNewValue();

			if ((newValue.equals(PropertyEditorState.CANCELED)) 
					
					|| (newValue.equals(PropertyEditorState.CONFIRMED))){

				setVisible(false);
				
				getDesktopPane().remove(this);
				
				dispose();

			} else 

				if (newValue.equals(PropertyEditorState.EDITING))

					if (getComponentAtTop() != null){

						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								
								getComponentAtTop().requestFocus();
								
							}
							
						});

					}

		}
		

	}

	
	public ExtendedPanel getCenterLayout() {

		
		if (centerLayout == null)
			
			centerLayout = new ExtendedPanel();
		
		return centerLayout;
		

	}
	
	
	public ExtendedPanel getNorthLayout(){

		
		if (northLayout==null)
			
			northLayout = new ExtendedPanel();
			
		return northLayout;
		

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

	
	public ConfirmAction getConfirmAction() {

		
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

	
	public abstract JComponent getComponentAtTop();
	

}