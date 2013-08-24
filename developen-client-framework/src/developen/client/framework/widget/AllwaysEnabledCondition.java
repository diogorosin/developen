package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.mvc.View;


public class AllwaysEnabledCondition extends Condition {

	
	public boolean analyse(PropertyChangeEvent event, View component) {

		return true;
		
	}
	

}