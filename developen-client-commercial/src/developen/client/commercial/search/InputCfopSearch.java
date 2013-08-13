package developen.client.commercial.search;


import developen.client.commercial.mvc.InputCfopSearchController;
import developen.client.commercial.mvc.InputCfopSearchModel;
import developen.client.commercial.mvc.InputCfopSearchView;
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