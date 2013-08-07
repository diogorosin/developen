package developen.client.commercial.search;



import developen.client.commercial.mvc.MacroSearchController;
import developen.client.commercial.mvc.MacroSearchModel;
import developen.client.commercial.mvc.MacroSearchView;
import developen.client.framework.search.Search;

public class MacroSearch extends Search {

	
	public MacroSearch(Boolean active){
		
		
		model = new MacroSearchModel();
		
		controller = new MacroSearchController();
		
		view = new MacroSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((MacroSearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public MacroSearchController getController(){
		
		return (MacroSearchController) controller;
		
	}
	
	
}