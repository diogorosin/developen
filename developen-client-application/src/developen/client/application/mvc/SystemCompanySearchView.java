package developen.client.application.mvc;

import developen.client.application.i18n.SystemCompanyTag;
import developen.client.framework.mvc.SearchController;
import developen.client.subject.mvc.CompanySearchView;
import developen.common.framework.utils.Tag;

public class SystemCompanySearchView extends CompanySearchView {

	private static final long serialVersionUID = 1L;
	
	public SystemCompanySearchView(SearchController controller) {

		super(controller);

	}

	public Tag getInternalFrameTitle() {

		return new SystemCompanyTag(); 

	}

}