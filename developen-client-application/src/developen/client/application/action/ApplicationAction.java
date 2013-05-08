package developen.client.application.action;

import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.JDesktopPane;

import developen.client.application.mvc.ClientController;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.PrivilegedAction;
import developen.common.subject.mvc.PersonAction;
import developen.common.subject.mvc.SystemPerson;

public abstract class ApplicationAction extends PrivilegedAction {


	private static final long serialVersionUID = 2008768539848263947L;

	
	public ApplicationAction(Tag tag) {

		super(tag);

	}

	
	public ApplicationAction(Tag tag, JDesktopPane desktop) {

		super(tag, desktop);

	}

	
	public void modelPropertyChanged(PropertyChangeEvent e) {

		
		if (e.getPropertyName().equals(ClientController.SYSTEM_PERSON_PROPERTY)){

			SystemPerson systemPerson  = (SystemPerson) e.getNewValue();

			if (systemPerson != null && systemPerson.getActions() != null){

				List<PersonAction> actions = systemPerson.getActions();
				
				for (PersonAction personAction : actions) {

					setEnabled(getClass().getName().equals(personAction.getIdentifier().getSystemAction().getIdentifier()));
					
					if (isEnabled())
					
						break;

				}

			} else
				
				setEnabled(false);

		}

		
	}

	
}