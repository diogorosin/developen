package developen.common.persistence.query;

import java.util.List;

public interface Query {


	public Class<?> getFrom();

	public void setFrom(Class<?> from);

	public List<InnerJoin> getInnerJoins();

	public void setInnerJoins(List<InnerJoin> innerJoins);

	public List<Condition> getWhere();

	public void setWhere(List<Condition> where);

	public List<Sort> getOrderBy();

	public void setOrderBy(List<Sort> orderBy);

	public Query add(final Condition condition);

	public Query add(Sort sort);
	
	public void setLimit(Limit limit);

	public Limit getLimit();
	
	
}