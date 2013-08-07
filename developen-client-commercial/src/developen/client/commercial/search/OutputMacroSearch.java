package developen.client.commercial.search;



import developen.client.commercial.mvc.OutputMacroSearchController;
import developen.client.commercial.mvc.OutputMacroSearchModel;
import developen.client.commercial.mvc.OutputMacroSearchView;
import developen.client.framework.search.Search;

public class OutputMacroSearch extends Search {

	
	public OutputMacroSearch(Boolean active){
		
		
		model = new OutputMacroSearchModel();
		
		controller = new OutputMacroSearchController();
		
		view = new OutputMacroSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((OutputMacroSearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public OutputMacroSearchController getController(){
		
		return (OutputMacroSearchController) controller;
		
	}
	
	
}