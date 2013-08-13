package developen.client.engineer.search;



import developen.client.engineer.mvc.IcmsSearchController;
import developen.client.engineer.mvc.IcmsSearchModel;
import developen.client.engineer.mvc.IcmsSearchView;
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