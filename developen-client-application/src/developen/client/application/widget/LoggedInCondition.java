package developen.client.application.widget;

import java.beans.PropertyChangeEvent;

import developen.client.application.mvc.ClientState;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComponent;

public class LoggedInCondition extends Condition {

	
	boolean latestState = true;

	
	public boolean analyse(PropertyChangeEvent event, DBComponent component) {

		
		if (event.getPropertyName().equals("ModelState"))

			latestState = !event.getNewValue().equals(ClientState.LOGOUT);

		return latestState;
		

	}
	

}