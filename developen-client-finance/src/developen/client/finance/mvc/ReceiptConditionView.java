package developen.client.finance.mvc;

import developen.client.finance.search.ReceiptConditionSearch;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.common.finance.i18n.ReceiptConditionTag;
import developen.common.finance.mvc.ReceiptCondition;
import developen.common.framework.utils.Tag;

public class ReceiptConditionView extends ConditionView {


	private static final long serialVersionUID = -837616976882153649L;

	
	public ReceiptConditionView(ReceiptConditionController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new ReceiptConditionTag();

	}


	public Search getSearch(){


		if (identifierSearch==null){

			identifierSearch = new ReceiptConditionSearch(new Boolean(true));

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((ReceiptCondition)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

		
	}

	
}