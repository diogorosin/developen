package developen.client.subject.search;



import developen.client.framework.search.Search;
import developen.client.subject.mvc.CompanySearchController;
import developen.client.subject.mvc.CompanySearchModel;
import developen.client.subject.mvc.CompanySearchView;

public class CompanySearch extends Search {

	
	public CompanySearch(Boolean active){
		
		
		model = new CompanySearchModel();
		
		controller = new CompanySearchController();
		
		view = new CompanySearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((CompanySearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public CompanySearchController getController(){
		
		return (CompanySearchController) controller;
		
	}
	
	
}