package developen.common.framework.i18n;

import developen.common.framework.utils.Tag;
import developen.common.framework.utils.TagParam;


public class InvalidDateTag extends Tag {


	public InvalidDateTag(String firstValue, Tag field){

		
		super();
		
		getParams().add(new TagParam(TagParam.FIRST_VALUE, firstValue));

		getParams().add(new TagParam(TagParam.FIELD, field));
		
		
	}

	
}