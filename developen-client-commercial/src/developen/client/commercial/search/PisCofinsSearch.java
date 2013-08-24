package developen.client.commercial.search;



import developen.client.commercial.mvc.PisCofinsSearchController;
import developen.client.commercial.mvc.PisCofinsSearchModel;
import developen.client.commercial.mvc.PisCofinsSearchView;
import developen.client.framework.search.Search;

public class PisCofinsSearch extends Search {

	
	public PisCofinsSearch(){
		
		
		super();
		
		model = new PisCofinsSearchModel();
		
		controller = new PisCofinsSearchController();
		
		view = new PisCofinsSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public PisCofinsSearchController getController(){
		
		return (PisCofinsSearchController) controller;
		
	}
	
	
}