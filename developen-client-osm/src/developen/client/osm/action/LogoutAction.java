package developen.client.osm.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import developen.client.application.action.ApplicationAction;
import developen.client.application.i18n.LogoutTag;
import developen.client.application.mvc.ClientController;
import developen.common.framework.messenger.Messenger;

public class LogoutAction extends ApplicationAction {


	private static final long serialVersionUID = 6266063387573349158L;
	
	private ClientController controller;
	

	public LogoutAction(ClientController controller) {

		
		super(new LogoutTag());
		
		setController(controller);
		
		
	}

	
	public void actionPerformed(ActionEvent e) {

		
		try {
			
			getController().logout();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}
		

	}

	
	public ClientController getController() {

		return controller;

	}

	
	public void setController(ClientController controller) {

		this.controller = controller;

	}

	
	public void modelPropertyChanged(PropertyChangeEvent e) {

		
		if (e.getPropertyName().equals(ClientController.SYSTEM_PERSON_PROPERTY))

			setEnabled(e.getNewValue() != null);

		
	}
	

}