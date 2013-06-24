package developen.client.fiscal.mvc;

import developen.client.fiscal.search.OutputCfopSearch;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.common.fiscal.i18n.OutputCfopTag;
import developen.common.fiscal.mvc.OutputCfop;
import developen.common.framework.utils.Tag;

public class OutputCfopView extends CfopView {


	private static final long serialVersionUID = 7161516192544216282L;

	
	public OutputCfopView(OutputCfopController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new OutputCfopTag();

	}

	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new OutputCfopSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((OutputCfop)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

		
	}

	
}