package developen.client.engineer.search;



import developen.client.engineer.mvc.CsosnSearchController;
import developen.client.engineer.mvc.CsosnSearchModel;
import developen.client.engineer.mvc.CsosnSearchView;
import developen.client.framework.search.Search;

public class CsosnSearch extends Search {

	
	public CsosnSearch(){
		
		
		super();
		
		model = new CsosnSearchModel();
		
		controller = new CsosnSearchController();
		
		view = new CsosnSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public CsosnSearchController getController(){
		
		return (CsosnSearchController) controller;
		
	}
	
	
}