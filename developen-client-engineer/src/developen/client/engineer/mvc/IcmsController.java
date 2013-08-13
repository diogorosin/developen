package developen.client.engineer.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.client.framework.exception.RecordNotFoundException;
import developen.client.framework.mvc.EntryController;
import developen.common.engineer.i18n.DenominationTag;
import developen.common.engineer.i18n.IdentifierTag;
import developen.common.engineer.mvc.Icms;
import developen.common.engineer.mvc.IcmsRule;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.framework.messenger.Messenger;

public class IcmsController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";

	public static final String RULES_PROPERTY = "Rules";


	public Icms getModel(){

		return (Icms) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if ((newValue > 9999999) || (newValue < 1))

			throw new OutOfRangeException(new IdentifierTag(), 1, 9999999);

		setModelProperty(IcmsController.IDENTIFIER_PROPERTY, newValue);

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

		setModelProperty(IcmsController.DENOMINATION_PROPERTY, newValue);

		
	}


	public void changeRulesProperty(List<IcmsRule> newValue) {

		setModelProperty(IcmsController.RULES_PROPERTY, newValue);

	}


	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(IcmsController.IDENTIFIER_PROPERTY, null);
		
		setModelProperty(IcmsController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(IcmsController.RULES_PROPERTY, null);


	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(IcmsController.RULES_PROPERTY, new ArrayList<IcmsRule>());


	}


}