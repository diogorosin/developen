package developen.common.persistence.session;

import java.lang.reflect.Field;

import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Query;


public class SessionUtils {


	public static Object getIdentifier(Object object) throws Exception{


		Class<?> subClass = object.getClass();


		while(subClass.isAnnotationPresent(Table.class)) {

			Field[] fields = subClass.getDeclaredFields();

			for (Field field : fields) {

				if (field.isAnnotationPresent(Identifier.class)){

					field.setAccessible(true);

					return field.get(object);

				}

			}

			subClass = subClass.getSuperclass();

		}

		return null;


	}


	public static void setIdentifier(final Object object, Object identifier) throws Exception{


		Class<?> subClass = object.getClass();


		while(subClass.isAnnotationPresent(Table.class)) {

			Field[] fields = subClass.getDeclaredFields();

			for (Field field : fields){

				if (field.isAnnotationPresent(Identifier.class)){

					field.setAccessible(true);

					field.set(object, identifier);

					return;

				}

			}

			subClass = subClass.getSuperclass();

		}


	}


	public static Query buildQuery(Class<?> from, Object identifier, String mappedBy) throws Exception{


		if (identifier==null)

			return null;

		ColumnQuery query = new ColumnQuery(from);

		Class<?> subClass = from;

		for (Field field : subClass.getDeclaredFields()){

			if (field.isAnnotationPresent(Identifier.class)){

				field.setAccessible(true);

				Class<?> identifierClass = field.getType();

				if (identifierClass.isAnnotationPresent(Embeddable.class)){

					Field mappedByField = identifierClass.getDeclaredField(mappedBy);

					Class<?> mappedByFieldType = mappedByField.getType();

					while(mappedByFieldType.isAnnotationPresent(Table.class)){

						for (Field myField : mappedByFieldType.getDeclaredFields()){

							if (myField.isAnnotationPresent(Identifier.class)){

								Class<?> myType = myField.getType();

								if (myType.isAnnotationPresent(Embeddable.class)){

									for (Field fieldOfMyType : myType.getDeclaredFields()){

										fieldOfMyType.setAccessible(true);

										if (fieldOfMyType.isAnnotationPresent(Column.class)){

											query.add(new Equal(

													new developen.common.persistence.query.Column(

															mappedByField.getName() + "_" + fieldOfMyType.getName(), 

															subClass), 

															fieldOfMyType.get(identifier)));

										} else {

											if (fieldOfMyType.isAnnotationPresent(ManyToOne.class)){

												query.add(new Equal(

														new developen.common.persistence.query.Column(

																mappedByField.getName() + "_" + fieldOfMyType.getName(), 

																subClass), 

																SessionUtils.getIdentifier(fieldOfMyType.get(identifier))));

											}

										}

									}

								} else {

									query.add(new Equal(

											new developen.common.persistence.query.Column(

													mappedByField.getName(), 

													subClass), 

													identifier));

								}

							}

						}

						mappedByFieldType = mappedByFieldType.getSuperclass();

					}

				} 

			}

		}

		return query;

	}


}