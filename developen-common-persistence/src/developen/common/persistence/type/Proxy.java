package developen.common.persistence.type;

import developen.common.persistence.query.Query;

public interface Proxy {

	
	public Query getQuery();
	
	public void setQuery(Query query);

	
}