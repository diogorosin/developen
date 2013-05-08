package developen.client.subject.search;


import developen.client.framework.search.Search;
import developen.client.subject.mvc.CitySearchController;
import developen.client.subject.mvc.CitySearchModel;
import developen.client.subject.mvc.CitySearchView;

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