package developen.client.engineer.search;



import developen.client.engineer.mvc.UnitMeasureSearchController;
import developen.client.engineer.mvc.UnitMeasureSearchModel;
import developen.client.engineer.mvc.UnitMeasureSearchView;
import developen.client.framework.search.Search;

public class UnitMeasureSearch extends Search {

	
	public UnitMeasureSearch(){
		
		
		super();
		
		model = new UnitMeasureSearchModel();
		
		controller = new UnitMeasureSearchController();
		
		view = new UnitMeasureSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public UnitMeasureSearchController getController(){
		
		return (UnitMeasureSearchController) controller;
		
	}
	
	
}