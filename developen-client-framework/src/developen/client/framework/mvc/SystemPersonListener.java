package developen.client.framework.mvc;

public interface SystemPersonListener {

	
	public void onLogout(SystemPersonEvent event) throws Exception;
	
	public void onLogin(SystemPersonEvent event) throws Exception;
	

}