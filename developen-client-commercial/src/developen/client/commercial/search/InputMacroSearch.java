package developen.client.commercial.search;



import developen.client.commercial.mvc.InputMacroSearchController;
import developen.client.commercial.mvc.InputMacroSearchModel;
import developen.client.commercial.mvc.InputMacroSearchView;
import developen.client.framework.search.Search;

public class InputMacroSearch extends Search {

	
	public InputMacroSearch(Boolean active){
		
		
		model = new InputMacroSearchModel();
		
		controller = new InputMacroSearchController();
		
		view = new InputMacroSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((InputMacroSearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public InputMacroSearchController getController(){
		
		return (InputMacroSearchController) controller;
		
	}
	
	
}