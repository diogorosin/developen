package developen.client.commercial.search;



import developen.client.commercial.mvc.ProductLineSearchController;
import developen.client.commercial.mvc.ProductLineSearchModel;
import developen.client.commercial.mvc.ProductLineSearchView;
import developen.client.framework.search.Search;

public class ProductLineSearch extends Search {

	
	public ProductLineSearch(){
		
		
		super();
		
		model = new ProductLineSearchModel();
		
		controller = new ProductLineSearchController();
		
		view = new ProductLineSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public ProductLineSearchController getController(){
		
		return (ProductLineSearchController) controller;
		
	}
	
	
}