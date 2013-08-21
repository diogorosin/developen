package developen.client.commercial.search;



import developen.client.commercial.mvc.IpiCstSearchController;
import developen.client.commercial.mvc.IpiCstSearchModel;
import developen.client.commercial.mvc.IpiCstSearchView;
import developen.client.framework.search.Search;

public class IpiCstSearch extends Search {

	
	public IpiCstSearch(){
		
		
		super();
		
		model = new IpiCstSearchModel();
		
		controller = new IpiCstSearchController();
		
		view = new IpiCstSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public IpiCstSearchController getController(){
		
		return (IpiCstSearchController) controller;
		
	}
	
	
}