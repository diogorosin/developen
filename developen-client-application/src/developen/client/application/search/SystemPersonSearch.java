package developen.client.application.search;

import developen.client.application.mvc.SystemPersonSearchController;
import developen.client.application.mvc.SystemPersonSearchModel;
import developen.client.application.mvc.SystemPersonSearchView;
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