package developen.common.framework.i18n;

import developen.common.framework.utils.Tag;
import developen.common.framework.utils.TagParam;

public class OutOfRangeTag extends Tag {

	
	public OutOfRangeTag(Tag field, Object firstValue, Object secondValue){
		
		
		super();
		
		getParams().add(new TagParam(TagParam.FIELD, field));
		
		getParams().add(new TagParam(TagParam.FIRST_VALUE, firstValue));
		
		getParams().add(new TagParam(TagParam.SECOND_VALUE, secondValue));
		
		
	}
	
	
}