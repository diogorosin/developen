package developen.client.commercial.search;



import developen.client.commercial.mvc.FiscalDocumentTypeSearchController;
import developen.client.commercial.mvc.FiscalDocumentTypeSearchModel;
import developen.client.commercial.mvc.FiscalDocumentTypeSearchView;
import developen.client.framework.search.Search;

public class FiscalDocumentTypeSearch extends Search {

	
	public FiscalDocumentTypeSearch(){
		
		
		super();
		
		model = new FiscalDocumentTypeSearchModel();
		
		controller = new FiscalDocumentTypeSearchController();
		
		view = new FiscalDocumentTypeSearchView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);
		
		
	}
	
	
	public FiscalDocumentTypeSearchController getController(){
		
		return (FiscalDocumentTypeSearchController) controller;
		
	}
	
	
}