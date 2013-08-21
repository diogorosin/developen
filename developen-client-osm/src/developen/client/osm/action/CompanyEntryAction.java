package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.CompanyController;
import developen.client.commercial.mvc.CompanyView;
import developen.client.osm.Client;
import developen.common.commercial.i18n.CompanyTag;
import developen.common.commercial.mvc.Company;
import developen.common.framework.widget.InternalFramePosition;

public class CompanyEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public CompanyEntryAction(JDesktopPane desktop) {

		super(new CompanyTag(), desktop);
		
	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		Company model = new Company();
		
		CompanyController controller = new CompanyController();
		
		controller.setModel(model);
		
		CompanyView view = new CompanyView(controller);
		
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