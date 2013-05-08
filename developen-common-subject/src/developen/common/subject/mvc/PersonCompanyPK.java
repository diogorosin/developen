package developen.common.subject.mvc;

import java.io.Serializable;

import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;


@Embeddable
public class PersonCompanyPK implements Serializable{

	
	private static final long serialVersionUID = -2623857371492618678L;

	@ManyToOne
	private SystemPerson systemPerson;
	
	@ManyToOne
	private SystemCompany systemCompany;
	
	
	public PersonCompanyPK(){
		
		
	};
	
	
	public PersonCompanyPK(SystemPerson systemPerson, SystemCompany systemCompany){
		
		
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
	

	public int hashCode() {
	
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result
				
				+ ((systemCompany == null) ? 0 : systemCompany.hashCode());
		
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
		
		PersonCompanyPK other = (PersonCompanyPK) obj;
		
		if (systemCompany == null) {
			
			if (other.systemCompany != null)
				
				return false;
			
		} else if (!systemCompany.equals(other.systemCompany))
			
			return false;
		
		if (systemPerson == null) {
			
			if (other.systemPerson != null)
				
				return false;
			
		} else if (!systemPerson.equals(other.systemPerson))
			
			return false;
		
		return true;
		
		
	}
	
	
}