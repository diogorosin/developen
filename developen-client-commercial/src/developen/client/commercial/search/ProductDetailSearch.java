package developen.client.commercial.search;



import developen.client.commercial.mvc.ProductDetailSearchController;
import developen.client.commercial.mvc.ProductDetailSearchModel;
import developen.client.commercial.mvc.ProductDetailSearchView;
import developen.client.framework.search.Search;

public class ProductDetailSearch extends Search {

	
	public ProductDetailSearch(){
		
		
		super();
		
		model = new ProductDetailSearchModel();
		
		controller = new ProductDetailSearchController();
		
		view = new ProductDetailSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public ProductDetailSearchController getController(){
		
		return (ProductDetailSearchController) controller;
		
	}
	
	
}