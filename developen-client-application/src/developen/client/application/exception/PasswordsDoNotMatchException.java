package developen.client.application.exception;

import developen.client.application.i18n.PasswordsDoNotMatchTag;

public class PasswordsDoNotMatchException extends Exception {

	
	private static final long serialVersionUID = 9146192968606303917L;

	
	public String getMessage(){

		return new PasswordsDoNotMatchTag().toString();

	}

	
}