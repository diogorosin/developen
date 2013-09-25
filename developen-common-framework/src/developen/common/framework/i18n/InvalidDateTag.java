package developen.common.framework.i18n;

import developen.common.framework.utils.Tag;


public class InvalidDateTag extends Tag {


	public InvalidDateTag(String firstValue, Tag field){

		
		super();
		
		put(Tag.FIRST_VALUE, firstValue);

		put(Tag.FIELD, field);
		
		
	}

	
}