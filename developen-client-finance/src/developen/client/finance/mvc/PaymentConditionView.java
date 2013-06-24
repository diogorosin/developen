package developen.client.finance.mvc;

import developen.client.finance.search.PaymentConditionSearch;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.common.finance.i18n.PaymentConditionTag;
import developen.common.finance.mvc.PaymentCondition;
import developen.common.framework.utils.Tag;

public class PaymentConditionView extends ConditionView {


	private static final long serialVersionUID = -837616976882153649L;

	
	public PaymentConditionView(PaymentConditionController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new PaymentConditionTag();

	}

	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new PaymentConditionSearch(new Boolean(true));

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((PaymentCondition)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

		
	}

	
}