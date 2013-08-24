package developen.client.osm.widget;

import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.View;

public class MVCBean {

	
	private View view;
	
	private Controller controller;
	
	private Model model;

	
	public View getView() {
		
		return view;
		
	}

	
	public void setView(View view) {
		
		this.view = view;
		
	}
	

	public Controller getController() {
		
		return controller;
		
	}
	

	public void setController(Controller controller) {
		
		this.controller = controller;
		
	}

	
	public Model getModel() {
		
		return model;
		
	}

	
	public void setModel(Model model) {
		
		this.model = model;
		
	}

	
}