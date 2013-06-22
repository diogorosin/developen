package developen.client.subject.mvc;

import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.subject.i18n.IdentifierTag;
import developen.common.subject.i18n.NumberTag;
import developen.common.subject.mvc.Organization;
import developen.common.subject.mvc.Rg;
import developen.common.subject.mvc.State;

public class RgController extends Controller {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String NUMBER_PROPERTY = "Number";

	public static final String ORGANIZATION_PROPERTY = "Organization";

	public static final String STATE_PROPERTY = "State";


	public Rg getModel(){

		return (Rg) super.getModel();

	}


	public void changeIdentifierProperty(Integer newValue) throws Exception {


		if (newValue==null)

			throw new NotNullException(new IdentifierTag());

		setModelProperty(IDENTIFIER_PROPERTY, newValue);


	}


	public void changeNumberProperty(String newValue) throws Exception {


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new NumberTag());

		setModelProperty(NUMBER_PROPERTY, newValue);


	}


	public void changeOrganizationProperty(Organization newValue) {

		setModelProperty(ORGANIZATION_PROPERTY, newValue);

	}


	public void changeStateProperty(State newValue) {

		setModelProperty(STATE_PROPERTY, newValue);
		
	}


}