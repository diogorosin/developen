package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.PersonController;
import developen.client.commercial.mvc.PersonView;
import developen.client.osm.Client;
import developen.common.commercial.i18n.PersonTag;
import developen.common.commercial.mvc.Person;
import developen.common.framework.widget.InternalFramePosition;

public class PersonEntryAction extends ApplicationAction {


	private static final long serialVersionUID = 8070703605294854709L;

	
	public PersonEntryAction(JDesktopPane desktop) {

		super(new PersonTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		Person model = new Person();
		
		PersonController controller = new PersonController();
		
		controller.setModel(model);
		
		PersonView view = new PersonView(controller);
		
		controller.addView(view);

		try {
			
			controller.clear();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);
		
		Client.getClientController().addSystemPersonListener(view);

		
	}

	
}