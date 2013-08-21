package developen.client.commercial.mvc;


import developen.common.commercial.i18n.OrderTag;
import developen.common.commercial.i18n.ProgenyTag;
import developen.common.commercial.mvc.Order;
import developen.common.commercial.mvc.OrderItemPK;
import developen.common.commercial.mvc.Progeny;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;

public class OrderItemPKController extends Controller{


	public static final String ORDER_PROPERTY = "Order";

	public static final String PROGENY_PROPERTY = "Progeny";


	public OrderItemPK getModel(){

		return (OrderItemPK) super.getModel();

	}


	public void setModel(OrderItemPK model){

		super.setModel(model);

	}


	public void changeOrderProperty(Order newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new OrderTag());

		setModelProperty(OrderItemPKController.ORDER_PROPERTY, newValue);


	}

	
	public void changeProgenyProperty(Progeny newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ProgenyTag());

		setModelProperty(OrderItemPKController.PROGENY_PROPERTY, newValue);


	}


}