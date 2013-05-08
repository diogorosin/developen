package developen.client.application.exception;

import developen.client.application.i18n.InvalidPasswordTag;

public class InvalidPasswordException extends Exception {


	private static final long serialVersionUID = 7698541096915807805L;

	
	public String getMessage(){

		return new InvalidPasswordTag().toString();

	}

	
}
