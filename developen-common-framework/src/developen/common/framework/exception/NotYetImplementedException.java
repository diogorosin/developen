package developen.common.framework.exception;

import developen.common.framework.i18n.NotYetImplementedTag;

public class NotYetImplementedException extends Exception {

	
	private static final long serialVersionUID = 6967085762076559471L;

	private NotYetImplementedTag tag;
	
	
	public NotYetImplementedException(){
		
		setTag(new NotYetImplementedTag());
		
	}

	
	public String getMessage(){
		
		return getTag().toString();
		
	}


	public NotYetImplementedTag getTag() {
		
		return tag;
		
	}


	public void setTag(NotYetImplementedTag tag) {
		
		this.tag = tag;
		
	}


}