package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchModel;


public class MacroSearchModel extends SearchModel {

	
	private static final long serialVersionUID = 8853920111618205645L;
	
	private Boolean active;

	
	public void setActive(Boolean newValue) {

		
		this.active = newValue;
		
		Boolean oldValue = this.active;
		
		firePropertyChange("Active", oldValue, newValue);
		

	}

	
	public Boolean getActive() {

		return active;

	}

	
}