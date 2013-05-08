package developen.client.application.mvc;

import developen.common.framework.mvc.Model;
import developen.common.subject.mvc.SystemCompany;
import developen.common.subject.mvc.SystemPerson;

public class ClientModel extends Model implements Client {


	private static final long serialVersionUID = 1831593178580066468L;
	
	private SystemPerson systemPerson;
	
	private SystemCompany systemCompany;

	private ClientState modelState;
	

	public SystemPerson getSystemPerson() {

		return systemPerson;

	}

	
	public void setSystemPerson(SystemPerson newValue) {

		
		SystemPerson oldValue = this.systemPerson;
		
		this.systemPerson = newValue;
		
		firePropertyChange("SystemPerson", oldValue, newValue);
		

	}

	
	public SystemCompany getSystemCompany() {
		
		return systemCompany;
		
	}

	
	public void setSystemCompany(SystemCompany newValue) {
	
		
		SystemCompany oldValue = this.systemCompany;
		
		this.systemCompany = newValue;
		
		firePropertyChange("SystemCompany", oldValue, newValue);
		
		
	}


	public void setModelState(ClientState newValue) {

		
		ClientState oldValue = this.modelState;
		
		this.modelState = newValue;
		
		firePropertyChange("ModelState", oldValue, newValue);
		
		
	}


	public ClientState getModelState() {

		return modelState;
		
	}

	
}