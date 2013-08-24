package developen.client.commercial.search;


import developen.client.commercial.mvc.CofinsCstSearchController;
import developen.client.commercial.mvc.CofinsCstSearchModel;
import developen.client.commercial.mvc.CofinsCstSearchView;
import developen.client.framework.search.Search;

public class CofinsCstSearch extends Search {

	
	public CofinsCstSearch(){
		
		
		super();
		
		model = new CofinsCstSearchModel();
		
		controller = new CofinsCstSearchController();
		
		view = new CofinsCstSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public CofinsCstSearchController getController(){
		
		return (CofinsCstSearchController) controller;
		
	}
	
	
}