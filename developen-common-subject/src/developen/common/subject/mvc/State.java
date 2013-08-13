package developen.common.subject.mvc;

import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;

@Table
public class State extends Model implements Search{


	private static final long serialVersionUID = -6353919483378662260L;
	
	@Identifier(sequence=true)
	private Long identifier;
	
	@Column
	private String denomination;
	
	@Column
	private String acronym;
	
	@ManyToOne
	private Country country;

	
	public Long getIdentifier() {
		
		return identifier;
		
	}

	
	public void setIdentifier(Long newValue) {
		

		Long oldValue = this.identifier;
		
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


	public String getAcronym() {
		
		return acronym;
		
	}
	
	
	public void setAcronym(String newValue) {
		
		
		String oldValue = this.acronym;
		
		this.acronym = newValue;
		
		firePropertyChange("Acronym", oldValue, newValue);
		
		
	}

	
	public Country getCountry() {
		
		return country;
		
	}


	public void setCountry(Country newValue) {
		

		Country oldValue = this.country;
		
		this.country = newValue;
		
		firePropertyChange("Country", oldValue, newValue);

		
	}


	public String toString(){

		return getDenomination();

	}


	public Object[] toColumns() {

		
		return new Object[]{
				
				getIdentifier(),
				
				getDenomination(),
				
				getAcronym(),

				getCountry()
				
		};
		
		
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
		
		State other = (State) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
	

}