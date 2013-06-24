package developen.client.fiscal.mvc;

import developen.client.fiscal.search.InputCfopSearch;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.common.fiscal.i18n.InputCfopTag;
import developen.common.fiscal.mvc.InputCfop;
import developen.common.framework.utils.Tag;

public class InputCfopView extends CfopView {


	private static final long serialVersionUID = -837616976882153649L;

	
	public InputCfopView(InputCfopController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new InputCfopTag();

	}


	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new InputCfopSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((InputCfop)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

		
	}

	
}