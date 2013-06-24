package developen.client.commercial.mvc;

import developen.common.commercial.i18n.SellerTag;
import developen.common.commercial.mvc.SaleOrder;
import developen.common.framework.exception.NotNullException;
import developen.common.subject.mvc.SystemPerson;



public abstract class SaleOrderController extends OutputOrderController {


	public static final String SELLER_PROPERTY = "Seller";

	
	public SaleOrder getModel(){
		
		return (SaleOrder) super.getModel();
		
	}

	
	public void changeSellerProperty(SystemPerson newValue) throws Exception{

		
		if (newValue==null)

			throw new NotNullException(new SellerTag());

		setModelProperty(SaleOrderController.SELLER_PROPERTY, newValue);
		

	}

	
	public void onClear() throws Exception{

		
		super.onClear();
		
		setModelProperty(SaleOrderController.SELLER_PROPERTY, null);

		
	}
	
	
	public void onInclude() throws Exception{
	
		
		super.onInclude();
		
		setModelProperty(SaleOrderController.SELLER_PROPERTY, getSystemPerson());
		
		
	}

	
	public abstract SystemPerson getSystemPerson();
	
	
}