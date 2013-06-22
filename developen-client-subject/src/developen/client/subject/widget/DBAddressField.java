package developen.client.subject.widget;

import java.awt.BorderLayout;

import javax.swing.JComponent;

import developen.client.subject.mvc.AddressController;
import developen.client.subject.mvc.AddressView;
import developen.common.subject.mvc.Address;

public class DBAddressField extends JComponent {


	private static final long serialVersionUID = -6299602364215133877L;
	
	private AddressController controller;
	
	private AddressView view;

	
	public DBAddressField(Address model){

		
		controller = new AddressController();
		
		view = new AddressView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);

		setLayout(new BorderLayout());
		
		add(view, BorderLayout.CENTER);
		
		
	}


	public AddressController getController() {

		return controller;

	}

	
	public AddressView getView() {

		return view;

	}

	
	public void requestFocus(){

	
		if (getView().getPlayAreaField().isFocusable())
			
			getView().getPlayAreaField().requestFocus();

		
	}

	
}