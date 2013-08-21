package developen.client.commercial.mvc;

import developen.client.framework.exception.RecordNotFoundException;
import developen.client.framework.mvc.EntryController;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.mvc.Cfop;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.framework.messenger.Messenger;

public class CfopController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";

	public static final String NOTE_PROPERTY = "Note";


	public Cfop getModel(){

		return (Cfop) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if ((newValue > 9999) || (newValue < 1000))

			throw new OutOfRangeException(new IdentifierTag(), 1000, 9999);

		setModelProperty(CfopController.IDENTIFIER_PROPERTY, newValue);

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

		setModelProperty(CfopController.DENOMINATION_PROPERTY, newValue);


	}


	public void changeNoteProperty(String newValue) throws Exception {

		setModelProperty(CfopController.NOTE_PROPERTY, newValue);

	}


	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(CfopController.IDENTIFIER_PROPERTY, null);

		setModelProperty(CfopController.DENOMINATION_PROPERTY, null);

		setModelProperty(CfopController.NOTE_PROPERTY, null);


	}


}