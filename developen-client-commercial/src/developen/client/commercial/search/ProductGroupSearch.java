package developen.client.commercial.search;



import developen.client.commercial.mvc.ProductGroupSearchController;
import developen.client.commercial.mvc.ProductGroupSearchModel;
import developen.client.commercial.mvc.ProductGroupSearchView;
import developen.client.framework.search.Search;

public class ProductGroupSearch extends Search {

	
	public ProductGroupSearch(){
		
		
		super();
		
		model = new ProductGroupSearchModel();
		
		controller = new ProductGroupSearchController();
		
		view = new ProductGroupSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public ProductGroupSearchController getController(){
		
		return (ProductGroupSearchController) controller;
		
	}
	
	
}