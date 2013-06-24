package developen.client.commercial.mvc;

import developen.common.commercial.mvc.OutputOrder;
import developen.common.subject.mvc.SystemCompany;



public abstract class OutputOrderController extends OrderController {

	
	public OutputOrder getModel(){
		
		return (OutputOrder) super.getModel();
		
	}

	
	public void onInclude() throws Exception{
	
		
		super.onInclude();
		
		setModelProperty(OrderController.FROM_PROPERTY, getSystemCompany());
		
		
	}

	
	public abstract SystemCompany getSystemCompany();
	
	
}