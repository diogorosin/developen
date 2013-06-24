package developen.client.finance.search;


import developen.client.finance.mvc.PaymentConditionSearchController;
import developen.client.finance.mvc.PaymentConditionSearchModel;
import developen.client.finance.mvc.PaymentConditionSearchView;
import developen.client.framework.search.Search;

public class PaymentConditionSearch extends Search {

	
	public PaymentConditionSearch(Boolean active){
		
		super();
		
		model = new PaymentConditionSearchModel();
		
		controller = new PaymentConditionSearchController();
		
		view = new PaymentConditionSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((PaymentConditionSearchController)controller).changeActiveProperty(active);
		
	}
	
	
	public PaymentConditionSearchController getController(){
		
		return (PaymentConditionSearchController) controller;
		
	}
	
	
}