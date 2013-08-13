package developen.client.subject.search;



import developen.client.framework.search.Search;
import developen.client.subject.mvc.RuleSearchController;
import developen.client.subject.mvc.RuleSearchModel;
import developen.client.subject.mvc.RuleSearchView;

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