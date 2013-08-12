package developen.client.commercial.mvc;

import developen.common.commercial.i18n.OutputMacroTag;
import developen.common.commercial.mvc.OutputMacro;
import developen.common.commercial.mvc.OutputOrder;
import developen.common.framework.exception.NotNullException;
import developen.common.subject.mvc.SystemCompany;



public abstract class OutputOrderController extends OrderController {

	
	public static final String OUTPUT_MACRO_PROPERTY = "OutputMacro";

	
	public OutputOrder getModel(){
		
		return (OutputOrder) super.getModel();
		
	}

	
	public void changeOutputMacroProperty(OutputMacro newValue) throws Exception{

		
		if (newValue==null)

			throw new NotNullException(new OutputMacroTag());

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