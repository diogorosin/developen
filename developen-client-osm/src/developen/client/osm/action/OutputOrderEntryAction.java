package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.OutputOrderController;
import developen.client.commercial.mvc.OutputOrderView;
import developen.client.osm.Client;
import developen.common.commercial.i18n.OutputOrderTag;
import developen.common.commercial.mvc.OutputOrder;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.framework.widget.InternalFramePosition;

public class OutputOrderEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public OutputOrderEntryAction(JDesktopPane desktop) {

		super(new OutputOrderTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		OutputOrder model = new OutputOrder();
		
		OutputOrderController controller = new OutputOrderController(){

			public SystemCompany getSystemCompany() {

				return Client.getClientModel().getSystemCompany();
				
			}

		};
		
		controller.setModel(model);
		
		OutputOrderView view = new OutputOrderView(controller);
		
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