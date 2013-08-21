package developen.client.commercial.search;



import developen.client.commercial.mvc.IcmsCsosnSearchController;
import developen.client.commercial.mvc.IcmsCsosnSearchModel;
import developen.client.commercial.mvc.IcmsCsosnSearchView;
import developen.client.framework.search.Search;

public class IcmsCsosnSearch extends Search {

	
	public IcmsCsosnSearch(){
		
		
		super();
		
		model = new IcmsCsosnSearchModel();
		
		controller = new IcmsCsosnSearchController();
		
		view = new IcmsCsosnSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public IcmsCsosnSearchController getController(){
		
		return (IcmsCsosnSearchController) controller;
		
	}
	
	
}