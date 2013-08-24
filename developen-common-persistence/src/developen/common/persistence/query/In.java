package developen.common.persistence.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class In implements Condition, Serializable, Parameterized {


	private static final long serialVersionUID = -3429750408606495939L;

	private List<Param> params;

	private Column column;
	
	private Query query;

	
	public In(Column column, Object[] in) {

		
		setColumn(column);
		
		for (Object object : in)
			
			getParams().add(new Param(object));
			

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
	

		String s = "(" + getColumn() + " IN (";
		
		for (Iterator<Param> iterator = getParams().iterator(); iterator.hasNext();) {
			
			iterator.next();
			
			s += "?" + (iterator.hasNext() ? ", " : "))");
			
		}
		
		return s;
		
		
	}


	public Query getQuery() {
		
		return query;
		
	}


	public void setQuery(Query query) {

		this.query = query;
		
	}
	

}