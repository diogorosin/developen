package developen.client.finance.mvc;

import developen.client.framework.mvc.SearchModel;


public class ConditionSearchModel extends SearchModel {

	
	private static final long serialVersionUID = 1539923053502563571L;

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