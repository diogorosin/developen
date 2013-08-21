package developen.client.commercial.search;



import developen.client.commercial.mvc.ProductSearchController;
import developen.client.commercial.mvc.ProductSearchModel;
import developen.client.commercial.mvc.ProductSearchView;
import developen.client.framework.search.Search;

public class ProductSearch extends Search {

	
	public ProductSearch(Boolean active){
		
		
		super();
		
		model = new ProductSearchModel();
		
		controller = new ProductSearchController();
		
		view = new ProductSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((ProductSearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public ProductSearchController getController(){
		
		return (ProductSearchController) controller;
		
	}
	
	
}