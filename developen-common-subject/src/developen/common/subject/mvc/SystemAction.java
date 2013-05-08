package developen.common.subject.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class SystemAction extends Model {


	private static final long serialVersionUID = -7132009236635138398L;
	
	@Identifier
	private String identifier;
	
	
	public SystemAction(){
		
		
	};
	
	
	public SystemAction(String identifier){
		
		setIdentifier(identifier);
		
	};
	
	
	public String getIdentifier() {
		
		return identifier;
		
	}

	
	public void setIdentifier(String newValue) {
		
		
		String oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);
		
		
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
		
		SystemAction other = (SystemAction) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}


	public String toString(){

		return getIdentifier(); 

	}

	
}