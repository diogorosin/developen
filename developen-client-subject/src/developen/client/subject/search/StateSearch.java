package developen.client.subject.search;


import developen.client.framework.search.Search;
import developen.client.subject.mvc.StateSearchController;
import developen.client.subject.mvc.StateSearchModel;
import developen.client.subject.mvc.StateSearchView;

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