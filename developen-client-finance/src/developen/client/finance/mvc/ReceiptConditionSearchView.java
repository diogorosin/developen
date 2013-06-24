package developen.client.finance.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.finance.i18n.ReceiptConditionTag;
import developen.common.framework.utils.Tag;

public class ReceiptConditionSearchView extends ConditionSearchView {


	private static final long serialVersionUID = 1L;

	
	public ReceiptConditionSearchView(SearchController controller) {

		super(controller);

	}

		
	public Tag getInternalFrameTitle() {

		return new ReceiptConditionTag(); 

	}

	
}