package developen.client.finance.search;


import developen.client.finance.mvc.ReceiptConditionSearchController;
import developen.client.finance.mvc.ReceiptConditionSearchModel;
import developen.client.finance.mvc.ReceiptConditionSearchView;
import developen.client.framework.search.Search;

public class ReceiptConditionSearch extends Search {

	
	public ReceiptConditionSearch(Boolean active){
		
		super();
		
		model = new ReceiptConditionSearchModel();
		
		controller = new ReceiptConditionSearchController();
		
		view = new ReceiptConditionSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((ReceiptConditionSearchController)controller).changeActiveProperty(active);
		
	}
	
	
	public ReceiptConditionSearchController getController(){
		
		return (ReceiptConditionSearchController) controller;
		
	}
	
	
}