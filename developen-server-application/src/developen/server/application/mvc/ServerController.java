package developen.server.application.mvc;

import java.util.List;

import developen.common.framework.exception.UpdatingServerException;
import developen.common.framework.mvc.Controller;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.query.Query;
import developen.common.persistence.rmi.Service;
import developen.common.persistence.session.Session;
import developen.common.persistence.session.SessionUtils;

public class ServerController extends Controller implements Service {


	public static final String SERVER_STATE_PROPERTY = "ServerState";

	
	public Boolean isReady(){
				
		return getModel().getServerState().equals(ServerState.READY);
		
	}

	
	public Boolean isUpdating(){
		
		return getModel().getServerState().equals(ServerState.UPDATING);
		
	}

	
	public ServerModel getModel(){
		
		return (ServerModel) super.getModel();
		
	}
	
	
	public void ready() throws Exception{


		onBeforeReady();

		onReady();

		onAfterReady();


	}


	private void onBeforeReady() throws Exception{


		
	}


	private void onReady() throws Exception{


		
	}


	private void onAfterReady() throws Exception{

		setModelProperty(ServerController.SERVER_STATE_PROPERTY, ServerState.READY);

	}

	
	public void exit() throws Exception{


		onBeforeExit();

		onExit();

		onAfterExit();


	}


	private void onBeforeExit() throws Exception{

		setModelProperty(ServerController.SERVER_STATE_PROPERTY, ServerState.EXITING);

	}


	private void onExit() throws Exception{

		System.exit(0);

	}


	private void onAfterExit() throws Exception{

		

	}


	public Object create(final Object object) throws Exception {


		if (isUpdating())
			
			throw new UpdatingServerException();
		
		Session session = DPA.getSessionFactory().createSession();

		session.create(object);

		session.close();

		return SessionUtils.getIdentifier(object);


	}


	public Object read(Class<?> table, Object identifier) throws Exception {


		if (isUpdating())
			
			throw new UpdatingServerException();

		Session session = DPA.getSessionFactory().createSession();

		Object object = session.read(table, identifier);

		session.close();

		return object;


	}


	public Object update(final Object object) throws Exception {


		if (isUpdating())
			
			throw new UpdatingServerException();

		Session session = DPA.getSessionFactory().createSession();

		session.update(object);

		session.close();

		return SessionUtils.getIdentifier(object);


	}


	public void delete(Object object) throws Exception {


		if (isUpdating())
			
			throw new UpdatingServerException();

		Session session = DPA.getSessionFactory().createSession();

		session.delete(object);

		session.close();


	}


	public List<Object> list(Class<?> from) throws Exception {


		if (isUpdating())
			
			throw new UpdatingServerException();

		Session session = DPA.getSessionFactory().createSession();

		List<Object> list = session.list(from);

		session.close();

		return list;


	}


	public List<Object> list(Query query) throws Exception {


		if (isUpdating())
			
			throw new UpdatingServerException();

		Session session = DPA.getSessionFactory().createSession();

		List<Object> list = session.list(query);

		session.close();

		return list;


	}


}