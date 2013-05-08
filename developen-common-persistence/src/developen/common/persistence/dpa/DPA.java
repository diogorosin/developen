package developen.common.persistence.dpa;

import developen.common.persistence.session.SessionFactory;


public class DPA {

	
	private static SessionFactory sessionFactory;

	
	public static SessionFactory getSessionFactory() {
		
		return sessionFactory;
		
	}
	

	public static void setSessionFactory(SessionFactory sessionFactory) {
		
		DPA.sessionFactory = sessionFactory;
		
	}
	
	
}