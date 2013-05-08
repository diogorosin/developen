package developen.client.application.mvc;

import developen.client.application.i18n.SystemCompanyTag;
import developen.client.application.search.SystemCompanySearch;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.subject.mvc.CompanyView;
import developen.common.framework.utils.Tag;
import developen.common.subject.mvc.SystemCompany;

public class SystemCompanyView extends CompanyView {

	private static final long serialVersionUID = 1L;
	
	public SystemCompanyView(SystemCompanyController controller) {

		super(controller);

	}

	public Tag getInternalFrameTitle() {

		return new SystemCompanyTag();

	}

	public Search getIdentifierSearch(){

		if (identifierSearch == null){

			identifierSearch = new SystemCompanySearch(true);
			identifierSearch.addSearchListener(new SearchAdapter() {
				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((SystemCompany)event.getSelectedRows().get(0)).getIdentifier());

				}
			});

		}
		return identifierSearch;

	}

}