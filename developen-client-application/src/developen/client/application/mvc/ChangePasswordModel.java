package developen.client.application.mvc;

import developen.common.framework.mvc.Model;
import developen.common.subject.mvc.SystemPerson;

public class ChangePasswordModel extends Model implements ChangePassword {

	
	private static final long serialVersionUID = -8188690606229400518L;

	private SystemPerson systemPerson;
	
	private String newPassword;
	
	private String confirmNewPassword;
	
	private ChangePasswordState modelState;
	
	
	public SystemPerson getSystemPerson() {
		
		return systemPerson;
		
	}

	
	public void setSystemPerson(SystemPerson newValue) {
	
		
		SystemPerson oldValue = this.systemPerson;
		
		this.systemPerson = newValue;
		
		firePropertyChange("SystemPerson", oldValue, newValue);
		
		
	}
	

	public String getNewPassword() {
		
		return newPassword;
		
	}
	

	public void setNewPassword(String newValue) {

		
		String oldValue = this.newPassword;
		
		this.newPassword = newValue;
		
		firePropertyChange("NewPassword", oldValue, newValue);
		
		
	}
	

	public String getConfirmNewPassword() {
		
		return confirmNewPassword;
		
	}
	

	public void setConfirmNewPassword(String newValue) {
		
	
		String oldValue = this.confirmNewPassword;
		
		this.confirmNewPassword = newValue;
		
		firePropertyChange("ConfirmNewPassword", oldValue, newValue);
		
		
	}


	public ChangePasswordState getModelState() {
		
		return modelState;
		
	} 

	
	public void setModelState(ChangePasswordState newValue) {

		
		ChangePasswordState oldValue = this.modelState;
		
		this.modelState = newValue;
		
		firePropertyChange("ModelState", oldValue, newValue);
		
		
	}

	
}