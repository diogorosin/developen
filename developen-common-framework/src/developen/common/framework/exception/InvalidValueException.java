package developen.common.framework.exception;

import developen.common.framework.i18n.InvalidValueTag;
import developen.common.framework.utils.Tag;

public class InvalidValueException extends Exception {

	
	private static final long serialVersionUID = 7030733098623765965L;
	
	private InvalidValueTag tag;
	
	
	public InvalidValueException(Object firstValue, Tag field){
		
		setTag(new InvalidValueTag(firstValue, field));
		
	}

	
	public String getMessage(){
		
		return getTag().toString();
		
	}


	public InvalidValueTag getTag() {
		
		return tag;
		
	}


	public void setTag(InvalidValueTag tag) {
		
		this.tag = tag;
		
	}

	
}