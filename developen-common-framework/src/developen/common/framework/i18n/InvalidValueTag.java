package developen.common.framework.i18n;

import developen.common.framework.utils.Tag;


public class InvalidValueTag extends Tag {


	public InvalidValueTag(Object firstValue, Tag field){

		
		super();
		
		put(Tag.FIRST_VALUE, firstValue);

		put(Tag.FIELD, field);
		
		
	}

	
}