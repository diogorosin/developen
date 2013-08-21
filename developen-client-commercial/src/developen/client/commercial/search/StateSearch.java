package developen.client.commercial.search;


import developen.client.commercial.mvc.StateSearchController;
import developen.client.commercial.mvc.StateSearchModel;
import developen.client.commercial.mvc.StateSearchView;
import developen.client.framework.search.Search;

public class StateSearch extends Search {

	
	public StateSearch(){
	
		
		model = new StateSearchModel();
		
		controller = new StateSearchController();
		
		view = new StateSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public StateSearchController getController(){
		
		return (StateSearchController) controller;
		
	}
	

}