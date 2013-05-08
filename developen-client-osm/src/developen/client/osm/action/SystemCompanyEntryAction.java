package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.application.i18n.SystemCompaniesTag;
import developen.client.application.mvc.SystemCompanyController;
import developen.client.application.mvc.SystemCompanyView;
import developen.common.framework.widget.InternalFramePosition;
import developen.common.subject.mvc.SystemCompany;

public class SystemCompanyEntryAction extends ApplicationAction {


	private static final long serialVersionUID = -1381822675217039440L;

	
	public SystemCompanyEntryAction(JDesktopPane desktop) {

		super(new SystemCompaniesTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent e) {

		
		SystemCompany model = new SystemCompany();
		
		SystemCompanyController controller = new SystemCompanyController();
		
		controller.setModel(model);
		
		SystemCompanyView view = new SystemCompanyView(controller);
		
		controller.addView(view);

		try {
			
			controller.clear();
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);

		
	}
	

}