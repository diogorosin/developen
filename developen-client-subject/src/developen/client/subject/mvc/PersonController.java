package developen.client.subject.mvc;

import developen.common.framework.exception.NotNullException;
import developen.common.subject.i18n.OrganizationTag;
import developen.common.subject.i18n.StateTag;
import developen.common.subject.mvc.Person;

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