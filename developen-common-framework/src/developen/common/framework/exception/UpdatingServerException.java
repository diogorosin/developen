package developen.common.framework.exception;

import developen.common.framework.i18n.UpdatingServerTag;

public class UpdatingServerException extends Exception {


	private static final long serialVersionUID = 433662990473090683L;
	
	private UpdatingServerTag tag;
	

	public UpdatingServerException(){
		
		setTag(new UpdatingServerTag());
		
	}
	
	
	public String getMessage(){
		
		return getTag().toString();
		
	}


	public UpdatingServerTag getTag() {
		
		return tag;
		
	}


	public void setTag(UpdatingServerTag tag) {
		
		this.tag = tag;
		
	}
	

}