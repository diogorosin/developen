package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class Cnae extends Model implements Search{
	

	private static final long serialVersionUID = 9057503010977777603L;

	@Identifier
	private String identifier;

	@Column
	private String denomination;


	public String getIdentifier() {

		return identifier;

	}


	public void setIdentifier(String newValue) {


		String oldValue = this.identifier;

		this.identifier = newValue;

		firePropertyChange("Identifier", oldValue, newValue);


	}


	public String getDenomination() {

		return denomination;

	}


	public void setDenomination(String newValue) {


		String oldValue = this.denomination;

		this.denomination = newValue;

		firePropertyChange("Denomination", oldValue, newValue);


	}


	public String toString(){

		return getDenomination();

	}


	public Object[] toColumns() {

		
		return new Object[]{

				getIdentifier(),
				
				getDenomination()

		};

		
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
		
		Cnae other = (Cnae) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
	
	
}