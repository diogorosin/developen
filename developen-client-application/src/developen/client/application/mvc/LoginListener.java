package developen.client.application.mvc;


public abstract interface LoginListener {

	
	public void onSuccess(LoginEvent event) throws Exception;
	
	
	public void onFailure(LoginEvent event) throws Exception;
	
	
}