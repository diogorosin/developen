package developen.client.subject.search;


import developen.client.framework.search.Search;
import developen.client.subject.mvc.CnaeSearchController;
import developen.client.subject.mvc.CnaeSearchModel;
import developen.client.subject.mvc.CnaeSearchView;

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