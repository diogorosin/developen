package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.i18n.SystemPersonTag;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.framework.mvc.Model;
import developen.common.framework.utils.Tag;

public class SystemPersonSearchView extends PersonSearchView {

	
	private static final long serialVersionUID = -489685580743117136L;

	
	public SystemPersonSearchView(SearchController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new SystemPersonTag(); 

	}
	

	public Class<? extends Model> getMimeType(){
		
		return SystemPerson.class;
		
	}

	
}