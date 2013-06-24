package developen.client.subject.mvc;

import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.subject.i18n.IdentifierTag;
import developen.common.subject.i18n.IeTag;
import developen.common.subject.mvc.Ie;

public class IeController extends Controller {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String NUMBER_PROPERTY = "Number";


	public Ie getModel(){

		return (Ie) super.getModel();

	}


	public void changeIdentifierProperty(Integer newValue) throws Exception {


		if (newValue==null)

			throw new NotNullException(new IdentifierTag());

		setModelProperty(IDENTIFIER_PROPERTY, newValue);


	}


	public void changeNumberProperty(String newValue) throws Exception {


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new IeTag());

		setModelProperty(NUMBER_PROPERTY, newValue);


	}


}