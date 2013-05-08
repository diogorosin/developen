package developen.client.framework.mvc;


public abstract interface PropertyEditorListener {

	
	public void onEditionConfirmed(PropertyEditorEvent event) throws Exception;
	
	
	public void onEditionCanceled(PropertyEditorEvent event) throws Exception;
	
	
}