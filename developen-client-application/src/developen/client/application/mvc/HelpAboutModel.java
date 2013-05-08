package developen.client.application.mvc;

import developen.common.framework.mvc.Model;

public class HelpAboutModel extends Model implements HelpAbout {

	
	private static final long serialVersionUID = -8543094750456089873L;

	private HelpAboutState modelState;
	

	public void setModelState(HelpAboutState newValue) {

		
		HelpAboutState oldValue = this.modelState;
		
		this.modelState = newValue;
		
		firePropertyChange("ModelState", oldValue, newValue);
		

	}


	public HelpAboutState getModelState() {
	
		return modelState;
		
	}
	

}