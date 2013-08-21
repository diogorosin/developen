package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.SaleOrderController;
import developen.client.commercial.mvc.SaleOrderView;
import developen.client.osm.Client;
import developen.common.commercial.i18n.SaleOrderTag;
import developen.common.commercial.mvc.SaleOrder;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.framework.widget.InternalFramePosition;

public class SaleOrderEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public SaleOrderEntryAction(JDesktopPane desktop) {

		super(new SaleOrderTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		SaleOrder model = new SaleOrder();
		
		SaleOrderController controller = new SaleOrderController(){

			public SystemCompany getSystemCompany() {

				return Client.getClientModel().getSystemCompany();
				
			}

			public SystemPerson getSystemPerson() {

				return Client.getClientModel().getSystemPerson();
				
			}
			
		};
		
		controller.setModel(model);
		
		SaleOrderView view = new SaleOrderView(controller);
		
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