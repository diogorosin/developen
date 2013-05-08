package developen.common.persistence.session;

import java.util.List;

import developen.common.persistence.query.Query;




public interface Session {
	
	
	public void create(final Object object) throws Exception;

	public Object read(Class<?> table, Object identifier) throws Exception;
	
	public void update(final Object object) throws Exception;

	public void delete(Object object) throws Exception;
	
	public List<Object> list(Class<?> from) throws Exception;
	
	public List<Object> list(Query query) throws Exception;

	public void close() throws Exception;
	
	
}