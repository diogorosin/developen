package developen.common.commercial.mvc;

import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;

@Table
public class OutputOrder extends Order {


	private static final long serialVersionUID = 6317620587785600752L;
	
	@ManyToOne
	private OutputMacro outputMacro;

	
	public OutputMacro getOutputMacro() {

		return outputMacro;

	}


	public void setOutputMacro(OutputMacro newValue) {


		OutputMacro oldValue = this.outputMacro;

		this.outputMacro = newValue;

		firePropertyChange("OutputMacro", oldValue, newValue);


	}

	
}