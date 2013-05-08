package developen.client.framework.exception;

import developen.client.framework.i18n.ManyRecordsFoundTag;
import developen.common.framework.messenger.InvisibleForUser;

public class ManyRecordsFoundException extends Exception implements InvisibleForUser {


	private static final long serialVersionUID = 2917073768345105528L;

	
	public String getMessage(){
		
		return new ManyRecordsFoundTag().toString();
		
	}
	

}