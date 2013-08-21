package developen.client.commercial.search;



import developen.client.commercial.mvc.RuleSearchController;
import developen.client.commercial.mvc.RuleSearchModel;
import developen.client.commercial.mvc.RuleSearchView;
import developen.client.framework.search.Search;

public class RuleSearch extends Search {

	
	public RuleSearch(){
		
		
		super();
		
		model = new RuleSearchModel();
		
		controller = new RuleSearchController();
		
		view = new RuleSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public RuleSearchController getController(){
		
		return (RuleSearchController) controller;
		
	}
	
	
}