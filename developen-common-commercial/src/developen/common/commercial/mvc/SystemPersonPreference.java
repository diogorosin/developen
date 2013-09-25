package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;

@Table
public class SystemPersonPreference extends Model {


	private static final long serialVersionUID = -4912249766001872135L;

	@Identifier
	private Long identifier;
	
	@ManyToOne
	private Idiom idiom;
	
	@ManyToOne
	private SystemCompany lastLoggedSystemCompany;
	

	public Long getIdentifier() {
		
		return identifier;
		
	}


	public void setIdentifier(Long newValue) {
		

		Long oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);

		
	}
	

	public Idiom getIdiom() {
		
		return idiom;
		
	}
	

	public void setIdiom(Idiom newValue) {
	
		
		Idiom oldValue = this.idiom;
		
		this.idiom = newValue;
		
		firePropertyChange("Idiom", oldValue, newValue);
		
		
	}
	

	public SystemCompany getLastLoggedSystemCompany() {
		
		return lastLoggedSystemCompany;
		
	}
	

	public void setLastLoggedSystemCompany(SystemCompany newValue) {
	
		
		SystemCompany oldValue = this.lastLoggedSystemCompany;
		
		this.lastLoggedSystemCompany = newValue;
		
		firePropertyChange("LastLoggedSystemCompany", oldValue, newValue);
		
		
	}

	
	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result
				
				+ ((identifier == null) ? 0 : identifier.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		SystemPersonPreference other = (SystemPersonPreference) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
	
	
}