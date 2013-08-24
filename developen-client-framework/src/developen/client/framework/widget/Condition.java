package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.mvc.View;

public abstract class Condition {
	
	
	public abstract boolean analyse(PropertyChangeEvent event, View component);
	
	
}