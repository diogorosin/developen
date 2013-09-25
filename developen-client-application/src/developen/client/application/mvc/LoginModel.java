package developen.client.application.mvc;

import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.framework.mvc.Model;

public class LoginModel extends Model implements Login{
	

	private static final long serialVersionUID = -4676103007802782892L;

	private SystemPerson systemPerson;
	
	private String password;

	private SystemCompany systemCompany;
	
	private LoginState modelState;
	

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


	public SystemCompany getSystemCompany() {
		
		return systemCompany;
		
	}
	

	public void setSystemCompany(SystemCompany newValue) {
		
		
		SystemCompany oldValue = this.systemCompany;
		
		this.systemCompany = newValue;
		
		firePropertyChange("SystemCompany", oldValue, newValue);
		
		
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