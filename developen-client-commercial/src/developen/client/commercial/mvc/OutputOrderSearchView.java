package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.i18n.OutputOrderTag;
import developen.common.framework.utils.Tag;

public class OutputOrderSearchView extends OrderSearchView {


	private static final long serialVersionUID = -5393558164825823980L;


	public OutputOrderSearchView(SearchController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new OutputOrderTag(); 

	}


}