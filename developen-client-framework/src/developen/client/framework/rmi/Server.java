package developen.client.framework.rmi;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import developen.common.persistence.rmi.Service;


public class Server {


	private static Service service = null;


	public static Service getService() 

			throws MalformedURLException, 

			RemoteException, 

			NotBoundException {

		if (service==null) {

			service = (Service) Naming.lookup("rmi://" +

					System.getProperty("developen.server.host", "localhost") + ":" +

					System.getProperty("developen.server.port", "1099") + "/" +

					Service.SERVICE_NAME);

		}

		return service;

	}


}