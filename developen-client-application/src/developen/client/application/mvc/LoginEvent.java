package developen.client.application.mvc;

import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;


public class LoginEvent {
	
	
	private SystemPerson systemPerson;

	private SystemCompany systemCompany;
	
	
	public LoginEvent(SystemPerson systemPerson, SystemCompany systemCompany){
		
		
		setSystemPerson(systemPerson);
		
		setSystemCompany(systemCompany);
		
		
	}

	
	public SystemPerson getSystemPerson() {
		
		return systemPerson;
		
	}

	
	public void setSystemPerson(SystemPerson systemPerson) {
		
		this.systemPerson = systemPerson;
		
	}


	public SystemCompany getSystemCompany() {
		
		return systemCompany;
		
	}


	public void setSystemCompany(SystemCompany systemCompany) {
		
		this.systemCompany = systemCompany;
		
	}
	
	
}