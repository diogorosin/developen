package developen.client.framework.search;


public abstract interface SearchListener {

	
	public void onSearchConfirmed(SearchEvent event) throws Exception;
	
	
	public void onSearchCanceled(SearchEvent event) throws Exception;
	
	
}