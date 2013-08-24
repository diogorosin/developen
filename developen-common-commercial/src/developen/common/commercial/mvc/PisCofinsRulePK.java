package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;

@Embeddable
public class PisCofinsRulePK extends Model{


	private static final long serialVersionUID = 4996502046486076544L;
	
	@ManyToOne
	private PisCofins pisCofins;
	
	@ManyToOne
	private Cfop cfop;

	@ManyToOne
	private Rule rule;

	
	public PisCofins getPisCofins() {
		
		return pisCofins;
		
	}

	
	public void setPisCofins(PisCofins newValue) {
		
		
		PisCofins oldValue = this.pisCofins;
		
		this.pisCofins = newValue;
		
		firePropertyChange("PisCofins", oldValue, newValue);
		
		
	}
	
	
	public Cfop getCfop() {
		
		return cfop;
		
	}

	
	public void setCfop(Cfop newValue) {
		
		
		Cfop oldValue = this.cfop;
		
		this.cfop = newValue;
		
		firePropertyChange("Cfop", oldValue, newValue);
		
		
	}

	
	public Rule getRule() {
		
		return rule;
		
	}

	
	public void setRule(Rule newValue) {

		
		Rule oldValue = this.rule;
		
		this.rule = newValue;
		
		firePropertyChange("Rule", oldValue, newValue);
		
		
	}


	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((cfop == null) ? 0 : cfop.hashCode());
		
		result = prime * result
				
				+ ((pisCofins == null) ? 0 : pisCofins.hashCode());
		
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		PisCofinsRulePK other = (PisCofinsRulePK) obj;
		
		if (cfop == null) {
			
			if (other.cfop != null)
				
				return false;
			
		} else if (!cfop.equals(other.cfop))
			
			return false;
		
		if (pisCofins == null) {
			
			if (other.pisCofins != null)
				
				return false;
			
		} else if (!pisCofins.equals(other.pisCofins))
			
			return false;
		
		if (rule == null) {
			
			if (other.rule != null)
				
				return false;
			
		} else if (!rule.equals(other.rule))
			
			return false;
		
		return true;
		
		
	}

	
}