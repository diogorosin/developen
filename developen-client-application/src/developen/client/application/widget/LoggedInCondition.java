package developen.client.application.widget;

import java.beans.PropertyChangeEvent;

import developen.client.application.mvc.ClientState;
import developen.client.framework.widget.Condition;
import developen.common.framework.mvc.View;

public class LoggedInCondition extends Condition {

	
	private boolean loggedIn = true;

	
	public boolean analyse(PropertyChangeEvent event, View component) {

		
		if (event.getPropertyName().equals("ModelState"))

			setLoggedIn(!event.getNewValue().equals(ClientState.LOGGED_OUT));

		return isLoggedIn();
		

	}


	public boolean isLoggedIn() {
		
		return loggedIn;
		
	}


	public void setLoggedIn(boolean loggedIn) {
		
		this.loggedIn = loggedIn;
		
	}
	

}