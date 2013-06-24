package developen.common.subject.mvc;

import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class Role extends Model implements Search{


	private static final long serialVersionUID = 4524856701013761728L;

	@Identifier
	private Long identifier;

	@Column
	private String denomination;
	
	@Column
	private Boolean supervision;
	
	@Column
	private Boolean sale;

	
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
	
	
	public Boolean getSupervision() {

		return supervision;

	}
	

	public void setSupervision(Boolean newValue) {

		
		Boolean oldValue = this.supervision;
		
		this.supervision = newValue;
		
		firePropertyChange("Supervision", oldValue, newValue);
		

	}

	
	public Boolean getSale() {

		return sale;

	}
	

	public void setSale(Boolean newValue) {

		
		Boolean oldValue = this.sale;
		
		this.sale = newValue;
		
		firePropertyChange("Sale", oldValue, newValue);
		

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
		
		Role other = (Role) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}

	
}