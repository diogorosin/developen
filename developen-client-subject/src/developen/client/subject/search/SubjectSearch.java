package developen.client.subject.search;



import developen.client.framework.search.Search;
import developen.client.subject.mvc.SubjectSearchController;
import developen.client.subject.mvc.SubjectSearchModel;
import developen.client.subject.mvc.SubjectSearchView;

public class SubjectSearch extends Search {

	
	public SubjectSearch(Boolean active){
		
	
		model = new SubjectSearchModel();
		
		controller = new SubjectSearchController();
		
		view = new SubjectSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((SubjectSearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public SubjectSearchController getController(){
		
		return (SubjectSearchController) controller;
		
	}
	
	
}