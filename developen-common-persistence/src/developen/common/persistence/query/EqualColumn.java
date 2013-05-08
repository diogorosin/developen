package developen.common.persistence.query;

import java.io.Serializable;


public class EqualColumn implements Condition, Serializable {
	
	
	private static final long serialVersionUID = -6415354601882465035L;

	private Column firstColumn;
	
	private Column secondColumn;
	
	private Query query;

	
	public EqualColumn(Column firstColumn, Column secondColumn){
		
		setFirstColumn(firstColumn);
		
		setSecondColumn(secondColumn);
		
	}
	
	
	public Column getFirstColumn() {
		
		return firstColumn;
		
	}
	

	public void setFirstColumn(Column firstColumn) {
		
		this.firstColumn = firstColumn;
		
	}

	
	public Column getSecondColumn() {
		
		return secondColumn;
		
	}

	
	public void setSecondColumn(Column secondColumn) {
		
		this.secondColumn = secondColumn;
		
	}

	
	public Query getQuery() {
		
		return query;
		
	}


	public void setQuery(Query query) {

		this.query = query;
		
	}

	
	public String toString(){
		
		return getFirstColumn().toString() + " = " + getSecondColumn().toString();
		
	}
	
	
}