package developen.server.application.rmi;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import developen.common.persistence.query.Query;
import developen.common.persistence.rmi.Service;
import developen.server.application.mvc.ServerController;


public class ServiceImpl extends UnicastRemoteObject implements Service {


	private static final long serialVersionUID = 3013897893008294472L;

	private ServerController controller;
	
	
	public ServiceImpl(ServerController controller) throws Exception {
			
		setController(controller);
		
	}
	
	
	public ServerController getController() {
		
		return controller;
		
	}


	public void setController(ServerController controller) {
		
		this.controller = controller;
		
	}


	public Object create(final Object object) throws Exception {
		
		return getController().create(object);
		
	}

	
	public Object read(Class<?> table, Object identifier) throws Exception {
		
		return getController().read(table, identifier);
		
	}

	
	public Object update(final Object object) throws Exception {
		
		return getController().update(object);		
		
	}

	
	public void delete(Object object) throws Exception {

		getController().delete(object);
		
	}

	
	public List<Object> list(Class<?> from) throws Exception {
		
		return getController().list(from);
		
	}

	
	public List<Object> list(Query query) throws Exception {
		
		return getController().list(query);
		
	}


}