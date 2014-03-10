package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchModel;
import developen.common.commercial.mvc.Icms;
import developen.common.commercial.mvc.Rule;
import developen.common.commercial.mvc.State;


public class IcmsRuleSearchModel extends SearchModel {

	
	private static final long serialVersionUID = 8853920111618205645L;
	
	private Icms icms;
	
	private State from;

	private State to;

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


}