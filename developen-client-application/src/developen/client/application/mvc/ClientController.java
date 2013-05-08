package developen.client.application.mvc;

import java.util.ArrayList;

import developen.client.framework.mvc.SystemPersonEvent;
import developen.client.framework.mvc.SystemPersonListener;
import developen.common.framework.mvc.Controller;
import developen.common.subject.mvc.PersonCompany;
import developen.common.subject.mvc.SystemCompany;
import developen.common.subject.mvc.SystemPerson;

public class ClientController extends Controller {

	
	public static final String MODEL_STATE_PROPERTY = "ModelState";
	
	public static final String SYSTEM_PERSON_PROPERTY = "SystemPerson";
	
	public static final String SYSTEM_COMPANY_PROPERTY = "SystemCompany";

	private ArrayList<SystemPersonListener> registeredSystemPersonListeners;


	private ArrayList<SystemPersonListener> getRegisteredSystemPersonListeners(){


		if (registeredSystemPersonListeners == null)

			registeredSystemPersonListeners = new ArrayList<SystemPersonListener>();

		return registeredSystemPersonListeners;


	}


	public void addSystemPersonListener(SystemPersonListener listener) {

		getRegisteredSystemPersonListeners().add(listener);

	}


	public void removeSystemPersonListener(SystemPersonListener listener) {

		getRegisteredSystemPersonListeners().remove(listener);

	}


	public void changeSystemPersonProperty(SystemPerson newValue) {

		setModelProperty(ClientController.SYSTEM_PERSON_PROPERTY, newValue);

	}

	
	public void changeSystemCompanyProperty(SystemCompany newValue) {

		setModelProperty(ClientController.SYSTEM_COMPANY_PROPERTY, newValue);

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

	
	public void exit() throws Exception{

		
		onBeforeExit();
		
		onExit();
		
		onAfterExit();
		

	}
	

	public void help() throws Exception{

		
		onBeforeHelp();
		
		onHelp();
		
		onAfterHelp();
		

	}


	private void onBeforeExit() throws Exception{}

	
	private void onExit() throws Exception{}

	
	private void onAfterExit() throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.EXITED);

	}


	private void onBeforeLogout() throws Exception{
		
		
		SystemPersonEvent event = new SystemPersonEvent(getModel().getSystemPerson());

		for (SystemPersonListener l : getRegisteredSystemPersonListeners())

			l.onLogout(event);


	}

	
	private void onLogout() throws Exception{

		changeSystemPersonProperty(null);

	}

	
	private void onAfterLogout() throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.LOGOUT);

	}


	private void onBeforeLogin(SystemPerson systemPerson) throws Exception{}

	
	private void onLogin(SystemPerson systemPerson) throws Exception{

	
		changeSystemPersonProperty(systemPerson);

		SystemCompany loggedCompany = systemPerson.getLastLoggedSystemCompany();
		
		if (loggedCompany==null)
			
			loggedCompany = ((PersonCompany)systemPerson.getCompanies().get(0)).getIdentifier().getSystemCompany();
		
		changeSystemCompanyProperty(loggedCompany);
		

	}

	
	private void onAfterLogin(SystemPerson systemPerson) throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.LOGIN);

	}


	private void onAfterHelp() throws Exception{}

	
	private void onHelp() throws Exception{


	}

	
	private void onBeforeHelp() throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.HELPING);

	}

	
}