package developen.client.osm.action;


import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.application.widget.LoggedInAndEntryActiveCondition;
import developen.client.framework.i18n.OpenSearchTag;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBAction;
import developen.client.osm.Client;

public class OpenSearchAction extends DBAction {


	private static final long serialVersionUID = 1439891185225088091L;


	public OpenSearchAction() {

		super(new OpenSearchTag());

	}


	public void actionPerformed(ActionEvent e) {

		
		JDesktopPane d = Client.getClientView().getDesktop();
		
		JInternalFrame f = d.getSelectedFrame();
		
		if (f instanceof EntryView)
			
			((EntryView) f).getSearch().openSearchView(d);
		
		if (f instanceof ListEditorView)
			
			((ListEditorView) f).getSearch().openSearchView(d);

	}	

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new LoggedInAndEntryActiveCondition();
		
		return condition;

		
	}

	
}