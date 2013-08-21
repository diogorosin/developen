package developen.common.engineer.exception;

import developen.common.commercial.i18n.ProductCannotContainYourselfTag;

public class ProductCannotContainYourselfException extends Exception {


	private static final long serialVersionUID = 8548156937505873802L;

	
	public String getMessage(){
		
		return new ProductCannotContainYourselfTag().toString();
		
	}

	
}
