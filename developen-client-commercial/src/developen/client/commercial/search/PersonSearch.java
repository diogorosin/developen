package developen.client.commercial.search;



import developen.client.commercial.mvc.PersonSearchController;
import developen.client.commercial.mvc.PersonSearchModel;
import developen.client.commercial.mvc.PersonSearchView;
import developen.client.framework.search.Search;

public class PersonSearch extends Search {

	
	public PersonSearch(Boolean active){
		
		
		model = new PersonSearchModel();
		
		controller = new PersonSearchController();
		
		view = new PersonSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((PersonSearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public PersonSearchController getController(){
		
		return (PersonSearchController) controller;
		
	}
	
	
}