package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.i18n.HelpTag;
import developen.client.application.mvc.ClientController;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.Action;

public class HelpAction extends Action {


	private static final long serialVersionUID = -6181497408609505731L;
	
	private ClientController controller;

	
	public HelpAction(ClientController controller) {

		
		super(new HelpTag());
		
		setController(controller);
				
	}

	
	public void actionPerformed(ActionEvent e) {

		
		try {
			
			getController().help();
			
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