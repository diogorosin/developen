package developen.client.application.mvc;

import java.security.MessageDigest;
import java.util.ArrayList;

import sun.misc.BASE64Encoder;
import developen.client.application.exception.InvalidPasswordException;
import developen.client.application.exception.ShortPasswordException;
import developen.common.commercial.i18n.SystemPersonTag;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.Checkable;

public class LoginController extends Controller {


	public static final String SYSTEM_PERSON_PROPERTY = "SystemPerson";

	public static final String PASSWORD_PROPERTY = "Password";

	public static final String SYSTEM_COMPANY_PROPERTY = "SystemCompany";

	public static final String MODEL_STATE = "ModelState";

	private ArrayList<LoginListener> registeredLoginListeners;


	public boolean isEditing(){

		return getModel().getModelState().equals(LoginState.CLEAN); 

	}


	private ArrayList<LoginListener> getRegisteredLoginListeners(){


		if (registeredLoginListeners == null)

			registeredLoginListeners = new ArrayList<LoginListener>();

		return registeredLoginListeners;


	}


	public void addLoginListener(LoginListener listener) {

		getRegisteredLoginListeners().add(listener);

	}


	public void removeLoginListener(LoginListener listener) {

		getRegisteredLoginListeners().remove(listener);

	}


	public LoginModel getModel(){

		return (LoginModel) super.getModel();

	}


	public void changeSystemPersonProperty(SystemPerson newValue) throws Exception {


		if (newValue == null)

			throw new NotNullException(new SystemPersonTag());

		setModelProperty(SYSTEM_PERSON_PROPERTY, newValue);


	}


	public void changePasswordProperty(String newValue) throws ShortPasswordException {


		if (newValue != null && newValue.length() < 6)

			throw new ShortPasswordException();

		setModelProperty(PASSWORD_PROPERTY, newValue);


	}


	public void changeSystemCompanyProperty(SystemCompany newValue) {

		setModelProperty(SYSTEM_COMPANY_PROPERTY, newValue);

	}


	public void login() throws Exception{


		onBeforelogin();

		onLogin();

		onAfterLogin();


	}


	public void cancel() throws Exception{


		onBeforeCancel();

		onCancel();

		onAfterCancel();


	}


	public void clear() throws Exception{


		onBeforeClear();

		onClear();

		onAfterClear();


	}


	protected void onBeforelogin() throws Exception{


		for (View view : getRegisteredViews()) {

			if (view instanceof Checkable){

				Checkable component = (Checkable) view;

				if (!component.isChecked())

					component.check();

			}

		}


	}


	protected void onLogin() throws Exception{


		MessageDigest digest = MessageDigest.getInstance("MD5");

		digest.update(getModel().getPassword().getBytes());

		BASE64Encoder encoder = new BASE64Encoder();

		String criptedPassword = encoder.encode(digest.digest());

		if (!criptedPassword.equals(getModel().getSystemPerson().getPassword()))

			throw new InvalidPasswordException();

		LoginEvent event = new LoginEvent(

				getModel().getSystemPerson(),

				getModel().getSystemCompany());

		for (LoginListener l : getRegisteredLoginListeners())

			l.onSuccess(event);


	}


	protected void onAfterLogin() throws Exception{

		setModelProperty(MODEL_STATE, LoginState.LOGGED_IN);

	}


	protected void onBeforeCancel() throws Exception{}


	protected void onCancel() throws Exception{


		LoginEvent event = new LoginEvent(null,null);

		for (LoginListener l : getRegisteredLoginListeners())

			l.onFailure(event);


	}


	protected void onAfterCancel() throws Exception{

		setModelProperty(MODEL_STATE, LoginState.CANCELED);

	}


	protected void onBeforeClear() throws Exception{}


	protected void onClear() throws Exception{}


	protected void onAfterClear() throws Exception{

		setModelProperty(MODEL_STATE, LoginState.CLEAN);

	}


}