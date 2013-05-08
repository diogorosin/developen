package developen.common.engineer.mvc;

import java.util.List;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.Table;

@Table
public class UnitMeasure extends Model implements Entry{


	private static final long serialVersionUID = 7117879849221675913L;
	
	private EntryState modelState;

	@Identifier(sequence=true)
	private Long identifier;

	@Column
	private String denomination;

	@Column
	private String acronym;
	
	@ManyToOne
	private UnitMeasureGroup unitMeasureGroup;

	@OneToMany(mappedBy="fromUnitMeasure")
	private List<UnitMeasureConversion> conversions;


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

	
	public List<UnitMeasureConversion> getConversions() {
		
		return conversions;
		
	}


	public void setConversions(List<UnitMeasureConversion> newValue) {
		
		
		List<UnitMeasureConversion> oldValue = this.conversions;
		
		this.conversions = newValue;
		
		firePropertyChange("Conversions", oldValue, newValue);

		
	}


	public UnitMeasureGroup getUnitMeasureGroup() {
		
		return unitMeasureGroup;
		
	}


	public void setUnitMeasureGroup(UnitMeasureGroup newValue) {

		
		UnitMeasureGroup oldValue = this.unitMeasureGroup;
		
		this.unitMeasureGroup = newValue;
		
		firePropertyChange("UnitMeasureGroup", oldValue, newValue);
		
		
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
		
		return getDenomination() + " (" + getAcronym() + ")";
		
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
		
		UnitMeasure other = (UnitMeasure) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}


}