package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.i18n.OutputMacroTag;
import developen.common.commercial.mvc.OutputMacro;
import developen.common.framework.mvc.Model;
import developen.common.framework.utils.Tag;

public class OutputMacroSearchView extends MacroSearchView {


	private static final long serialVersionUID = -5393558164825823980L;


	public OutputMacroSearchView(SearchController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new OutputMacroTag(); 

	}


	public Class<? extends Model> getMimeType(){
		
		return OutputMacro.class;
		
	}


}