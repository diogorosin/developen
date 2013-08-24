package developen.client.application.widget;

import java.beans.PropertyChangeEvent;
import java.util.List;

import developen.client.application.mvc.ClientState;
import developen.client.framework.widget.Condition;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.commercial.mvc.SystemPersonSystemAction;
import developen.common.framework.mvc.View;

public class LoggedInAndEnabledCondition extends Condition {

	
	private boolean logged = false;
	
	private boolean enabled = true;

	
	public boolean analyse(PropertyChangeEvent event, View component) {

		
		if (event.getPropertyName().equals("ModelState"))

			setLogged(!event.getNewValue().equals(ClientState.LOGGED_OUT));
		
		if (event.getPropertyName().equals("SystemPerson")){
			
			SystemPerson systemPerson  = (SystemPerson) event.getNewValue();

			if (systemPerson != null && systemPerson.getSystemActions() != null){

				List<SystemPersonSystemAction> actions = systemPerson.getSystemActions();
				
				for (SystemPersonSystemAction personAction : actions) {

					setEnabled(component.getClass().getName().equals(personAction.getIdentifier().getSystemAction().getIdentifier()));
					
					if (isEnabled())
					
						break;

				}

			} else
				
				setEnabled(false);
			
		}

		return isLogged() && isEnabled();
		

	}


	public boolean isLogged() {
		
		return logged;
		
	}


	public void setLogged(boolean logged) {
		
		this.logged = logged;
		
	}


	public boolean isEnabled() {
		
		return enabled;
		
	}


	public void setEnabled(boolean enabled) {
		
		this.enabled = enabled;
		
	}
	

}