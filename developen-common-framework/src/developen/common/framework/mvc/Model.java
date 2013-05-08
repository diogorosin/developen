package developen.common.framework.mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;


public abstract class Model implements Serializable {

	
	private static final long serialVersionUID = 9030542744695310258L;

	private PropertyChangeSupport propertyChangeSupport;

	
	private PropertyChangeSupport getPropertyChangeSupport(){
		

		if (propertyChangeSupport == null)
			
			propertyChangeSupport = new PropertyChangeSupport(this);
		
		return propertyChangeSupport;
		
		
	}

	
	public void addPropertyChangeListener(PropertyChangeListener listener) {

		getPropertyChangeSupport().addPropertyChangeListener(listener);

	}

	
	public void removePropertyChangeListener(PropertyChangeListener listener) {

		getPropertyChangeSupport().removePropertyChangeListener(listener);

	}

	
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {

		getPropertyChangeSupport().firePropertyChange(propertyName, oldValue, newValue);

	}

	
}