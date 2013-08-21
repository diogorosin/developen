package developen.client.commercial.search;



import developen.client.commercial.mvc.IcmsCstSearchController;
import developen.client.commercial.mvc.IcmsCstSearchModel;
import developen.client.commercial.mvc.IcmsCstSearchView;
import developen.client.framework.search.Search;

public class IcmsCstSearch extends Search {

	
	public IcmsCstSearch(){
		
		
		super();
		
		model = new IcmsCstSearchModel();
		
		controller = new IcmsCstSearchController();
		
		view = new IcmsCstSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public IcmsCstSearchController getController(){
		
		return (IcmsCstSearchController) controller;
		
	}
	
	
}