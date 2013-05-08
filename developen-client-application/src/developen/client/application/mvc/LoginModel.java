package developen.client.application.mvc;

import developen.common.framework.mvc.Model;
import developen.common.subject.mvc.SystemPerson;

public class LoginModel extends Model implements Login{
	

	private static final long serialVersionUID = -4676103007802782892L;

	private SystemPerson systemPerson;
	
	private LoginState modelState;

	private String password;
	

	public SystemPerson getSystemPerson() {
		
		return systemPerson;
		
	}
	

	public void setSystemPerson(SystemPerson newValue) {
		
		
		SystemPerson oldValue = this.systemPerson;
		
		this.systemPerson = newValue;
		
		firePropertyChange("SystemPerson", oldValue, newValue);
		
		
	}

	
	public String getPassword() {
		
		return password;
		
	}
	

	public void setPassword(String newValue) {
		
		
		String oldValue = this.password;
		
		this.password = newValue;
		
		firePropertyChange("Password", oldValue, newValue);
		
		
	}


	public void setModelState(LoginState state) {
	
		
		LoginState oldValue = this.modelState;
		
		this.modelState = state;
		
		firePropertyChange("ModelState", oldValue, state);
		

	}


	public LoginState getModelState() {
	
		return modelState;
		
	}

	
}