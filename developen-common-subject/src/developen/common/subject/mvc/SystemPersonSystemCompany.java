package developen.common.subject.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class SystemPersonSystemCompany extends Model {

	
	private static final long serialVersionUID = 1276572100842474590L;

	@Identifier
	private SystemPersonSystemCompanyPK identifier;
	
	
	public SystemPersonSystemCompany(){
		
		
	}

	
	public SystemPersonSystemCompany(SystemPersonSystemCompanyPK identifier){
		
		this.identifier = identifier;
		
	}
	
	
	public SystemPersonSystemCompanyPK getIdentifier() {
		
		return identifier;
		
	}

	
	public void setIdentifier(SystemPersonSystemCompanyPK newValue) {
	
		
		SystemPersonSystemCompanyPK oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);
		
		
	}

	
	public String toString(){
		
		return getIdentifier().getSystemCompany().getDenomination();
		
	}
	

	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		
		return result;
		
		
	}

	
	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		SystemPersonSystemCompany other = (SystemPersonSystemCompany) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
 
	
}