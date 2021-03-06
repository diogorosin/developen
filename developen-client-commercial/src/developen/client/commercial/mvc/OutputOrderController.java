package developen.client.commercial.mvc;

import developen.common.commercial.i18n.MacroTag;
import developen.common.commercial.mvc.OutputMacro;
import developen.common.commercial.mvc.OutputOrder;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.framework.exception.NotNullException;



public abstract class OutputOrderController extends OrderController {

	
	public static final String OUTPUT_MACRO_PROPERTY = "OutputMacro";

	
	public OutputOrder getModel(){
		
		return (OutputOrder) super.getModel();
		
	}

	
	public void changeOutputMacroProperty(OutputMacro newValue) throws Exception{

		
		if (newValue==null)

			throw new NotNullException(new MacroTag());

		setModelProperty(OutputOrderController.OUTPUT_MACRO_PROPERTY, newValue);
		

	}

	
	public void onInclude() throws Exception{
	
		
		super.onInclude();
		
		setModelProperty(OrderController.FROM_PROPERTY, getSystemCompany());
		
		setModelProperty(OutputOrderController.OUTPUT_MACRO_PROPERTY, null);
		
		
	}

	
	public void onClear() throws Exception{
	
		
		super.onClear();
		
		setModelProperty(OutputOrderController.OUTPUT_MACRO_PROPERTY, null);
		
		
	}

	
	public abstract SystemCompany getSystemCompany();
	
	
}