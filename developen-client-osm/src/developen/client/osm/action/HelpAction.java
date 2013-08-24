package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.i18n.HelpTag;
import developen.common.framework.exception.NotYetImplementedException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.Action;

public class HelpAction extends Action {


	private static final long serialVersionUID = -6181497408609505731L;


	public HelpAction() {

		super(new HelpTag());

	}


	public void actionPerformed(ActionEvent e) {


		try {

			throw new NotYetImplementedException();

		} catch (NotYetImplementedException e1) {

			Messenger.show(e1);

		}


	}


}