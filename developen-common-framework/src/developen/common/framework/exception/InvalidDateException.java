package developen.common.framework.exception;

import developen.common.framework.i18n.InvalidDateTag;
import developen.common.framework.utils.Tag;

public class InvalidDateException extends Exception {

	
	private static final long serialVersionUID = 7030733098623765965L;
	
	private InvalidDateTag tag;
	
	
	public InvalidDateException(String firstValue, Tag field){
		
		setTag(new InvalidDateTag(firstValue, field));
		
	}

	
	public String getMessage(){
		
		return getTag().toString();
		
	}


	public InvalidDateTag getTag() {
		
		return tag;
		
	}


	public void setTag(InvalidDateTag tag) {
		
		this.tag = tag;
		
	}

	
}