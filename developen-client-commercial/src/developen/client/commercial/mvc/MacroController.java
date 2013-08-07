package developen.client.commercial.mvc;

import developen.client.framework.mvc.EntryController;
import developen.common.commercial.mvc.Macro;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;

public class MacroController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";

	public static final String ACTIVE_PROPERTY = "Active";
	

	public Macro getModel(){

		return (Macro) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if (newValue > 999999)

			throw new OutOfRangeException(new IdentifierTag(), 0, 999999);

		setModelProperty(MacroController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			refresh();

		else 

			include();


	}


	public void changeDenominationProperty(String newValue) throws Exception{


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(MacroController.DENOMINATION_PROPERTY, newValue);


	}


	public void changeActiveProperty(Boolean newValue) {

		setModelProperty(MacroController.ACTIVE_PROPERTY, newValue);

	}

	
	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(MacroController.IDENTIFIER_PROPERTY, null);

		setModelProperty(MacroController.DENOMINATION_PROPERTY, null);

		setModelProperty(MacroController.ACTIVE_PROPERTY, new Boolean(false));
		

	}


	public void onInclude() throws Exception{


		super.onInclude();
		
		setModelProperty(MacroController.ACTIVE_PROPERTY, new Boolean(true));


	}


}