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


public class InnerJoin implements Serializable {


	private static final long serialVersionUID = -6869480208269339220L;

	private Class<?> join;

	private Class<?> from;

	private List<Condition> conditions;


	public InnerJoin(Class<?> join, Class<?> from){


		this.join = join;

		this.from = from;


		for (Field field : this.join.getDeclaredFields()) {

			if (field.isAnnotationPresent(Identifier.class)){

				Class<?> classOfEmbeddable = field.getType();

				if (classOfEmbeddable.isAnnotationPresent(Embeddable.class)){

					for (Field fieldOfEmbeddable : classOfEmbeddable.getDeclaredFields()) {

						if (fieldOfEmbeddable.isAnnotationPresent(Column.class)

								|| fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

							EqualColumn equal = new EqualColumn(

									new developen.common.persistence.query.Column(
											
											fieldOfEmbeddable.getName(), 
											
											join),

									new developen.common.persistence.query.Column(
											
											fieldOfEmbeddable.getName(), 
											
											from));

							getConditions().add(equal);

						}						

					}

				} else {

					EqualColumn equal = new EqualColumn(

							new developen.common.persistence.query.Column(
									
									field.getName(), 
									
									join),

							new developen.common.persistence.query.Column(
									
									join.getSimpleName().substring(0, 1).toLowerCase() +
							
									join.getSimpleName().substring(1), 
									
									from));

					getConditions().add(equal);

				}

			}

		}

		if (getConditions().isEmpty()){

			EqualColumn equal = new EqualColumn(

					new developen.common.persistence.query.Column(
							
							join.getSuperclass().getSimpleName().substring(0, 1).toLowerCase() + 
							
							join.getSuperclass().getSimpleName().substring(1), 
							
							join),

					new developen.common.persistence.query.Column(
							
							from.getSuperclass().getSimpleName().substring(0, 1).toLowerCase() + 
							
							from.getSuperclass().getSimpleName().substring(1), 
							
							from));

			getConditions().add(equal);

		}


	}


	public List<Condition> getConditions() {

		
		if (conditions==null)

			conditions = new ArrayList<Condition>();

		return conditions;

		
	}


	public void setConditions(List<Condition> conditions) {

		this.conditions = conditions;

	}


	public String toString(){

		
		String sql = " INNER JOIN " + '"' + getJoin().getSimpleName() + '"' + " ON ";

		for (Iterator<Condition> iterator = getConditions().iterator(); iterator.hasNext();) {

			Condition condition = iterator.next();

			sql += condition.toString() + (iterator.hasNext() ? " AND " : "");

		}

		return sql;

		
	}


	public Class<?> getJoin() {

		return join;

	}


	public void setJoin(Class<?> join) {

		this.join = join;

	}


	public Class<?> getFrom() {

		return from;

	}


	public void setFrom(Class<?> from) {

		this.from = from;

	}

	
}