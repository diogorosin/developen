package developen.common.framework.exception;

import developen.common.framework.i18n.OutOfRangeTag;
import developen.common.framework.utils.Tag;


public class OutOfRangeException extends Exception {


	private static final long serialVersionUID = -8604210863306678820L;
	
	private OutOfRangeTag tag;
	
	
	public OutOfRangeException(Tag field, Object firstValue, Object secondValue){
		
		setTag(new OutOfRangeTag(field, firstValue, secondValue));
		
	}

	
	public String getMessage(){
		
		return getTag().toString();
		
	}


	public OutOfRangeTag getTag() {
		
		return tag;
		
	}


	public void setTag(OutOfRangeTag tag) {
		
		this.tag = tag;
		
	}

	
}