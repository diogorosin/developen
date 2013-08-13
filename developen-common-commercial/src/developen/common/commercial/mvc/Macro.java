package developen.common.commercial.mvc;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class Macro extends Model implements Entry, Search{

	
	private static final long serialVersionUID = 7490607156220905408L;

	private EntryState modelState;

	@Identifier(sequence=true)
	private Long identifier;
	
	@Column
	private String denomination;
	
	@Column
	private Boolean active;

	@Column
	private Boolean icms;
	
	@Column
	private Boolean ipi;
	
	@Column
	private Boolean pisCofins;
	
	@Column
	private Boolean iss;
	
	@Column
	private Boolean stock;

	@Column
	private Boolean finance;

	
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

	
	public Boolean getIcms() {
		
		return icms;
		
	}

	
	public void setIcms(Boolean newValue) {
		
		
		Boolean oldValue = this.icms;
		
		this.icms = newValue;
		
		firePropertyChange("Icms", oldValue, newValue);
		
		
	}

	
	public Boolean getIpi() {
		
		return ipi;
		
	}

	
	public void setIpi(Boolean newValue) {
		
		
		Boolean oldValue = this.ipi;
		
		this.ipi = newValue;
		
		firePropertyChange("Ipi", oldValue, newValue);
		
		
	}

	
	public Boolean getPisCofins() {
		
		return pisCofins;
		
	}

	
	public void setPisCofins(Boolean newValue) {
		
		
		Boolean oldValue = this.pisCofins;
		
		this.pisCofins = newValue;
		
		firePropertyChange("PisCofins", oldValue, newValue);
		
		
	}

	
	public Boolean getIss() {
		
		return iss;
		
	}

	
	public void setIss(Boolean newValue) {
		
		
		Boolean oldValue = this.iss;
		
		this.iss = newValue;
		
		firePropertyChange("Iss", oldValue, newValue);
		
		
	}

	
	public Boolean getStock() {
		
		return stock;
		
	}

	
	public void setStock(Boolean newValue) {
		
		
		Boolean oldValue = this.stock;
		
		this.stock = newValue;
		
		firePropertyChange("Stock", oldValue, newValue);
		
		
	}

	
	public Boolean getFinance() {
		
		return finance;
		
	}

	
	public void setFinance(Boolean newValue) {
		
		
		Boolean oldValue = this.finance;
		
		this.finance = newValue;
		
		firePropertyChange("Finance", oldValue, newValue);
		
		
	}

	
	public void setModelState(EntryState state) {


		EntryState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}

	
	public EntryState getModelState() {

		return modelState;

	}


	public String toString (){

		return getDenomination();

	}


	public Object[] toColumns() {


		return new Object[]{

				getIdentifier(),
				
				getDenomination(),
				
				getStock(),
				
				getFinance()

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
		
		Macro other = (Macro) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}

	
}