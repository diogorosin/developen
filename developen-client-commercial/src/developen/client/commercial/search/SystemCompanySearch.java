package developen.client.commercial.search;

import developen.client.commercial.mvc.SystemCompanySearchController;
import developen.client.commercial.mvc.SystemCompanySearchModel;
import developen.client.commercial.mvc.SystemCompanySearchView;
import developen.client.framework.search.Search;

public class SystemCompanySearch extends Search {

	
	public SystemCompanySearch(Boolean active){
		
		
		super();
		
		model = new SystemCompanySearchModel();
		
		controller = new SystemCompanySearchController();
		
		view = new SystemCompanySearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((SystemCompanySearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public SystemCompanySearchController getController(){
		
		return (SystemCompanySearchController) controller;
		
	}
	
	
}