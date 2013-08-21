package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchModel;


public class ProgenySearchModel extends SearchModel {


	private static final long serialVersionUID = -5744380970607143253L;
	
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