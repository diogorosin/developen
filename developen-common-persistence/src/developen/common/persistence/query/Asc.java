package developen.common.persistence.query;

import java.io.Serializable;

public class Asc implements Sort, Serializable {

	
	private static final long serialVersionUID = -5090118982875508932L;

	private Column column;
	
	private Query query;
		
	
	public Asc(Column column){
		
		this.column = column;
		
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
		
		return getColumn().toString() + " ASC";
		
	}

	
}