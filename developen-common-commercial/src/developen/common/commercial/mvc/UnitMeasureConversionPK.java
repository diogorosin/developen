package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;

@Embeddable
public class UnitMeasureConversionPK extends Model{


	private static final long serialVersionUID = 4996502046486076544L;
	
	@ManyToOne
	private UnitMeasure fromUnitMeasure;
	
	@ManyToOne
	private UnitMeasure toUnitMeasure;

	
	public UnitMeasure getFromUnitMeasure() {
		
		return fromUnitMeasure;
		
	}

	
	public void setFromUnitMeasure(UnitMeasure newValue) {
		
		
		UnitMeasure oldValue = this.fromUnitMeasure;
		
		this.fromUnitMeasure = newValue;
		
		firePropertyChange("FromUnitMeasure", oldValue, newValue);
		
		
	}

	
	public UnitMeasure getToUnitMeasure() {
		
		return toUnitMeasure;
		
	}

	
	public void setToUnitMeasure(UnitMeasure newValue) {

		
		UnitMeasure oldValue = this.toUnitMeasure;
		
		this.toUnitMeasure = newValue;
		
		firePropertyChange("ToUnitMeasure", oldValue, newValue);
		
		
	}


	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((fromUnitMeasure == null) ? 0 : fromUnitMeasure.hashCode());
		
		result = prime * result + ((toUnitMeasure == null) ? 0 : toUnitMeasure.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		UnitMeasureConversionPK other = (UnitMeasureConversionPK) obj;
		
		if (fromUnitMeasure == null) {
			
			if (other.fromUnitMeasure != null)
				
				return false;
			
		} else if (!fromUnitMeasure.equals(other.fromUnitMeasure))
			
			return false;
		
		if (toUnitMeasure == null) {
			
			if (other.toUnitMeasure != null)
				
				return false;
			
		} else if (!toUnitMeasure.equals(other.toUnitMeasure))
			
			return false;
		
		return true;
		
		
	}

	
}