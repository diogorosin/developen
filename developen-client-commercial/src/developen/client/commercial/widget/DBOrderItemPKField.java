package developen.client.commercial.widget;

import java.awt.BorderLayout;

import javax.swing.JComponent;

import developen.client.commercial.mvc.OrderItemPKController;
import developen.client.commercial.mvc.OrderItemPKView;
import developen.common.commercial.mvc.OrderItemPK;


public class DBOrderItemPKField extends JComponent{


	private static final long serialVersionUID = -6009120201640056143L;

	private OrderItemPKView view;

	private OrderItemPKController controller;


	public DBOrderItemPKField(OrderItemPK model){


		controller = new OrderItemPKController();

		view = new OrderItemPKView(controller);

		controller.addView(view);

		controller.setModel(model);

		setLayout(new BorderLayout());

		add(view, BorderLayout.CENTER);


	}


	public OrderItemPKView getView() {

		return view;

	}


	public OrderItemPKController getController() {

		return controller;

	}


	public void requestFocus(){


		if (getView().getOrderField().isEnabled())

			getView().getOrderField().requestFocus();

		else

			if (getView().getProgenyField().isEnabled())

				getView().getProgenyField().requestFocus();


	}


}