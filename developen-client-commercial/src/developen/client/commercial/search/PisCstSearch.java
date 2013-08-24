package developen.client.commercial.search;



import developen.client.commercial.mvc.PisCstSearchController;
import developen.client.commercial.mvc.PisCstSearchModel;
import developen.client.commercial.mvc.PisCstSearchView;
import developen.client.framework.search.Search;

public class PisCstSearch extends Search {

	
	public PisCstSearch(){
		
		
		super();
		
		model = new PisCstSearchModel();
		
		controller = new PisCstSearchController();
		
		view = new PisCstSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public PisCstSearchController getController(){
		
		return (PisCstSearchController) controller;
		
	}
	
	
}