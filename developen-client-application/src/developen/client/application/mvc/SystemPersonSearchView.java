package developen.client.application.mvc;

import developen.client.application.i18n.SystemPersonTag;
import developen.client.framework.mvc.SearchController;
import developen.client.subject.mvc.PersonSearchView;
import developen.common.framework.utils.Tag;

public class SystemPersonSearchView extends PersonSearchView {

	
	private static final long serialVersionUID = -489685580743117136L;

	
	public SystemPersonSearchView(SearchController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new SystemPersonTag(); 

	}
	
	
}