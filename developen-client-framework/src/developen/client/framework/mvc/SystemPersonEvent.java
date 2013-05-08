package developen.client.framework.mvc;

import developen.common.subject.mvc.SystemPerson;

public class SystemPersonEvent {

	
	private SystemPerson systemPerson;

	
	public SystemPersonEvent(SystemPerson systemPerson){
		
		setSystemPerson(systemPerson);		
		
	}

	
	public SystemPerson getSystemPerson() {
		
		return systemPerson;
		
	}

	
	public void setSystemPerson(SystemPerson systemPerson) {
		
		this.systemPerson = systemPerson;
		
	}

	
}