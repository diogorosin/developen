package developen.client.commercial.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import developen.client.framework.mvc.ListEditorController;
import developen.common.commercial.i18n.OrderTag;
import developen.common.commercial.mvc.Order;
import developen.common.commercial.mvc.OrderItem;
import developen.common.commercial.mvc.OrderItemPK;
import developen.common.engineer.i18n.ProgenyTag;
import developen.common.engineer.mvc.Progeny;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;

public class OrderItemController extends ListEditorController {


	public static final String AMOUNT_PROPERTY = "Amount";

	public static final String PRICE_PROPERTY = "Price";

	public static final String VALUE_PROPERTY = "Value";


	public OrderItem getModel(){

		return (OrderItem) super.getModel();

	}


	public void changeOrderProperty(Order newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new OrderTag());

		getModel().getIdentifier().setOrder(newValue);


	}


	public void changeProgenyProperty(Progeny newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ProgenyTag());

		getModel().getIdentifier().setProgeny(newValue);

		changePriceProperty(getModel().getIdentifier().getProgeny().getPrice());


	}


	public void changeAmountProperty(Double newValue){


		setModelProperty(OrderItemController.AMOUNT_PROPERTY, newValue);

		setModelProperty(OrderItemController.VALUE_PROPERTY, newValue * getModel().getPrice());


	}


	public void changePriceProperty(Double newValue){


		setModelProperty(OrderItemController.PRICE_PROPERTY, newValue);

		setModelProperty(OrderItemController.VALUE_PROPERTY, newValue * getModel().getAmount());


	}


	public void changeValueProperty(Double newValue){

		setModelProperty(OrderItemController.VALUE_PROPERTY, newValue);

	}


	public void setModel(Model model){


		super.setModel(model);

		((OrderItem)model).getIdentifier().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				OrderItemPK orderItemPK = getModel().getIdentifier();

				if ((orderItemPK.getOrder() != null) 

						&& (orderItemPK.getProgeny() != null)){

					try{

						Object orderItem = null;

						for (Object object : getData()){

							if (object.hashCode()==getModel().hashCode()){

								orderItem = object;

								break;

							}

						}

						if (orderItem != null){

							refresh();

							edit();

						} else 

							include();

					} catch (Exception e) {

						Messenger.show(e);

					}

				}

			}

		});


	}


	protected void onClear() throws Exception{


		super.onClear();

		getModel().getIdentifier().setProgeny(null);

		setModelProperty(OrderItemController.AMOUNT_PROPERTY, new Double(0));

		setModelProperty(OrderItemController.PRICE_PROPERTY, new Double(0));

		setModelProperty(OrderItemController.VALUE_PROPERTY, new Double(0));


	}


	protected void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(OrderItemController.PRICE_PROPERTY, 
				
				getModel().getIdentifier().getProgeny().getPrice());


	}


}