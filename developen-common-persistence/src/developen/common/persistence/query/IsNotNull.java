package developen.common.persistence.query;

import java.io.Serializable;

public class IsNotNull implements Condition, Serializable {

	
	private static final long serialVersionUID = -1814398920234455909L;

	private Column column;
	
	private Query query;

	
	public IsNotNull(Column column){
		
		setColumn(column);
		
	}

	
	public Column getColumn() {
		
		return column;
		
	}

	
	public void setColumn(Column column) {
		
		this.column = column;
		
	}

	
	public Query getQuery() {
		
		return query;
		
	}


	public void setQuery(Query query) {

		this.query = query;
		
	}

	
	public String toString(){
		
		return getColumn().toString() + " IS NOT NULL";
		
	}
	
	
}