package developen.client.commercial.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.client.framework.exception.RecordNotFoundException;
import developen.client.framework.mvc.EntryController;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.mvc.PisCofins;
import developen.common.commercial.mvc.PisCofinsRule;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.framework.messenger.Messenger;

public class PisCofinsController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";

	public static final String RULES_PROPERTY = "Rules";


	public PisCofins getModel(){

		return (PisCofins) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if ((newValue > 9999999) || (newValue < 1))

			throw new OutOfRangeException(new IdentifierTag(), 1, 9999999);

		setModelProperty(PisCofinsController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			try {

				refresh();

			} catch (Exception e) {

				if (e instanceof RecordNotFoundException)

					include();

				else

					Messenger.show(e);					

			}

		else 

			include();


	}


	public void changeDenominationProperty(String newValue) throws Exception {

		
		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(PisCofinsController.DENOMINATION_PROPERTY, newValue);

		
	}


	public void changeRulesProperty(List<PisCofinsRule> newValue) {

		setModelProperty(PisCofinsController.RULES_PROPERTY, newValue);

	}


	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(PisCofinsController.IDENTIFIER_PROPERTY, null);
		
		setModelProperty(PisCofinsController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(PisCofinsController.RULES_PROPERTY, null);


	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(PisCofinsController.RULES_PROPERTY, new ArrayList<PisCofinsRule>());


	}


}