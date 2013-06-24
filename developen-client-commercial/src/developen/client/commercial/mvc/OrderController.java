package developen.client.commercial.mvc;


import java.util.ArrayList;
import java.util.List;

import developen.client.framework.mvc.EntryController;
import developen.common.commercial.mvc.Order;
import developen.common.commercial.mvc.OrderItem;
import developen.common.engineer.i18n.FromTag;
import developen.common.engineer.i18n.ToTag;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.subject.i18n.NumberTag;
import developen.common.subject.mvc.Subject;

public class OrderController extends EntryController {

	
	public static final String IDENTIFIER_PROPERTY = "Identifier";
	
	public static final String FROM_PROPERTY = "From";
	
	public static final String TO_PROPERTY = "To";
	
	public static final String ITEMS_PROPERTY = "Items";


	public Order getModel(){
		
		return (Order) super.getModel();
		
	}

	
	public void changeIdentifierProperty(Long newValue) throws Exception {

		
		if (newValue > 999999)
			
			throw new OutOfRangeException(new NumberTag(), 0, 999999);

		setModelProperty(OrderController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			refresh();

		else 

			include();
		

	}
	

	public void changeFromProperty(Subject newValue) throws Exception{

		
		if (newValue==null)

			throw new NotNullException(new FromTag());

		setModelProperty(OrderController.FROM_PROPERTY, newValue);
		

	}

	
	public void changeToProperty(Subject newValue) throws Exception{

		
		if (newValue==null)

			throw new NotNullException(new ToTag());

		setModelProperty(OrderController.TO_PROPERTY, newValue);
		

	}

	
	public void changeItemsProperty(List<OrderItem> newValue){
		
		setModelProperty(OrderController.ITEMS_PROPERTY, newValue);
		
	}

	
	public void onClear() throws Exception{

	
		super.onClear();
		
		setModelProperty(OrderController.IDENTIFIER_PROPERTY, null);
		
		setModelProperty(OrderController.FROM_PROPERTY, null);
		
		setModelProperty(OrderController.TO_PROPERTY, null);
		
		setModelProperty(OrderController.ITEMS_PROPERTY, null);
		
		
	}
	

	public void onInclude() throws Exception{

		
		super.onInclude();
		
		setModelProperty(OrderController.ITEMS_PROPERTY, new ArrayList<OrderItem>());
		
		
	}

	
}