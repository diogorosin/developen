package developen.client.commercial.mvc;

import developen.client.framework.exception.RecordNotFoundException;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.mvc.OutputCfop;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.framework.messenger.Messenger;

public class OutputCfopController extends CfopController {

	
	public OutputCfop getModel(){

		return (OutputCfop) super.getModel();

	}

	
	public void changeIdentifierProperty(Long newValue) throws Exception {


		if ((newValue > 7999) || (newValue < 5000))

			throw new OutOfRangeException(new IdentifierTag(), 5000, 7999);

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

	
}