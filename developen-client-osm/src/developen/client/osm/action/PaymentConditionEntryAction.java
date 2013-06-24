package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.finance.mvc.PaymentConditionController;
import developen.client.finance.mvc.PaymentConditionView;
import developen.client.osm.Client;
import developen.common.finance.i18n.PaymentConditionTag;
import developen.common.finance.mvc.PaymentCondition;
import developen.common.framework.widget.InternalFramePosition;

public class PaymentConditionEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public PaymentConditionEntryAction(JDesktopPane desktop) {

		super(new PaymentConditionTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		PaymentCondition model = new PaymentCondition();
		
		PaymentConditionController controller = new PaymentConditionController();
		
		controller.setModel(model);
		
		PaymentConditionView view = new PaymentConditionView(controller);
		
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