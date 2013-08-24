package developen.client.commercial.search;



import developen.client.commercial.mvc.ProductMarkSearchController;
import developen.client.commercial.mvc.ProductMarkSearchModel;
import developen.client.commercial.mvc.ProductMarkSearchView;
import developen.client.framework.search.Search;

public class ProductMarkSearch extends Search {

	
	public ProductMarkSearch(){
		
		
		super();
		
		model = new ProductMarkSearchModel();
		
		controller = new ProductMarkSearchController();
		
		view = new ProductMarkSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public ProductMarkSearchController getController(){
		
		return (ProductMarkSearchController) controller;
		
	}
	
	
}