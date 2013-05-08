package developen.common.persistence.session;


public interface SessionFactory {
	
	public Session createSession() throws Exception;
	
}