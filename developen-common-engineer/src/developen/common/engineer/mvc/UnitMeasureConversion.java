package developen.common.engineer.mvc;

import developen.common.framework.mvc.ListEditor;
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class UnitMeasureConversion extends Model implements Search, ListEditor {


	private static final long serialVersionUID = -7594301808140494489L;
	
	private ListEditorState modelState;

	@Identifier
	private UnitMeasureConversionPK identifier;

	@Column
	private Double value;
	
	
	public UnitMeasureConversionPK getIdentifier() {
		
		
		if (identifier==null)

			identifier = new UnitMeasureConversionPK();

		return identifier;
		
		
	}
	

	public void setIdentifier(UnitMeasureConversionPK newValue) {
		

		UnitMeasureConversionPK oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);
		
		
	}


	public Double getValue() {
		
		return value;
		
	}


	public void setValue(Double newValue) {
		
		
		Double oldValue = this.value;
		
		this.value = newValue;
		
		firePropertyChange("Value", oldValue, newValue);

		
	}


	public void setModelState(ListEditorState state) {


		ListEditorState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public ListEditorState getModelState() {

		return modelState;

	}


	public String toString(){

		return getIdentifier().getToUnitMeasure().getDenomination();

	}


	public Object[] toColumns() {

		return new Object[]{ getIdentifier().getToUnitMeasure(), getValue() };

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
		
		UnitMeasureConversion other = (UnitMeasureConversion) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
	

}
