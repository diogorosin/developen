package developen.client.commercial.search;


import developen.client.commercial.mvc.CnaeSearchController;
import developen.client.commercial.mvc.CnaeSearchModel;
import developen.client.commercial.mvc.CnaeSearchView;
import developen.client.framework.search.Search;

public class CnaeSearch extends Search {

	
	public CnaeSearch(){
	
		
		model = new CnaeSearchModel();
		
		controller = new CnaeSearchController();
		
		view = new CnaeSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public CnaeSearchController getController(){
		
		return (CnaeSearchController) controller;
		
	}
	

}