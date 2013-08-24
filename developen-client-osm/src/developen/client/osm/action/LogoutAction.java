package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.i18n.LogoutTag;
import developen.client.application.widget.LoggedInCondition;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBAction;
import developen.client.osm.Client;
import developen.common.framework.messenger.Messenger;

public class LogoutAction extends DBAction {


	private static final long serialVersionUID = 6266063387573349158L;
	

	public LogoutAction() {

		super(new LogoutTag());
		
	}
 
	
	public void actionPerformed(ActionEvent e) {

		
		try {

			Client.getClientController().logout();

		} catch (Exception exception) {

			Messenger.show(exception);

		}
		

	}	

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new LoggedInCondition();
		
		return condition;

		
	}
 
	
}