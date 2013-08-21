package developen.client.commercial.search;

import developen.client.commercial.mvc.SystemPersonSearchController;
import developen.client.commercial.mvc.SystemPersonSearchModel;
import developen.client.commercial.mvc.SystemPersonSearchView;
import developen.client.framework.search.Search;

public class SystemPersonSearch extends Search {

	
	public SystemPersonSearch(Boolean active){
		
		
		super();
		
		model = new SystemPersonSearchModel();
		
		controller = new SystemPersonSearchController();
		
		view = new SystemPersonSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		((SystemPersonSearchController)controller).changeActiveProperty(active);
		
		
	}
	
	
	public SystemPersonSearchController getController(){
		
		return (SystemPersonSearchController) controller;
		
	}
	
	
	public void setOnlyActive(Boolean onlyActive){
		
		getController().changeActiveProperty(onlyActive);
		
	}
	
	
	public Boolean isOnlyActive(){
		
		return getController().getModel().getActive();
		
	}
	

}