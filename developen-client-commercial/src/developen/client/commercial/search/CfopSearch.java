package developen.client.commercial.search;


import developen.client.commercial.mvc.CfopSearchController;
import developen.client.commercial.mvc.CfopSearchModel;
import developen.client.commercial.mvc.CfopSearchView;
import developen.client.framework.search.Search;

public class CfopSearch extends Search {

	
	public CfopSearch(){

		
		super();
		
		model = new CfopSearchModel();
		
		controller = new CfopSearchController();
		
		view = new CfopSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public CfopSearchController getController(){
		
		return (CfopSearchController) controller;
		
	}
	
	
}