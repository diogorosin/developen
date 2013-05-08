package developen.common.framework.exception;

import developen.common.framework.i18n.NotNullTag;
import developen.common.framework.utils.Tag;


public class NotNullException extends Exception {


	private static final long serialVersionUID = -8604210863306678820L;
	
	private NotNullTag tag;
	
	
	public NotNullException(Tag field){
		
		setTag(new NotNullTag(field));
		
	}

	
	public String getMessage(){
		
		return getTag().toString();
		
	}


	public NotNullTag getTag() {
		
		return tag;
		
	}


	public void setTag(NotNullTag tag) {
		
		this.tag = tag;
		
	}

	
}