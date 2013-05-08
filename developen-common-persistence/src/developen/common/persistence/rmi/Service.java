package developen.common.persistence.rmi;

import java.rmi.Remote;
import java.util.List;

import developen.common.persistence.query.Query;


public interface Service extends Remote {


	public static String SERVICE_NAME = "Service";
	
	public Object create(final Object object) throws Exception;

	public Object read(Class<?> table, Object identifier) throws Exception;
	
	public Object update(final Object object) throws Exception;

	public void delete(Object object) throws Exception;
	
	public List<Object> list(Class<?> from) throws Exception;
	
	public List<Object> list(Query query) throws Exception;

		
}