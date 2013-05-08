package developen.client.framework.search;


public abstract interface SearchListener<E> {

	
	public void onSearchConfirmed(SearchEvent<E> event) throws Exception;
	
	
	public void onSearchCanceled(SearchEvent<E> event) throws Exception;
	
	
}