package developen.common.commercial.mvc;

import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;

@Table
public class InputMacro extends Macro{

	
	private static final long serialVersionUID = -6933171148756968735L;

	@ManyToOne
	private InputCfop cfopState1;
	
	@ManyToOne
	private InputCfop cfopInterstate1;
	
	@ManyToOne
	private InputCfop cfopExterior1;
	
	@ManyToOne
	private InputCfop cfopState2;
	
	@ManyToOne
	private InputCfop cfopInterstate2;
	
	@ManyToOne
	private InputCfop cfopExterior2;

	@ManyToOne
	private InputCfop cfopState3;
	
	@ManyToOne
	private InputCfop cfopInterstate3;
	
	@ManyToOne
	private InputCfop cfopExterior3;

	@ManyToOne
	private InputCfop cfopState4;
	
	@ManyToOne
	private InputCfop cfopInterstate4;
	
	@ManyToOne
	private InputCfop cfopExterior4;

	
	public InputCfop getCfopState1() {
		
		return cfopState1;
		
	}

	
	public void setCfopState1(InputCfop newValue) {
		
		
		InputCfop oldValue = this.cfopState1;
		
		this.cfopState1 = newValue;
		
		firePropertyChange("CfopState1", oldValue, newValue);

		
	}

	
	public InputCfop getCfopInterstate1() {
		
		return cfopInterstate1;
		
	}

	
	public void setCfopInterstate1(InputCfop newValue) {
		

		InputCfop oldValue = this.cfopInterstate1;
		
		this.cfopInterstate1 = newValue;
		
		firePropertyChange("CfopInterstate1", oldValue, newValue);

		
	}

	
	public InputCfop getCfopExterior1() {
		
		return cfopExterior1;
		
	}

	
	public void setCfopExterior1(InputCfop newValue) {
		

		InputCfop oldValue = this.cfopExterior1;
		
		this.cfopExterior1 = newValue;
		
		firePropertyChange("CfopExterior1", oldValue, newValue);

		
	}
	
	
	public InputCfop getCfopState2() {
		
		return cfopState2;
		
	}

	
	public void setCfopState2(InputCfop newValue) {
		
		
		InputCfop oldValue = this.cfopState2;
		
		this.cfopState2 = newValue;
		
		firePropertyChange("CfopState2", oldValue, newValue);

		
	}

	
	public InputCfop getCfopInterstate2() {
		
		return cfopInterstate2;
		
	}

	
	public void setCfopInterstate2(InputCfop newValue) {
		

		InputCfop oldValue = this.cfopInterstate2;
		
		this.cfopInterstate2 = newValue;
		
		firePropertyChange("CfopInterstate2", oldValue, newValue);

		
	}

	
	public InputCfop getCfopExterior2() {
		
		return cfopExterior2;
		
	}

	
	public void setCfopExterior2(InputCfop newValue) {
		

		InputCfop oldValue = this.cfopExterior2;
		
		this.cfopExterior2 = newValue;
		
		firePropertyChange("CfopExterior2", oldValue, newValue);

		
	}

	
	public InputCfop getCfopState3() {
		
		return cfopState3;
		
	}

	
	public void setCfopState3(InputCfop newValue) {
		
		
		InputCfop oldValue = this.cfopState3;
		
		this.cfopState3 = newValue;
		
		firePropertyChange("CfopState3", oldValue, newValue);

		
	}

	
	public InputCfop getCfopInterstate3() {
		
		return cfopInterstate3;
		
	}

	
	public void setCfopInterstate3(InputCfop newValue) {
		

		InputCfop oldValue = this.cfopInterstate3;
		
		this.cfopInterstate3 = newValue;
		
		firePropertyChange("CfopInterstate3", oldValue, newValue);

		
	}

	
	public InputCfop getCfopExterior3() {
		
		return cfopExterior3;
		
	}

	
	public void setCfopExterior3(InputCfop newValue) {
		

		InputCfop oldValue = this.cfopExterior3;
		
		this.cfopExterior3 = newValue;
		
		firePropertyChange("CfopExterior3", oldValue, newValue);

		
	}

		
	public InputCfop getCfopState4() {
		
		return cfopState4;
		
	}

	
	public void setCfopState4(InputCfop newValue) {
		
		
		InputCfop oldValue = this.cfopState4;
		
		this.cfopState4 = newValue;
		
		firePropertyChange("CfopState4", oldValue, newValue);

		
	}

	
	public InputCfop getCfopInterstate4() {
		
		return cfopInterstate4;
		
	}

	
	public void setCfopInterstate4(InputCfop newValue) {
		

		InputCfop oldValue = this.cfopInterstate4;
		
		this.cfopInterstate4 = newValue;
		
		firePropertyChange("CfopInterstate4", oldValue, newValue);

		
	}

	
	public InputCfop getCfopExterior4() {
		
		return cfopExterior4;
		
	}

	
	public void setCfopExterior4(InputCfop newValue) {
		

		InputCfop oldValue = this.cfopExterior4;
		
		this.cfopExterior4 = newValue;
		
		firePropertyChange("CfopExterior4", oldValue, newValue);

		
	}
	
	
}