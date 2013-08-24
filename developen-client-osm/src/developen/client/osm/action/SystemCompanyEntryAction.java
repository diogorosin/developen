package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.SystemCompaniesTag;
import developen.common.framework.messenger.Messenger;

public class SystemCompanyEntryAction extends EntryAction {


	private static final long serialVersionUID = -1381822675217039440L;

	
	public SystemCompanyEntryAction(JDesktopPane desktop) {

		super(new SystemCompaniesTag());

	}

	
	public void actionPerformed(ActionEvent e) {


		try {
			
			Client.getClientView().executeSystemCompanyEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}
		
		
	}
	

}