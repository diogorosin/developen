package developen.client.commercial.search;



import developen.client.commercial.mvc.CompanySearchController;
import developen.client.commercial.mvc.CompanySearchModel;
import developen.client.commercial.mvc.CompanySearchView;
import developen.client.framework.search.Search;

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