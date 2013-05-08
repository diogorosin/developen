package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

public abstract class Condition {
	
	
	public abstract boolean analyse(PropertyChangeEvent event, DBComponent component);
	
	
}