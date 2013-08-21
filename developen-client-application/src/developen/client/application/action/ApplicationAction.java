package developen.client.application.action;

import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.JDesktopPane;

import developen.client.application.mvc.ClientController;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.commercial.mvc.SystemPersonSystemAction;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.PrivilegedAction;

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

			if (systemPerson != null && systemPerson.getSystemActions() != null){

				List<SystemPersonSystemAction> actions = systemPerson.getSystemActions();
				
				for (SystemPersonSystemAction personAction : actions) {

					setEnabled(getClass().getName().equals(personAction.getIdentifier().getSystemAction().getIdentifier()));
					
					if (isEnabled())
					
						break;

				}

			} else
				
				setEnabled(false);

		}

		
	}

	
}