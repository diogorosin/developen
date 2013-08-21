package developen.client.commercial.search;


import developen.client.commercial.mvc.CitySearchController;
import developen.client.commercial.mvc.CitySearchModel;
import developen.client.commercial.mvc.CitySearchView;
import developen.client.framework.search.Search;

public class CitySearch extends Search {

	
	public CitySearch(){
	
		
		model = new CitySearchModel();
		
		controller = new CitySearchController();
		
		view = new CitySearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public CitySearchController getController(){
		
		return (CitySearchController) controller;
		
	}
	

}