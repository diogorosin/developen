package developen.client.commercial.search;



import developen.client.commercial.mvc.ProductFinishSearchController;
import developen.client.commercial.mvc.ProductFinishSearchModel;
import developen.client.commercial.mvc.ProductFinishSearchView;
import developen.client.framework.search.Search;

public class ProductFinishSearch extends Search {

	
	public ProductFinishSearch(){
		
		
		super();
		
		model = new ProductFinishSearchModel();
		
		controller = new ProductFinishSearchController();
		
		view = new ProductFinishSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public ProductFinishSearchController getController(){
		
		return (ProductFinishSearchController) controller;
		
	}
	
	
}