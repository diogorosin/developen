package developen.common.commercial.mvc;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class ProductDetail extends Model implements Entry, Search{
	
	
	private static final long serialVersionUID = -680187333503380526L;

	private EntryState modelState;

	@Identifier(sequence=true)
	private Long identifier;

	@Column
	private String denomination;

	@Column
	private String shortDenomination;

	
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


	public String getShortDenomination() {
		
		return shortDenomination;
		
	}
	
	
	public void setShortDenomination(String newValue) {
		
		
		String oldValue = this.shortDenomination;
		
		this.shortDenomination = newValue;
		
		firePropertyChange("ShortDenomination", oldValue, newValue);
		
		
	}

	
	public void setModelState(EntryState state) {


		EntryState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public Object[] toColumns() {


		return new Object[]{

				getIdentifier(),

				getDenomination(),
				
				getShortDenomination()

		};


	}
	

	public EntryState getModelState() {

		return modelState;

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
		
		ProductDetail other = (ProductDetail) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
	

	public String toString(){

		return getDenomination(); 

	}

}