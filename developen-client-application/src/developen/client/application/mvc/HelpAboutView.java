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

import developen.client.application.i18n.HelpAboutInformationTag;
import developen.client.application.i18n.HelpAboutTag;
import developen.client.application.i18n.HelpTag;
import developen.client.framework.action.CancelAction;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Controller;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonLayout;
import developen.common.framework.widget.ButtonLayoutAligment;
import developen.common.framework.widget.ExtendedLayout;
import developen.common.framework.widget.InternalFrame;
import developen.common.framework.widget.Label;

public class HelpAboutView extends InternalFrame {

	
	private static final long serialVersionUID = 620267952342629888L;

	private ExtendedLayout centerLayout;
	
	private ButtonLayout buttonLayout;
	
	private Button cancelButton;	
	
	private CancelAction cancelAction;
	
	private Label infoLabel;	

	
	public HelpAboutView(Controller controller) {

		super(controller);

	}

	
	public HelpAboutController getController(){

		return (HelpAboutController) super.getController();

	}

	
	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		if (evt.getPropertyName().equals("ModelState")){

			if (evt.getNewValue().equals(HelpAboutState.CANCELED)){

				setVisible(false);
				
				getDesktopPane().remove(this);
				
				dispose();

			} else {

				if (evt.getNewValue().equals(HelpAboutState.CLEAN)){

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
		
		getActionMap().put(HelpAboutAction.CANCEL, new AbstractAction() {
			
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
				
				HelpAboutAction.CANCEL);
		
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(getCenterLayout(), BorderLayout.CENTER);
		
		getContentPane().add(getButtonLayout(), BorderLayout.SOUTH);
		
		buildInterface();
		

	}
	

	public void buildInterface(){

		setSize(new Dimension(450,300));

	}

	
	public Tag getInternalFrameTitle() {

		return new HelpAboutTag();

	}

	
	public ImageIcon getInternalFrameIcon() {

		return new HelpTag().getSmallIcon();

	}

	
	public ExtendedLayout getCenterLayout() {

		
		if (centerLayout == null){
			
			centerLayout = new ExtendedLayout();
			
			centerLayout.add(getInfoLabel());
			
		}
		
		return centerLayout;
		

	}
	

	protected ButtonLayout getButtonLayout() {

		
		if (buttonLayout == null){
			
			buttonLayout = new ButtonLayout();
			
			buttonLayout.add(getCancelButton(), ButtonLayoutAligment.RIGHT);
			
		}
		
		return buttonLayout;

		
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

		return getCancelButton();

	}

	
	public Label getInfoLabel() {
		
		
		if (infoLabel == null)
			
			infoLabel = new Label(new HelpAboutInformationTag());
		
		return infoLabel;
		
		
	}

	
}