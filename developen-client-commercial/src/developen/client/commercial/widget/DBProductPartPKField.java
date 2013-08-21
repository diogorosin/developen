package developen.client.commercial.widget;

import java.awt.BorderLayout;

import javax.swing.JComponent;

import developen.client.commercial.mvc.ProductPartPKController;
import developen.client.commercial.mvc.ProductPartPKView;
import developen.common.commercial.mvc.ProductPartPK;


public class DBProductPartPKField extends JComponent{


	private static final long serialVersionUID = -6009120201640056143L;
	
	private ProductPartPKView view;
	
	private ProductPartPKController controller;
	

	public DBProductPartPKField(ProductPartPK model){

		
		controller = new ProductPartPKController();
		
		view = new ProductPartPKView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);

		setLayout(new BorderLayout());
		
		add(view, BorderLayout.CENTER);
		

	}

	
	public ProductPartPKView getView() {

		return view;

	}
	

	public ProductPartPKController getController() {

		return controller;

	}

	
	public void requestFocus(){

		
		if (getView().getProductField().isEnabled())
			
			getView().getProductField().requestFocus();
		
		else
			
			if (getView().getPartField().isEnabled())
				
				getView().getPartField().requestFocus();

		
	}

	
}