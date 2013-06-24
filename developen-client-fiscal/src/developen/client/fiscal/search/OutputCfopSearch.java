package developen.client.fiscal.search;


import developen.client.fiscal.mvc.OutputCfopSearchController;
import developen.client.fiscal.mvc.OutputCfopSearchModel;
import developen.client.fiscal.mvc.OutputCfopSearchView;
import developen.client.framework.search.Search;

public class OutputCfopSearch extends Search {

	
	public OutputCfopSearch(){
		
	
		super();
		
		model = new OutputCfopSearchModel();
		
		controller = new OutputCfopSearchController();
		
		view = new OutputCfopSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		

	}
	
	
	public OutputCfopSearchController getController(){
		
		return (OutputCfopSearchController) controller;
		
	}
	
	
}