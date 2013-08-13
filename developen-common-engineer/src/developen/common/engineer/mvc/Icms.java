package developen.common.engineer.mvc;

import java.util.List;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.Table;

@Table
public class Icms extends Model implements Entry, Search{


	private static final long serialVersionUID = -416281488779522235L;

	private EntryState modelState;

	@Identifier
	private Long identifier;

	@Column
	private String denomination;

	@OneToMany(mappedBy="icms")
	private List<IcmsRule> rules;


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


	public List<IcmsRule> getRules() {

		return rules;

	}


	public void setRules(List<IcmsRule> newValue) {


		List<IcmsRule> oldValue = this.rules;

		this.rules = newValue;

		firePropertyChange("Rules", oldValue, newValue);


	}


	public void setModelState(EntryState state) {


		EntryState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public EntryState getModelState() {

		return modelState;

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
		
		Icms other = (Icms) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}


}