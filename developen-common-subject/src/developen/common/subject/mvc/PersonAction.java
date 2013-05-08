package developen.common.subject.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class PersonAction extends Model {

	
	private static final long serialVersionUID = -8359538482937758484L;
	
	@Identifier
	private PersonActionPK identifier;
	
	
	public PersonAction(){
		
		
	}

	
	public PersonAction(PersonActionPK identifier){
		
		this.identifier = identifier;
		
	}

	
	public PersonActionPK getIdentifier() {
		
		return identifier;
		
	}

	
	public void setIdentifier(PersonActionPK newValue) {
	
		
		PersonActionPK oldValue = this.identifier;
		
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
		
		PersonAction other = (PersonAction) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}

	
}