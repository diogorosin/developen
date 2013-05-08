package developen.client.commercial.search;



import developen.client.commercial.mvc.OrderSearchController;
import developen.client.commercial.mvc.OrderSearchModel;
import developen.client.commercial.mvc.OrderSearchView;
import developen.client.framework.search.Search;

public class OrderSearch extends Search {

	
	public OrderSearch(){
		
		
		super();
		
		model = new OrderSearchModel();
		
		controller = new OrderSearchController();
		
		view = new OrderSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public OrderSearchController getController(){
		
		return (OrderSearchController) controller;
		
	}
	
	
}