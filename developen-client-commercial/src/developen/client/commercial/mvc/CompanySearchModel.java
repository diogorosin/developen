package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchModel;


public class CompanySearchModel extends SearchModel {

	
	private static final long serialVersionUID = -1009783526264223616L;

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