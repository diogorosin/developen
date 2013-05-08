package developen.client.application.exception;

import developen.client.application.i18n.ShortPasswordTag;

public class ShortPasswordException extends Exception {

	
	private static final long serialVersionUID = 6671201187702348175L;

	
	public String getMessage(){

		return new ShortPasswordTag().toString();

	}

	
}