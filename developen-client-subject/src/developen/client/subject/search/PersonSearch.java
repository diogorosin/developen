package developen.client.subject.search;



import developen.client.framework.search.Search;
import developen.client.subject.mvc.PersonSearchController;
import developen.client.subject.mvc.PersonSearchModel;
import developen.client.subject.mvc.PersonSearchView;

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