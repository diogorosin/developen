package developen.common.subject.mvc;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToOne;
import developen.common.persistence.annotation.Table;

@Table
public class Subject extends Model implements Entry{

	
	private static final long serialVersionUID = -8636743405067645513L;
	
	private EntryState modelState;

	@Identifier(sequence=true)
	private Long identifier;

	@Column
	private String denomination;
	
	@Column
	private Boolean active;
	
	@OneToOne
	private Address address;
	
	@ManyToOne
	private Rule rule;
	
	
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
	

	public Boolean getActive() {
		
		return active;
		
	}

	
	public void setActive(Boolean newValue) {
		
		
		Boolean oldValue = this.active;
		
		this.active = newValue;
		
		firePropertyChange("Active", oldValue, newValue);
		
		
	}


	public Address getAddress() {
		
		
		if (address==null)
			
			address = new Address();
		
		return address;

		
	}


	public void setAddress(Address newValue) {
		
		
		Address oldValue = this.address;
		
		this.address = newValue;
		
		firePropertyChange("Address", oldValue, newValue);
		
		
	}


	public Rule getRule() {
		
		
		if (rule==null)
			
			rule = new Rule();
		
		return rule;

		
	}


	public void setRule(Rule newValue) {
		
		
		Rule oldValue = this.rule;
		
		this.rule = newValue;
		
		firePropertyChange("Rule", oldValue, newValue);
		
		
	}

	
	public void setModelState(EntryState state) {
	
		
		EntryState oldValue = this.modelState;
		
		this.modelState = state;
		
		firePropertyChange("ModelState", oldValue, state);
		

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
		
		Subject other = (Subject) obj;
		
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