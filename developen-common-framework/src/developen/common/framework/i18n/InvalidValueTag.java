package developen.common.framework.i18n;

import developen.common.framework.utils.Tag;
import developen.common.framework.utils.TagParam;


public class InvalidValueTag extends Tag {


	public InvalidValueTag(Object firstValue, Tag field){

		
		super();
		
		getParams().add(new TagParam(TagParam.FIRST_VALUE, firstValue));

		getParams().add(new TagParam(TagParam.FIELD, field));
		
		
	}

	
}