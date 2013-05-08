package developen.client.engineer.search;



import developen.client.engineer.mvc.ProgenySearchController;
import developen.client.engineer.mvc.ProgenySearchModel;
import developen.client.engineer.mvc.ProgenySearchView;
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