package developen.common.persistence.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import developen.common.persistence.annotation.Table;
import developen.common.persistence.annotation.View;


public class CountQuery implements Query, Serializable {


	private static final long serialVersionUID = -1898146696908038201L;

	private List<InnerJoin> innerJoins;

	private List<Condition> where;

	private List<Sort> orderBy;

	private Class<?> from;

	private Limit limit;


	public CountQuery(Class<?> from){

		setFrom(from);

	}


	public Class<?> getFrom() {

		return from;

	}


	public void setFrom(Class<?> from) {


		this.from = from;

		Class<?> subClass = this.from;

		Class<?> lastClass = null;


		while(subClass.isAnnotationPresent(Table.class) 
				
				|| subClass.isAnnotationPresent(View.class)) {

			if (lastClass!=null)

				getInnerJoins().add(new InnerJoin(subClass, lastClass));

			lastClass = subClass;

			subClass = subClass.getSuperclass();

		}
		

	}


	public List<InnerJoin> getInnerJoins() {

		
		if (innerJoins==null)

			innerJoins = new ArrayList<InnerJoin>();

		return innerJoins;

		
	}


	public void setInnerJoins(List<InnerJoin> innerJoins) {

		this.innerJoins = innerJoins;

	}


	public List<Condition> getWhere() {

		
		if (where==null)

			where = new ArrayList<Condition>(); 

		return where;

		
	}


	public void setWhere(List<Condition> where) {

		this.where = where;

	}


	public List<Sort> getOrderBy() {

		
		if (orderBy == null)

			orderBy = new ArrayList<Sort>();

		return orderBy;

		
	}


	public void setOrderBy(List<Sort> orderBy) {

		this.orderBy = orderBy;

	}


	public CountQuery add(final Condition condition){

		
		getWhere().add(condition);

		return this;

		
	}


	public CountQuery add(Sort sort){

		getOrderBy().add(sort);

		return this;

	}


	public String toString(){

		
		String sql = "SELECT COUNT(*) AS " + '"' + "count" + '"' + " FROM " + '"' + getFrom().getSimpleName() + '"';

		for (InnerJoin innerJoin : getInnerJoins())

			sql += innerJoin.toString();

		sql += !getWhere().isEmpty() ? " WHERE " : "";

		int index = 1;

		for (Iterator<Condition> i = getWhere().iterator(); i.hasNext();) {

			Condition condition = i.next();

			if (condition instanceof Parameterized){

				for (final Param param : ((Parameterized) condition).getParams())

					param.setIndex(index++);

			}

			sql += condition.toString() + (i.hasNext() ? " AND " : "");

		}

		sql += getLimit() == null ? "" : " " + getLimit();
		
		return sql;

		
	}

	
	public void setLimit(Limit limit) {

		this.limit = limit;

	}


	public Limit getLimit() {

		return this.limit;

	}

	
}