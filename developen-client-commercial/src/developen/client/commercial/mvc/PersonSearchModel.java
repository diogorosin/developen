package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchModel;


public class PersonSearchModel extends SearchModel {

	
	private static final long serialVersionUID = -3787902622081943524L;

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