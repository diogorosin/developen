package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.subject.mvc.RuleController;
import developen.client.subject.mvc.RuleView;
import developen.common.framework.widget.InternalFramePosition;
import developen.common.subject.i18n.TributaryRuleTag;
import developen.common.subject.mvc.Rule;

public class RuleEntryAction extends ApplicationAction {


	private static final long serialVersionUID = 1685330308713832918L;


	public RuleEntryAction(JDesktopPane desktop) {

		super(new TributaryRuleTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		Rule model = new Rule();
		
		RuleController controller = new RuleController();
		
		controller.setModel(model);
		
		RuleView view = new RuleView(controller);
		
		controller.addView(view);

		try {
			
			controller.clear();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);
		
		
	}

	
}