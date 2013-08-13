package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.i18n.OutputCfopTag;
import developen.common.framework.utils.Tag;

public class OutputCfopSearchView extends CfopSearchView {


	private static final long serialVersionUID = -6227055460494662697L;


	public OutputCfopSearchView(SearchController controller) {

		super(controller);

	}

		
	public Tag getInternalFrameTitle() {

		return new OutputCfopTag(); 

	}

	
}