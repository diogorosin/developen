package developen.client.framework.exception;

import developen.client.framework.i18n.RecordNotFoundTag;

public class RecordNotFoundException extends Exception {


	private static final long serialVersionUID = -2884215243971174335L;

	
	public String getMessage(){
		
		return new RecordNotFoundTag().toString();
		
	}
	

}