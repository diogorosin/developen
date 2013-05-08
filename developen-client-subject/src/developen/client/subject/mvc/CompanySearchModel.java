package developen.client.subject.mvc;


public class CompanySearchModel extends SubjectSearchModel {

	
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