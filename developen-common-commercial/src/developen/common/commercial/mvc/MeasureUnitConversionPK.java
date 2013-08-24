package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;

@Embeddable
public class MeasureUnitConversionPK extends Model{


	private static final long serialVersionUID = 4996502046486076544L;
	
	@ManyToOne
	private MeasureUnit from;
	
	@ManyToOne
	private MeasureUnit to;

	
	public MeasureUnit getFrom() {
		
		return from;
		
	}

	
	public void setFrom(MeasureUnit newValue) {
		
		
		MeasureUnit oldValue = this.from;
		
		this.from = newValue;
		
		firePropertyChange("From", oldValue, newValue);
		
		
	}

	
	public MeasureUnit getTo() {
		
		return to;
		
	}

	
	public void setTo(MeasureUnit newValue) {

		
		MeasureUnit oldValue = this.to;
		
		this.to = newValue;
		
		firePropertyChange("To", oldValue, newValue);
		
		
	}


	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		MeasureUnitConversionPK other = (MeasureUnitConversionPK) obj;
		
		if (from == null) {
			
			if (other.from != null)
				
				return false;
			
		} else if (!from.equals(other.from))
			
			return false;
		
		if (to == null) {
			
			if (other.to != null)
				
				return false;
			
		} else if (!to.equals(other.to))
			
			return false;
		
		return true;
		
		
	}

	
}