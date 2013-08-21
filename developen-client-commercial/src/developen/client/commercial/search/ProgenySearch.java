package developen.client.commercial.search;



import developen.client.commercial.mvc.ProgenySearchController;
import developen.client.commercial.mvc.ProgenySearchModel;
import developen.client.commercial.mvc.ProgenySearchView;
import developen.client.framework.search.Search;

public class ProgenySearch extends Search {

	
	public ProgenySearch(Boolean active){
		
		super();
		
		model = new ProgenySearchModel();
		
		controller = new ProgenySearchController();
		
		view = new ProgenySearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((ProgenySearchController)controller).changeActiveProperty(active);
		
	}
	
	
	public ProgenySearchController getController(){
		
		return (ProgenySearchController) controller;
		
	}
	
	
}