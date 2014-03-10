package developen.client.commercial.search;



import developen.client.commercial.mvc.IcmsRuleSearchController;
import developen.client.commercial.mvc.IcmsRuleSearchModel;
import developen.client.commercial.mvc.IcmsRuleSearchView;
import developen.client.framework.search.Search;

public class IcmsRuleSearch extends Search {

	
	public IcmsRuleSearch(){
		
		
		super();
		
		model = new IcmsRuleSearchModel();
		
		controller = new IcmsRuleSearchController();
		
		view = new IcmsRuleSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public IcmsRuleSearchController getController(){
		
		return (IcmsRuleSearchController) controller;
		
	}
	
	
}