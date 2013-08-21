package developen.client.osm.action;


import java.awt.event.ActionEvent;

import developen.client.application.mvc.ClientController;
import developen.common.framework.i18n.ExitTag;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.Action;

public class ExitAction extends Action {

	
	private static final long serialVersionUID = 1439891185225088091L;
	
	private ClientController controller;
	

	public ExitAction(ClientController controller) {

		
		super(new ExitTag());
		
		setController(controller);

		
	}

	
	public void actionPerformed(ActionEvent e) {

		
		try {
			
			getController().exit();
			
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

}