package developen.client.engineer.search;



import developen.client.engineer.mvc.CstSearchController;
import developen.client.engineer.mvc.CstSearchModel;
import developen.client.engineer.mvc.CstSearchView;
import developen.client.framework.search.Search;

public class CstSearch extends Search {

	
	public CstSearch(){
		
		
		super();
		
		model = new CstSearchModel();
		
		controller = new CstSearchController();
		
		view = new CstSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public CstSearchController getController(){
		
		return (CstSearchController) controller;
		
	}
	
	
}