package developen.common.persistence.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Between implements Condition, Serializable, Parameterized {


	private static final long serialVersionUID = -3429750408606495939L;

	private List<Param> params;

	private Column column;
	
	private Query query;

	
	public Between(Column column, Object low, Object high) {

		setColumn(column);
		
		getParams().add(new Param(low));
		
		getParams().add(new Param(high));

	}

	
	public Column getColumn() {
		
		return column;
		
	}

	
	public void setColumn(Column column) {
		
		this.column = column;
		
	}

	
	public List<Param> getParams() {
		
		if (params==null)
			
			params = new ArrayList<Param>();
		
		return params;
		
	}


	public void setParams(List<Param> params) {
		
		this.params = params;
		
	}

	
	public String toString(){
	
		return "(" + getColumn() + " BETWEEN ? AND ?)";
		
	}


	public Query getQuery() {
		
		return query;
		
	}


	public void setQuery(Query query) {

		this.query = query;
		
	}
	

}