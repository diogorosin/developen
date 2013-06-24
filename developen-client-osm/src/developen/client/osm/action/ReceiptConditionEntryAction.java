package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.finance.mvc.ReceiptConditionController;
import developen.client.finance.mvc.ReceiptConditionView;
import developen.client.osm.Client;
import developen.common.finance.i18n.ReceiptConditionTag;
import developen.common.finance.mvc.ReceiptCondition;
import developen.common.framework.widget.InternalFramePosition;

public class ReceiptConditionEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public ReceiptConditionEntryAction(JDesktopPane desktop) {

		super(new ReceiptConditionTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		ReceiptCondition model = new ReceiptCondition();
		
		ReceiptConditionController controller = new ReceiptConditionController();
		
		controller.setModel(model);
		
		ReceiptConditionView view = new ReceiptConditionView(controller);
		
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