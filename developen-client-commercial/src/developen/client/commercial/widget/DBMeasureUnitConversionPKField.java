package developen.client.commercial.widget;

import java.awt.BorderLayout;

import javax.swing.JComponent;

import developen.client.commercial.mvc.MeasureUnitConversionPKController;
import developen.client.commercial.mvc.MeasureUnitConversionPKView;
import developen.common.commercial.mvc.MeasureUnitConversionPK;


public class DBMeasureUnitConversionPKField extends JComponent{


	private static final long serialVersionUID = -6009120201640056143L;
	
	private MeasureUnitConversionPKView view;
	
	private MeasureUnitConversionPKController controller;
	

	public DBMeasureUnitConversionPKField(MeasureUnitConversionPK model){

		
		controller = new MeasureUnitConversionPKController();
		
		view = new MeasureUnitConversionPKView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);

		setLayout(new BorderLayout());
		
		add(view, BorderLayout.CENTER);
		

	}

	
	public MeasureUnitConversionPKView getView() {

		return view;

	}
	

	public MeasureUnitConversionPKController getController() {

		return controller;

	}

	
	public void requestFocus(){

		
		if (getView().getFromField().isEnabled())
			
			getView().getFromField().requestFocus();
		
		else
			
			if (getView().getToField().isEnabled())
				
				getView().getToField().requestFocus();

		
	}

	
}