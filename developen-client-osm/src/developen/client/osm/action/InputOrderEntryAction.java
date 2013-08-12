package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.common.commercial.i18n.InputOrderTag;
import developen.common.framework.exception.NotYetImplementedException;
import developen.common.framework.messenger.Messenger;

public class InputOrderEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public InputOrderEntryAction(JDesktopPane desktop) {

		super(new InputOrderTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		try {
			
			throw new NotYetImplementedException();
			
		} catch (NotYetImplementedException e) {

			Messenger.show(e);
			
		}
		
		
	}

	
}