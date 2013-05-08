package developen.client.application.exception;

import developen.client.application.i18n.FramesInDesktopTag;

public class FramesInDesktopException extends Exception {


	private static final long serialVersionUID = -3981178103287959629L;

	
	public String getMessage(){
		
		return new FramesInDesktopTag().toString();
		
	}

	
}