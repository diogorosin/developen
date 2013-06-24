package developen.client.commercial.search;



import developen.client.commercial.mvc.OutputOrderSearchController;
import developen.client.commercial.mvc.SaleOrderSearchController;
import developen.client.commercial.mvc.SaleOrderSearchModel;
import developen.client.commercial.mvc.SaleOrderSearchView;
import developen.client.framework.search.Search;

public class SaleOrderSearch extends Search {

	
	public SaleOrderSearch(){
		
		
		super();
		
		model = new SaleOrderSearchModel();
		
		controller = new SaleOrderSearchController();
		
		view = new SaleOrderSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public OutputOrderSearchController getController(){
		
		return (OutputOrderSearchController) controller;
		
	}
	
	
}