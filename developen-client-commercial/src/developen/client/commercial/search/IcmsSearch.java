package developen.client.commercial.search;



import developen.client.commercial.mvc.IcmsSearchController;
import developen.client.commercial.mvc.IcmsSearchModel;
import developen.client.commercial.mvc.IcmsSearchView;
import developen.client.framework.search.Search;

public class IcmsSearch extends Search {

	
	public IcmsSearch(){
		
		
		super();
		
		model = new IcmsSearchModel();
		
		controller = new IcmsSearchController();
		
		view = new IcmsSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public IcmsSearchController getController(){
		
		return (IcmsSearchController) controller;
		
	}
	
	
}