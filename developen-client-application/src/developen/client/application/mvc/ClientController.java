package developen.client.application.mvc;


import javax.swing.JInternalFrame;

import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.commercial.mvc.SystemPersonSystemCompany;
import developen.common.framework.mvc.Controller;

public class ClientController extends Controller {

	
	public static final String MODEL_STATE_PROPERTY = "ModelState";
	
	public static final String SYSTEM_PERSON_PROPERTY = "SystemPerson";
	
	public static final String SYSTEM_COMPANY_PROPERTY = "SystemCompany";
	
	public static final String ACTIVE_FRAME_PROPERTY = "ActiveFrame";


	public void changeSystemPersonProperty(SystemPerson newValue) {

		setModelProperty(ClientController.SYSTEM_PERSON_PROPERTY, newValue);

	}

	
	public void changeSystemCompanyProperty(SystemCompany newValue) {

		setModelProperty(ClientController.SYSTEM_COMPANY_PROPERTY, newValue);

	}
	

	public void changeActiveFrameProperty(JInternalFrame newValue) {

		setModelProperty(ClientController.ACTIVE_FRAME_PROPERTY, newValue);

	}

	
	public ClientModel getModel() {

		return (ClientModel) model;

	}
	

	public void logout() throws Exception{

		
		onBeforeLogout();
		
		onLogout();
		
		onAfterLogout();
		

	}
	

	public void login(SystemPerson user) throws Exception{

		
		onBeforeLogin(user);
		
		onLogin(user);
		
		onAfterLogin(user);
		

	}

	
	public void close() throws Exception{

		
		onBeforeClose();
		
		onClose();
		
		onAfterClose();
		

	}
	

	private void onBeforeClose() throws Exception{}

	
	private void onClose() throws Exception{}

	
	private void onAfterClose() throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.CLOSED);

	}


	private void onBeforeLogout() throws Exception{}
	
	private void onLogout() throws Exception{

		changeSystemPersonProperty(null);

	}

	
	private void onAfterLogout() throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.LOGGED_OUT);

	}


	private void onBeforeLogin(SystemPerson systemPerson) throws Exception{}

	
	private void onLogin(SystemPerson systemPerson) throws Exception{

	
		changeSystemPersonProperty(systemPerson);

		SystemCompany loggedCompany = systemPerson.getLastLoggedSystemCompany();
		
		if (loggedCompany==null)
			
			loggedCompany = ((SystemPersonSystemCompany)systemPerson.getSystemCompanies().get(0)).getIdentifier().getSystemCompany();
		
		changeSystemCompanyProperty(loggedCompany);
		

	}

	
	private void onAfterLogin(SystemPerson systemPerson) throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.LOGGED_IN);

	}

	
}