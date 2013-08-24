package developen.client.commercial.search;



import developen.client.commercial.mvc.ProductModelSearchController;
import developen.client.commercial.mvc.ProductModelSearchModel;
import developen.client.commercial.mvc.ProductModelSearchView;
import developen.client.framework.search.Search;

public class ProductModelSearch extends Search {

	
	public ProductModelSearch(){
		
		
		super();
		
		model = new ProductModelSearchModel();
		
		controller = new ProductModelSearchController();
		
		view = new ProductModelSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public ProductModelSearchController getController(){
		
		return (ProductModelSearchController) controller;
		
	}
	
	
}