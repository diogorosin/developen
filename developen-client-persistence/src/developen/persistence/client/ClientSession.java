package developen.persistence.client;

import java.util.List;

import developen.common.persistence.query.Query;
import developen.common.persistence.session.Session;
import developen.persistence.client.rmi.Server;


public class ClientSession implements Session{


	private Object lastGeneratedIdentifier;
	
	
	public void create(final Object object) throws Exception {

		
		setLastGeneratedIdentifier(Server.getService().create(object));
		
//		Object newObject = Server.getService().create(object);
//
//		Class<?> myClass = newObject.getClass();
//
//		while (myClass.isAnnotationPresent(Table.class)){
//
//			for(Field field : myClass.getDeclaredFields()){
//
//				if (field.isAnnotationPresent(Column.class)
//
//						|| field.isAnnotationPresent(Identifier.class)
//
//						|| field.isAnnotationPresent(ManyToOne.class)
//
//						|| field.isAnnotationPresent(OneToMany.class)
//
//						|| field.isAnnotationPresent(OneToOne.class)){
//
//					field.setAccessible(true);
//
//					Method setter = myClass.getMethod(
//							
//							"set" + field.getName().substring(0, 1).toUpperCase() +
//							
//							field.getName().substring(1)
//					
//							, new Class[]{field.getType()});
//					
//					setter.invoke(object, field.get(newObject));					
//					
//				}
//
//			}
//
//			myClass = myClass.getSuperclass();
//
//		}

		
	}

	
	public Object read(Class<?> table, Object identifier) throws Exception {

		return Server.getService().read(table, identifier);

	}

	
	public void update(final Object object) throws Exception {

		
		setLastGeneratedIdentifier(Server.getService().update(object));
		
//		Object newObject = Server.getService().update(object);
//
//		Class<?> myClass = newObject.getClass();
//
//		while (myClass.isAnnotationPresent(Table.class)){
//
//			for(Field field : myClass.getDeclaredFields()){
//
//				if (field.isAnnotationPresent(Column.class)
//
//						|| field.isAnnotationPresent(Identifier.class)
//
//						|| field.isAnnotationPresent(ManyToOne.class)
//
//						|| field.isAnnotationPresent(OneToMany.class)
//
//						|| field.isAnnotationPresent(OneToOne.class)){
//
//					field.setAccessible(true);
//
//					Method setter = myClass.getMethod(
//							
//							"set" + field.getName().substring(0, 1).toUpperCase() +
//							
//							field.getName().substring(1)
//					
//							, new Class[]{field.getType()});
//					
//					setter.invoke(object, field.get(newObject));					
//					
//				}
//
//			}
//
//			myClass = myClass.getSuperclass();
//
//		}

		
	}

	
	public void delete(Object object) throws Exception {

		Server.getService().delete(object);

	}

	
	public List<Object> list(Class<?> from) throws Exception {

		return Server.getService().list(from);

	}

	
	public List<Object> list(Query query) throws Exception {

		return Server.getService().list(query);

	}

	
	public void close() throws Exception {}


	public Object getLastGeneratedIdentifier() {
		
		return lastGeneratedIdentifier;
		
	}


	public void setLastGeneratedIdentifier(Object lastGeneratedIdentifier) {
		
		this.lastGeneratedIdentifier = lastGeneratedIdentifier;
		
	}


}