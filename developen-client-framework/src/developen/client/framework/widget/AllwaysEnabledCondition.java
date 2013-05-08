package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;


public class AllwaysEnabledCondition extends Condition {

	
	public boolean analyse(PropertyChangeEvent event, DBComponent component) {

		return true;
		
	}
	

}