package developen.client.finance.search;


import developen.client.finance.mvc.ConditionSearchController;
import developen.client.finance.mvc.ConditionSearchModel;
import developen.client.finance.mvc.ConditionSearchView;
import developen.client.framework.search.Search;

public class ConditionSearch extends Search {

	
	public ConditionSearch(Boolean active){
		
		super();
		
		model = new ConditionSearchModel();
		
		controller = new ConditionSearchController();
		
		view = new ConditionSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((ConditionSearchController)controller).changeActiveProperty(active);
		
	}
	
	
	public ConditionSearchController getController(){
		
		return (ConditionSearchController) controller;
		
	}
	
	
}