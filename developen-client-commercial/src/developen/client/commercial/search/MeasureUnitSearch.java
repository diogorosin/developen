package developen.client.commercial.search;



import developen.client.commercial.mvc.MeasureUnitSearchController;
import developen.client.commercial.mvc.MeasureUnitSearchModel;
import developen.client.commercial.mvc.MeasureUnitSearchView;
import developen.client.framework.search.Search;

public class MeasureUnitSearch extends Search {

	
	public MeasureUnitSearch(){
		
		
		super();
		
		model = new MeasureUnitSearchModel();
		
		controller = new MeasureUnitSearchController();
		
		view = new MeasureUnitSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public MeasureUnitSearchController getController(){
		
		return (MeasureUnitSearchController) controller;
		
	}
	
	
}