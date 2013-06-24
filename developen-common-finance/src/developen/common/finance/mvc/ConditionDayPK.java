package developen.common.finance.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;

@Embeddable
public class ConditionDayPK extends Model{


	private static final long serialVersionUID = 3648092197193455828L;

	@ManyToOne
	private Condition condition;
	
	@Column
	private Long day;

	
	public Condition getCondition() {
		
		return condition;
		
	}

	
	public void setCondition(Condition newValue) {
		
		
		Condition oldValue = this.condition;
		
		this.condition = newValue;
		
		firePropertyChange("Condition", oldValue, newValue);
		
		
	}

	
	public Long getDay() {
		
		return day;
		
	}

	
	public void setDay(Long newValue) {

		
		Long oldValue = this.day;
		
		this.day = newValue;
		
		firePropertyChange("Day", oldValue, newValue);
		
		
	}


	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result
				
				+ ((condition == null) ? 0 : condition.hashCode());
		
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		ConditionDayPK other = (ConditionDayPK) obj;
		
		if (condition == null) {
			
			if (other.condition != null)
				
				return false;
			
		} else if (!condition.equals(other.condition))
			
			return false;
		
		if (day == null) {
			
			if (other.day != null)
				
				return false;
			
		} else if (!day.equals(other.day))
			
			return false;
		
		return true;
		
		
	}

	
}