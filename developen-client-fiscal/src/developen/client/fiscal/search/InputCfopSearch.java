package developen.client.fiscal.search;


import developen.client.fiscal.mvc.InputCfopSearchController;
import developen.client.fiscal.mvc.InputCfopSearchModel;
import developen.client.fiscal.mvc.InputCfopSearchView;
import developen.client.framework.search.Search;

public class InputCfopSearch extends Search {

	
	public InputCfopSearch(){
		
		
		super();
		
		model = new InputCfopSearchModel();
		
		controller = new InputCfopSearchController();
		
		view = new InputCfopSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public InputCfopSearchController getController(){
		
		return (InputCfopSearchController) controller;
		
	}
	
	
}