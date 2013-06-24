package developen.common.finance.mvc;

import developen.common.framework.mvc.ListEditor;
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class ConditionDay extends Model implements Search, ListEditor {


	private static final long serialVersionUID = -883963928751659203L;

	private ListEditorState modelState;

	@Identifier
	private ConditionDayPK identifier;

	@Column
	private Boolean fixedPercentage;
	
	@Column
	private Double valuePercentage;

	
	public ConditionDayPK getIdentifier() {
		
		
		if (identifier==null)

			identifier = new ConditionDayPK();

		return identifier;
		
		
	}
	

	public void setIdentifier(ConditionDayPK newValue) {
		

		ConditionDayPK oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);
		
		
	}


	public Boolean getFixedPercentage() {
		
		return fixedPercentage;
		
	}


	public void setFixedPercentage(Boolean newValue) {
		
		
		Boolean oldValue = this.fixedPercentage;
		
		this.fixedPercentage = newValue;
		
		firePropertyChange("FixedPercentage", oldValue, newValue);

		
	}


	public Double getValuePercentage() {
		
		return valuePercentage;
		
	}


	public void setValuePercentage(Double newValue) {
		
		
		Double oldValue = this.valuePercentage;
		
		this.valuePercentage = newValue;
		
		firePropertyChange("ValuePercentage", oldValue, newValue);

		
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

		return getIdentifier().getDay().toString();

	}


	public Object[] toColumns() {

		
		return new Object[]{ 
				
				getIdentifier().getDay(),
				
				getFixedPercentage(),
				
				getValuePercentage()
				
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
		
		ConditionDay other = (ConditionDay) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
	

}