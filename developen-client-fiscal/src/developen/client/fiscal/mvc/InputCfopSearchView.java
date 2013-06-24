package developen.client.fiscal.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.fiscal.i18n.InputCfopTag;
import developen.common.framework.utils.Tag;

public class InputCfopSearchView extends CfopSearchView {


	private static final long serialVersionUID = 1L;

	
	public InputCfopSearchView(SearchController controller) {

		super(controller);

	}

		
	public Tag getInternalFrameTitle() {

		return new InputCfopTag(); 

	}

	
}