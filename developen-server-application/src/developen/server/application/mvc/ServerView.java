package developen.server.application.mvc;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import developen.common.framework.i18n.ExitTag;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.SystemTrayIcon;
import developen.server.application.i18n.DevelOpenServerTag;


public class ServerView extends SystemTrayIcon {


	public ServerView(ServerController controller) {


		super(SystemTray.getSystemTray().getTrayIconSize().height==16 

				? new DevelOpenServerTag().getSmallIcon().getImage()

						: new DevelOpenServerTag().getLargeIcon().getImage() 

						, new DevelOpenServerTag(), controller);


	}


	public void init() {


		PopupMenu menu = new PopupMenu();

		MenuItem exit = new MenuItem(new ExitTag().toString());

		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {

					getController().exit();

				} catch (Exception e) {

					Messenger.show(e);

				}

			}

		});

		menu.add(exit);

		setPopupMenu(menu);


	}


	public void modelPropertyChanged(PropertyChangeEvent evt) {}


	public ServerController getController(){

		return (ServerController) super.getController();

	}


}