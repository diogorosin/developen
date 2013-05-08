package developen.common.framework.exception;

import developen.common.framework.i18n.OperationCanceledByUserTag;
import developen.common.framework.messenger.InvisibleForUser;

public class OperationCanceledByUserException extends Exception implements InvisibleForUser{


	private static final long serialVersionUID = 2864058134766438483L;

	private OperationCanceledByUserTag tag;

	
	public OperationCanceledByUserException(){
		
		setTag(new OperationCanceledByUserTag());
		
	}
	
	
	public String getMessage(){
		
		return getTag().toString();
		
	}


	public OperationCanceledByUserTag getTag() {
		
		return tag;
		
	}


	public void setTag(OperationCanceledByUserTag tag) {
		
		this.tag = tag;
		
	}
	
	
}