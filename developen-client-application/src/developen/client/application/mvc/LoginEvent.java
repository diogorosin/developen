package developen.client.application.mvc;

import developen.common.subject.mvc.SystemPerson;


public class LoginEvent {
	
	
	private SystemPerson systemPerson;

	
	public LoginEvent(SystemPerson property){
		
		setSystemPerson(property);
		
	}

	
	public SystemPerson getSystemPerson() {
		
		return systemPerson;
		
	}

	
	public void setSystemPerson(SystemPerson systemPerson) {
		
		this.systemPerson = systemPerson;
		
	}
	
	
}