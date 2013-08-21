package developen.client.application.mvc;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;
import developen.client.application.exception.PasswordsDoNotMatchException;
import developen.client.application.exception.ShortPasswordException;
import developen.client.framework.rmi.Server;
import developen.common.commercial.i18n.SystemPersonTag;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.Checkable;

public class ChangePasswordController extends Controller {


	public static final String MODEL_STATE_PROPERTY = "ModelState";

	public static final String SYSTEMPERSON_PROPERTY = "SystemPerson";

	public static final String NEW_PASSWORD_PROPERTY = "NewPassword";

	public static final String CONFIRM_NEW_PASSWORD_PROPERTY = "ConfirmNewPassword";


	public boolean isEditing(){

		return getModel().getModelState().equals(ChangePasswordState.EDITING); 

	}


	public ChangePasswordModel getModel(){

		return (ChangePasswordModel) super.getModel();

	}


	public void changeSystemPersonProperty(SystemPerson newValue) throws Exception {


		if (newValue == null)

			throw new NotNullException(new SystemPersonTag());

		setModelProperty(ChangePasswordController.SYSTEMPERSON_PROPERTY, newValue);


	}


	public void changeNewPasswordProperty(String newValue) throws ShortPasswordException {


		if (newValue != null && newValue.length() < 6)

			throw new ShortPasswordException();

		setModelProperty(ChangePasswordController.NEW_PASSWORD_PROPERTY, newValue);


	}


	public void changeConfirmNewPasswordProperty(String newValue) 

			throws 

			ShortPasswordException, 

			PasswordsDoNotMatchException {


		if (newValue != null && newValue.length() < 6)

			throw new ShortPasswordException();

		else

			if (!newValue.equals(getModel().getNewPassword()))

				throw new PasswordsDoNotMatchException();

		setModelProperty(ChangePasswordController.CONFIRM_NEW_PASSWORD_PROPERTY, newValue);


	}	


	public void confirm() throws Exception{


		onBeforeConfirm();

		onConfirm();

		onAfterConfirm();


	}


	public void cancel() throws Exception{


		onBeforeCancel();

		onCancel();

		onAfterCancel();


	}


	public void edit() throws Exception{


		onBeforeEdit();

		onEdit();

		onAfterEdit();


	}


	protected void onBeforeConfirm() throws Exception{


		for (View view : getRegisteredViews()) {

			if (view instanceof Checkable){

				Checkable component = (Checkable) view;

				if (!component.isChecked())

					component.check();

			}

		}


	}


	protected void onConfirm() throws Exception{


		MessageDigest digest = MessageDigest.getInstance("MD5");

		digest.update(getModel().getNewPassword().getBytes());

		BASE64Encoder encoder = new BASE64Encoder();

		String criptedPassword = encoder.encode(digest.digest());

		getModel().getSystemPerson().setPassword(criptedPassword);

		Server.getService().update(getModel().getSystemPerson());


	}


	protected void onAfterConfirm() throws Exception{

		setModelProperty(ChangePasswordController.MODEL_STATE_PROPERTY, ChangePasswordState.CONFIRMED);

	}


	protected void onBeforeCancel() throws Exception{}


	protected void onCancel() throws Exception{}


	protected void onAfterCancel() throws Exception{

		setModelProperty(ChangePasswordController.MODEL_STATE_PROPERTY, ChangePasswordState.CANCELED);

	}


	protected void onBeforeEdit() throws Exception{}


	protected void onEdit() throws Exception{}


	protected void onAfterEdit() throws Exception{

		setModelProperty(ChangePasswordController.MODEL_STATE_PROPERTY, ChangePasswordState.EDITING);

	}


}