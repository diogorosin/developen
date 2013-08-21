package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class Cnpj extends Model {


	private static final long serialVersionUID = -7827849590004528788L;

	@Identifier
	private Long identifier;
	
	@Column
	private Long number;
	

	public Long getIdentifier() {
		
		return identifier;
		
	}


	public void setIdentifier(Long newValue) {
		

		Long oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);

		
	}


	public Long getNumber() {
		
		return number;
		
	}

	
	public void setNumber(Long newValue) {
		

		Long oldValue = this.number;
		
		this.number = newValue;
		
		firePropertyChange("Number", oldValue, newValue);

		
	}

	
	public String toString(){
		
		return getNumber().toString();				
		
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
		
		Cnpj other = (Cnpj) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
	
	
}