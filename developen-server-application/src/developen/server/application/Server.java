package developen.server.application;


import java.awt.SystemTray;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import developen.common.persistence.dpa.DPA;
import developen.common.persistence.rmi.Service;
import developen.server.application.mvc.ServerController;
import developen.server.application.mvc.ServerModel;
import developen.server.application.mvc.ServerView;
import developen.server.application.rmi.ServiceImpl;
import developen.server.persistence.ServerConfiguration;
import developen.server.persistence.ServerSessionFactory;


public class Server {


	public static void main(String[] args) {


		ServerSessionFactory serverSessionFactory = new ServerConfiguration().

				configure().

				buildServerSessionFactory();

		DPA.setSessionFactory(serverSessionFactory);

		try{

			ServerModel model = new ServerModel();

			ServerController controller = new ServerController();

			controller.setModel(model);

			if (SystemTray.isSupported()){

				ServerView view = new ServerView(controller);

				SystemTray.getSystemTray().add(view);

				controller.addView(view);

			}

			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

			Naming.bind(Service.SERVICE_NAME, new ServiceImpl(controller));

			controller.ready();

		} catch (Exception e) {

			e.printStackTrace();

		}		


	}


}