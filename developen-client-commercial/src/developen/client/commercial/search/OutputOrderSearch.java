package developen.client.commercial.search;



import developen.client.commercial.mvc.OutputOrderSearchController;
import developen.client.commercial.mvc.OutputOrderSearchModel;
import developen.client.commercial.mvc.OutputOrderSearchView;
import developen.client.framework.search.Search;

public class OutputOrderSearch extends Search {

	
	public OutputOrderSearch(){
		
		
		super();
		
		model = new OutputOrderSearchModel();
		
		controller = new OutputOrderSearchController();
		
		view = new OutputOrderSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public OutputOrderSearchController getController(){
		
		return (OutputOrderSearchController) controller;
		
	}
	
	
}