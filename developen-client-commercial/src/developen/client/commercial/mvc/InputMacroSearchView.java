package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.i18n.InputMacroTag;
import developen.common.commercial.mvc.InputMacro;
import developen.common.framework.mvc.Model;
import developen.common.framework.utils.Tag;

public class InputMacroSearchView extends MacroSearchView {


	private static final long serialVersionUID = -5393558164825823980L;


	public InputMacroSearchView(SearchController controller) {

		super(controller);

	}

	
	public Tag getInternalFrameTitle() {

		return new InputMacroTag(); 

	}


	public Class<? extends Model> getMimeType(){
		
		return InputMacro.class;
		
	}


}