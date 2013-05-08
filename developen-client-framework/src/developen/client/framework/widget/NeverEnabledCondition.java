package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;


public class NeverEnabledCondition extends Condition {

	
	public boolean analyse(PropertyChangeEvent event, DBComponent component) {

		return false;
		
	}
	

}