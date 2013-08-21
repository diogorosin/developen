package developen.client.commercial.mvc;

import developen.common.commercial.i18n.OrganizationTag;
import developen.common.commercial.i18n.StateTag;
import developen.common.commercial.mvc.Person;
import developen.common.framework.exception.NotNullException;

public class PersonController extends SubjectController {

	
	public Person getModel(){

		return (Person) super.getModel();

	}

	
	public void onClear() throws Exception{

		
		super.onClear();
		
		getModel().getCpf().setIdentifier(null);

		getModel().getCpf().setNumber(null);

		getModel().getRg().setIdentifier(null);
		
		getModel().getRg().setNumber(null);
		
		getModel().getRg().setOrganization(null);
		
		getModel().getRg().setState(null);
		
		
	}

	
	public void onInclude() throws Exception{

		
		super.onInclude();

		getModel().getCpf().setIdentifier(null);

		getModel().getCpf().setNumber(null);
		
		getModel().getRg().setIdentifier(null);
		
		getModel().getRg().setNumber(null);
		
		getModel().getRg().setOrganization(null);
		
		getModel().getRg().setState(null);
		
		
	}
	

	protected void onBeforeSave() throws Exception{


		super.onBeforeSave();
		
		if (getModel().getRg().getOrganization()==null)
			
			throw new NotNullException(new OrganizationTag()); 

		if (getModel().getRg().getState()==null)
			
			throw new NotNullException(new StateTag()); 

		
	}

	
}