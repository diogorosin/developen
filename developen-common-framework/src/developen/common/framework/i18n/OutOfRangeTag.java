package developen.common.framework.i18n;

import developen.common.framework.utils.Tag;

public class OutOfRangeTag extends Tag {

	
	public OutOfRangeTag(Tag field, Object firstValue, Object secondValue){
		
		
		super();
		
		put(Tag.FIELD, field);
		
		put(Tag.FIRST_VALUE, firstValue);
		
		put(Tag.SECOND_VALUE, secondValue);
		
		
	}
	
	
}