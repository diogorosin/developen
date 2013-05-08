package developen.common.persistence.query;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;
import developen.common.persistence.annotation.View;


public class ColumnQuery implements Query, Serializable{


	private static final long serialVersionUID = -8008469887901855230L;

	private List<developen.common.persistence.query.Column> columns;

	private List<InnerJoin> innerJoins;

	private List<Condition> where;

	private List<Sort> orderBy;

	private Class<?> from;

	private Limit limit;


	public ColumnQuery(Class<?> from){

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

			for (Field field : subClass.getDeclaredFields()) {

				if (field.isAnnotationPresent(Column.class)

						|| field.isAnnotationPresent(ManyToOne.class)){

					String alias = subClass.getSimpleName() + "." + field.getName();

					getColumns().add(

							new developen.common.persistence.query.Column(

									field.getName(), 

									subClass, 

									alias));

				} else {

					if (field.isAnnotationPresent(Identifier.class)){

						Class<?> classOfIdentifier = field.getType();

						if (classOfIdentifier.isAnnotationPresent(Embeddable.class)){

							for (Field fieldOfEmbeddable : classOfIdentifier.getDeclaredFields()) {

								if (fieldOfEmbeddable.isAnnotationPresent(Column.class)){

									String alias = subClass.getSimpleName() + "." + fieldOfEmbeddable.getName();

									getColumns().add(

											new developen.common.persistence.query.Column(

													fieldOfEmbeddable.getName(), 

													subClass, 

													alias));

								} else {

									if (fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

										Class<?> myClass = fieldOfEmbeddable.getType();

										Class<?> myClassIdentifier = null;

										while(myClass.isAnnotationPresent(Table.class)){

											for(Field myField : myClass.getDeclaredFields())

												if (myField.isAnnotationPresent(Identifier.class))

													myClassIdentifier = myField.getType();

											myClass = myClass.getSuperclass();

										}

										if (myClassIdentifier.isAnnotationPresent(Embeddable.class)){

											for(Field myField : myClassIdentifier.getDeclaredFields()){

												if (myField.isAnnotationPresent(Column.class) 

														|| myField.isAnnotationPresent(ManyToOne.class)) {

													String alias = subClass.getSimpleName() + "." + 

															fieldOfEmbeddable.getName() + 

															"_" + myField.getName();

													getColumns().add(

															new developen.common.persistence.query.Column(

																	fieldOfEmbeddable.getName() + "_" + myField.getName(),

																	subClass,

																	alias));

												}

											}

										} else {

											String alias = subClass.getSimpleName() + "." + fieldOfEmbeddable.getName();

											getColumns().add(

													new developen.common.persistence.query.Column(

															fieldOfEmbeddable.getName(), 

															subClass, 

															alias));

										}

									}

								}

							}

						} else {

							String alias = subClass.getSimpleName() + "." + field.getName();

							getColumns().add(

									new developen.common.persistence.query.Column(

											field.getName(), 

											subClass, 

											alias));

						}

					}

				}

			}

			if (lastClass!=null)

				getInnerJoins().add(new InnerJoin(subClass, lastClass));

			lastClass = subClass;

			subClass = subClass.getSuperclass();

		}


	}


	public List<developen.common.persistence.query.Column> getColumns() {


		if (columns==null)

			columns = new ArrayList<developen.common.persistence.query.Column>();

		return columns;


	}


	public void setColumns(List<developen.common.persistence.query.Column> columns) {

		this.columns = columns;

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


	public ColumnQuery add(final Condition condition){


		condition.setQuery(this);

		getWhere().add(condition);

		return this;


	}


	public ColumnQuery add(Sort sort){


		sort.setQuery(this);

		getOrderBy().add(sort);

		return this;


	}


	public String toString(){


		String sql = "SELECT ";

		for (Iterator<developen.common.persistence.query.Column> i = getColumns().iterator(); i.hasNext();) {

			developen.common.persistence.query.Column column = i.next();

			sql += column.toString() + (i.hasNext() ? ", " : " FROM " + '"' + getFrom().getSimpleName() + '"');

		}

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

		sql += !getOrderBy().isEmpty() ? " ORDER BY " : "";

		for (Iterator<Sort> i = getOrderBy().iterator(); i.hasNext();) {

			Sort sort = i.next();

			sql += sort.toString() + (i.hasNext() ? ", " : "");

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