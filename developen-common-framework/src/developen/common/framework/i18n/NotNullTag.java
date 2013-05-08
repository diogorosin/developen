package developen.common.framework.i18n;

import developen.common.framework.utils.Tag;
import developen.common.framework.utils.TagParam;

public class NotNullTag extends Tag {


	public NotNullTag(Tag field){
		
		
		super();
		
		getParams().add(new TagParam(TagParam.FIELD, field));
		
		
	}
	

}