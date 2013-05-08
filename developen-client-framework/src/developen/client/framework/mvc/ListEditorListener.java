package developen.client.framework.mvc;


public abstract interface ListEditorListener {

	
	public void onIncluded(ListEditorEvent event) throws Exception;
	
	
	public void onUpdated(ListEditorEvent event) throws Exception;


	public void onDeleted(ListEditorEvent event) throws Exception;

	
	public void onCanceled(ListEditorEvent event) throws Exception;
	
	
}