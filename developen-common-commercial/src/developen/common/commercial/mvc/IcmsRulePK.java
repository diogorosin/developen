package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;

@Embeddable
public class IcmsRulePK extends Model{


	private static final long serialVersionUID = 4996502046486076544L;
	
	@ManyToOne
	private Icms icms;
	
	@ManyToOne
	private State from;

	@ManyToOne
	private State to;

	@ManyToOne
	private Rule rule;

	
	public Icms getIcms() {
		
		return icms;
		
	}

	
	public void setIcms(Icms newValue) {
		
		
		Icms oldValue = this.icms;
		
		this.icms = newValue;
		
		firePropertyChange("Icms", oldValue, newValue);
		
		
	}
	
	
	public State getFrom() {
		
		return from;
		
	}

	
	public void setFrom(State newValue) {
		
		
		State oldValue = this.from;
		
		this.from = newValue;
		
		firePropertyChange("From", oldValue, newValue);
		
		
	}

	
	public State getTo() {
		
		return to;
		
	}

	
	public void setTo(State newValue) {

		
		State oldValue = this.to;
		
		this.to = newValue;
		
		firePropertyChange("To", oldValue, newValue);
		
		
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
		
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		
		result = prime * result + ((icms == null) ? 0 : icms.hashCode());
		
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
		
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
		
		IcmsRulePK other = (IcmsRulePK) obj;
		
		if (from == null) {
			
			if (other.from != null)
				
				return false;
			
		} else if (!from.equals(other.from))
			
			return false;
		
		if (icms == null) {
			
			if (other.icms != null)
				
				return false;
			
		} else if (!icms.equals(other.icms))
			
			return false;
		
		if (rule == null) {
			
			if (other.rule != null)
				
				return false;
			
		} else if (!rule.equals(other.rule))
			
			return false;
		
		if (to == null) {
			
			if (other.to != null)
				
				return false;
			
		} else if (!to.equals(other.to))
			
			return false;
		
		return true;
		
		
	}

	
}