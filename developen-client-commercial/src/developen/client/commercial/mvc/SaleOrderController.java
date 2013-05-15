package developen.client.commercial.mvc;

import developen.common.subject.mvc.SystemCompany;



public abstract class SaleOrderController extends OrderController {

	
	public void onInclude() throws Exception{

		
		super.onInclude();
				
		setModelProperty(OrderController.FROM_PROPERTY, getSystemCompany());

		
	}

	
	public abstract SystemCompany getSystemCompany();
	

}