package developen.common.persistence.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equal implements Condition, Serializable, Parameterized {

	
	private static final long serialVersionUID = -5605355039358381712L;

	private Column column;
	
	private List<Param> params;
	
	private Query query;
	
	
	public Equal(Column column, Object value){
		
		
		setColumn(column);
		
		getParams().add(new Param(value));
		
		
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

	
	public Query getQuery() {
		
		return query;
		
	}


	public void setQuery(Query query) {

		this.query = query;
		
	}

	
	public String toString(){
		
		return getColumn().toString() + " = ?";
		
	}
	

}