package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.i18n.SaleOrderTag;
import developen.common.framework.utils.Tag;

public class SaleOrderSearchView extends OutputOrderSearchView {


	private static final long serialVersionUID = -5393558164825823980L;


	public SaleOrderSearchView(SearchController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new SaleOrderTag(); 

	}


}