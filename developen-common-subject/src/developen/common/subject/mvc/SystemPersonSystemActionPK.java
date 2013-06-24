package developen.common.subject.mvc;

import java.io.Serializable;

import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;



@Embeddable
public class SystemPersonSystemActionPK implements Serializable {

	
	private static final long serialVersionUID = -1217505250361725664L;

	@ManyToOne
	private SystemPerson systemPerson;
	
	@ManyToOne
	private SystemAction systemAction;

	
	public SystemPersonSystemActionPK(){
		
		
	};
	
	
	public SystemPersonSystemActionPK(SystemPerson systemPerson, SystemAction systemAction){
		
		
		setSystemPerson(systemPerson);
		
		setSystemAction(systemAction);
		
		
	}
	
	
	public SystemPerson getSystemPerson() {
		
		return systemPerson;
		
	}
	
	
	public void setSystemPerson(SystemPerson systemPerson) {
		
		this.systemPerson = systemPerson;
		
	}
	
	
	public SystemAction getSystemAction() {
		
		return systemAction;
		
	}
	
	
	public void setSystemAction(SystemAction systemAction) {
		
		this.systemAction = systemAction;
		
	}


	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result
				
				+ ((systemAction == null) ? 0 : systemAction.hashCode());
		
		result = prime * result
				
				+ ((systemPerson == null) ? 0 : systemPerson.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		SystemPersonSystemActionPK other = (SystemPersonSystemActionPK) obj;
		
		if (systemAction == null) {
			
			if (other.systemAction != null)
				
				return false;
			
		} else if (!systemAction.equals(other.systemAction))
			
			return false;
		
		if (systemPerson == null) {
			
			if (other.systemPerson != null)
				
				return false;
			
		} else if (!systemPerson.equals(other.systemPerson))
			
			return false;
		
		return true;
	
		
	}
	

}