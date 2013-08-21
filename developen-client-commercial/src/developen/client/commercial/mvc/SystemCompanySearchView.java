package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.i18n.SystemCompanyTag;
import developen.common.framework.utils.Tag;

public class SystemCompanySearchView extends CompanySearchView {

	
	private static final long serialVersionUID = 3429171322054677147L;

	
	public SystemCompanySearchView(SearchController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new SystemCompanyTag(); 

	}

	
}