package developen.client.application.mvc;


import java.util.Locale;

import javax.swing.JInternalFrame;

import developen.common.commercial.mvc.Idiom;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.commercial.mvc.SystemPersonPreference;
import developen.common.framework.mvc.Controller;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;

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


	public void login(SystemPerson systemPerson, SystemCompany systemCompany) throws Exception{


		onBeforeLogin(systemPerson, systemCompany);

		onLogin(systemPerson, systemCompany);

		onAfterLogin(systemPerson, systemCompany);


	}


	public void close() throws Exception{


		onBeforeClose();

		onClose();

		onAfterClose();


	}


	private void onBeforeClose() throws Exception{
		
		savePreferences();

	}


	private void onClose() throws Exception{}


	private void onAfterClose() throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.CLOSED);

	}


	private void onBeforeLogout() throws Exception{

		savePreferences();

	}


	private void onLogout() throws Exception{


		changeSystemPersonProperty(null);

		changeSystemCompanyProperty(null);


	}


	private void onAfterLogout() throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.LOGGED_OUT);

	}


	private void onBeforeLogin(SystemPerson systemPerson, SystemCompany systemCompany) throws Exception{}


	private void onLogin(SystemPerson systemPerson, SystemCompany systemCompany) throws Exception{


		changeSystemPersonProperty(systemPerson);

		changeSystemCompanyProperty(systemCompany);


	}


	private void onAfterLogin(SystemPerson systemPerson, SystemCompany systemCompany) throws Exception{

		setModelProperty(ClientController.MODEL_STATE_PROPERTY, ClientState.LOGGED_IN);

	}


	private void savePreferences() throws Exception{
		
		
		SystemPerson person = getModel().getSystemPerson();
		
		if (person!=null){
			
			SystemPersonPreference preference = person.getPreference();

			preference.setLastLoggedSystemCompany(getModel().getSystemCompany());

			preference.setIdiom(
					
					new Idiom(Locale.getDefault().getLanguage() 
					
					+ "_" 
					
					+ Locale.getDefault().getCountry()));

			Session session = DPA.getSessionFactory().createSession();

			session.update(preference);

			session.close();

		}
		
		
	}
	
	
}