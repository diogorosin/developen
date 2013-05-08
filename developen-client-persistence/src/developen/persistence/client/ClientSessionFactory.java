package developen.persistence.client;

import developen.common.persistence.session.Session;
import developen.common.persistence.session.SessionFactory;

public class ClientSessionFactory implements SessionFactory{

	
	public Session createSession() throws Exception {

		return new ClientSession();
		
	}

	
}