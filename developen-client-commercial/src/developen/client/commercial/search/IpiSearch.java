package developen.client.commercial.search;



import developen.client.commercial.mvc.IpiSearchController;
import developen.client.commercial.mvc.IpiSearchModel;
import developen.client.commercial.mvc.IpiSearchView;
import developen.client.framework.search.Search;

public class IpiSearch extends Search {

	
	public IpiSearch(){
		
		
		super();
		
		model = new IpiSearchModel();
		
		controller = new IpiSearchController();
		
		view = new IpiSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public IpiSearchController getController(){
		
		return (IpiSearchController) controller;
		
	}
	
	
}