package developen.client.commercial.mvc;

import developen.client.framework.exception.RecordNotFoundException;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.mvc.InputCfop;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.framework.messenger.Messenger;

public class InputCfopController extends CfopController {

	
	public InputCfop getModel(){

		return (InputCfop) super.getModel();

	}

	
	public void changeIdentifierProperty(Long newValue) throws Exception {


		if ((newValue > 3999) || (newValue < 1000))

			throw new OutOfRangeException(new IdentifierTag(), 1000, 3999);

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