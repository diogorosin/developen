package developen.client.commercial.widget;

import java.awt.BorderLayout;

import javax.swing.JComponent;

import developen.client.commercial.mvc.UnitMeasureConversionPKController;
import developen.client.commercial.mvc.UnitMeasureConversionPKView;
import developen.common.commercial.mvc.UnitMeasureConversionPK;


public class DBUnitMeasureConversionPKField extends JComponent{


	private static final long serialVersionUID = -6009120201640056143L;
	
	private UnitMeasureConversionPKView view;
	
	private UnitMeasureConversionPKController controller;
	

	public DBUnitMeasureConversionPKField(UnitMeasureConversionPK model){

		
		controller = new UnitMeasureConversionPKController();
		
		view = new UnitMeasureConversionPKView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);

		setLayout(new BorderLayout());
		
		add(view, BorderLayout.CENTER);
		

	}

	
	public UnitMeasureConversionPKView getView() {

		return view;

	}
	

	public UnitMeasureConversionPKController getController() {

		return controller;

	}

	
	public void requestFocus(){

		
		if (getView().getFromUnitMeasureField().isEnabled())
			
			getView().getFromUnitMeasureField().requestFocus();
		
		else
			
			if (getView().getToUnitMeasureField().isEnabled())
				
				getView().getToUnitMeasureField().requestFocus();

		
	}

	
}