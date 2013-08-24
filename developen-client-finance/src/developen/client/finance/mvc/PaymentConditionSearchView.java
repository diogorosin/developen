package developen.client.finance.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.finance.i18n.PaymentConditionTag;
import developen.common.finance.mvc.PaymentCondition;
import developen.common.framework.mvc.Model;
import developen.common.framework.utils.Tag;

public class PaymentConditionSearchView extends ConditionSearchView {


	private static final long serialVersionUID = 1L;

	
	public PaymentConditionSearchView(SearchController controller) {

		super(controller);

	}

		
	public Tag getInternalFrameTitle() {

		return new PaymentConditionTag(); 

	}


	public Class<? extends Model> getMimeType() {

		return PaymentCondition.class;
		
	}
	
}