package developen.common.finance.exception;

import developen.common.finance.i18n.PercentageSumMustBeHundredTag;

public class PercentageSumMustBeHundredException extends Exception {

	
	private static final long serialVersionUID = 6967085762076559471L;

	private PercentageSumMustBeHundredTag tag;
	
	
	public PercentageSumMustBeHundredException(){
		
		setTag(new PercentageSumMustBeHundredTag());
		
	}

	
	public String getMessage(){
		
		return getTag().toString();
		
	}


	public PercentageSumMustBeHundredTag getTag() {
		
		return tag;
		
	}


	public void setTag(PercentageSumMustBeHundredTag tag) {
		
		this.tag = tag;
		
	}


}