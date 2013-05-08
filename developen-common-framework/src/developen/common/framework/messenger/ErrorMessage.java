package developen.common.framework.messenger;

import developen.common.framework.utils.Tag;


public class ErrorMessage extends Message {

	
	private Exception exception;

	
	public ErrorMessage(Tag text) {

		super(text);

	}

	
	public ErrorMessage(Tag text, Exception exception) {

		super(text);
		
		this.setException(exception);

	}

	
	public void setException(Exception exception) {

		this.exception = exception;

	}

	
	public Exception getException() {

		return exception;

	}

	
}
