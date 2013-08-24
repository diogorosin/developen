package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.i18n.HelpAboutTag;
import developen.client.osm.Client;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.Action;

public class HelpAboutAction extends Action {


	private static final long serialVersionUID = -8734803996901758389L;

	
	public HelpAboutAction(JDesktopPane desktop) {

		super(new HelpAboutTag());

	}

	
	public void actionPerformed(ActionEvent e) {

		
		try {
			
			Client.getClientView().executeHelpAbout();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}