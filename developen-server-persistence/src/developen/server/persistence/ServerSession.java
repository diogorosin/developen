package developen.server.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import developen.common.persistence.annotation.CascadeType;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.FetchType;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.OneToOne;
import developen.common.persistence.annotation.Table;
import developen.common.persistence.annotation.View;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Condition;
import developen.common.persistence.query.CountQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Param;
import developen.common.persistence.query.Parameterized;
import developen.common.persistence.query.Query;
import developen.common.persistence.session.Session;
import developen.common.persistence.session.SessionUtils;
import developen.common.persistence.type.ProxyList;


public class ServerSession implements Session{


	private Connection connection;


	public ServerSession(Connection connection){

		setConnection(connection);

	}


	public void close() throws Exception{

		getConnection().close();

	}


	public void delete(Object object) throws Exception {


		try{

			getConnection().setAutoCommit(false);

			delete2(object);

			getConnection().commit();

		} catch (Exception e) {

			getConnection().rollback();

			throw e;

		} finally {

			getConnection().setAutoCommit(true);

		}


	}


	private void delete2(Object object) throws Exception {


		int size = 0;

		Object[] subObjects = new Object[size];

		Object subObject = object.getClass().newInstance();


		while(subObject.getClass().isAnnotationPresent(Table.class)) {

			for (Field field : subObject.getClass().getDeclaredFields()) {

				if (field.isAnnotationPresent(Column.class)

						|| field.isAnnotationPresent(Identifier.class)

						|| field.isAnnotationPresent(ManyToOne.class)

						|| field.isAnnotationPresent(OneToMany.class)

						|| field.isAnnotationPresent(OneToOne.class)){

					field.setAccessible(true);

					field.set(subObject, field.get(object));

				}

			}

			subObjects = Arrays.copyOf(subObjects, size + 1);

			subObjects[size++] = subObject;

			Class<?> superClass = subObject.getClass().getSuperclass();

			if (Modifier.isAbstract(superClass.getModifiers()))

				break;

			else

				subObject = superClass.newInstance();

		}

		for (int i = 0; i < subObjects.length; i++)

			delete3(object, subObjects[i]);


	}


	private void delete3(Object object, Object subObject) throws Exception{


		List<Field> manyToOneFields = new ArrayList<Field>();

		Field identifierField = null;


		for (Field field : subObject.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			if (field.isAnnotationPresent(Identifier.class)){

				identifierField = field;

			} else {

				if (field.isAnnotationPresent(OneToOne.class)){

					List<CascadeType> cascadeList = Arrays.asList(field.

							getAnnotation(OneToOne.class).cascade());

					if (cascadeList.contains(CascadeType.ALL) || 

							cascadeList.contains(CascadeType.DELETE))

						delete2(field.get(subObject));

				} else {

					if (field.isAnnotationPresent(OneToMany.class)){

						List<CascadeType> cascadeList = Arrays.asList(field.

								getAnnotation(OneToMany.class).cascade());

						if (cascadeList.contains(CascadeType.ALL) || 

								cascadeList.contains(CascadeType.DELETE)) {

							Type myType = field.getGenericType();

							if (myType instanceof ParameterizedType){

								ParameterizedType pt = (ParameterizedType) myType;

								Type typeArgument = pt.getActualTypeArguments()[0];

								Class<?> from = (Class<?>) typeArgument;

								Query query = SessionUtils.buildQuery(

										from, 

										SessionUtils.getIdentifier(object), 

										field.getAnnotation(OneToMany.class).mappedBy()

										);

								List<Object> list = list(query);

								for (Object value : list)

									delete2(value);

							}

						}

					} else {

						if (field.isAnnotationPresent(ManyToOne.class)){

							List<CascadeType> cascadeList = Arrays.asList(field.

									getAnnotation(ManyToOne.class).cascade());

							if (cascadeList.contains(CascadeType.ALL) || 

									cascadeList.contains(CascadeType.DELETE))

								manyToOneFields.add(field);

						}

					}

				}

			}

		}

		HashMap<String, Object> columns = new HashMap<String, Object>();

		Object identifierValue = identifierField == null ? SessionUtils.getIdentifier(object) : identifierField.get(subObject);

		if (identifierValue.getClass().isAnnotationPresent(Embeddable.class)){

			for (Field fieldOfEmbeddable : identifierValue.getClass().getDeclaredFields()) {

				fieldOfEmbeddable.setAccessible(true);

				if (fieldOfEmbeddable.isAnnotationPresent(Column.class)){

					Object value = fieldOfEmbeddable.get(identifierValue);

					if (value instanceof Date)

						value = new java.sql.Date(((Date) value).getTime());

					columns.put(fieldOfEmbeddable.getName(), value);

				} else {

					if (fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

						Object value = SessionUtils.getIdentifier(fieldOfEmbeddable.get(identifierValue));

						if (value.getClass().isAnnotationPresent(Embeddable.class)){

							for (Field myField : value.getClass().getDeclaredFields()){

								myField.setAccessible(true);

								if (myField.isAnnotationPresent(Column.class)){

									columns.put(fieldOfEmbeddable.getName() + "_" + myField.getName(), myField.get(value));

								} else {

									if (myField.isAnnotationPresent(ManyToOne.class)){

										columns.put(fieldOfEmbeddable.getName() + "_" + myField.getName(), 

												SessionUtils.getIdentifier(myField.get(value)));

									}										

								}

							}

						} else {

							if (value instanceof Date)

								value = new java.sql.Date(((Date) value).getTime());

							columns.put(fieldOfEmbeddable.getName(), value);

						}

					}

				}

			}

		} else {

			String columnName = identifierField == null 

					? subObject.getClass().getSuperclass().getSimpleName().substring(0, 1).toLowerCase() + 

							subObject.getClass().getSuperclass().getSimpleName().substring(1)	

							: identifierField.getName(); 

							if (identifierValue instanceof Date)

								identifierValue = new java.sql.Date(((Date) identifierValue).getTime());

							columns.put(columnName, identifierValue);

		}

		String statement = "DELETE FROM " + '"' + subObject.getClass().getSimpleName() + '"' + " WHERE ";

		String parameter = "";

		for (Iterator<String> i = columns.keySet().iterator(); i.hasNext();) {

			String key = i.next();

			parameter += '"' + key + '"' + " = ?" + (i.hasNext() ? " AND " : "");

		}

		statement += parameter;

		System.out.println(statement);

		PreparedStatement preparedStatement = getConnection().prepareStatement(statement);

		int x = 1;

		for (String key : columns.keySet()) 

			preparedStatement.setObject(x++, columns.get(key));

		preparedStatement.executeUpdate();

		preparedStatement.close();

		for (Field field : manyToOneFields)

			delete2(field.get(subObject));


	}


	public void update(final Object object) throws Exception {


		try{

			getConnection().setAutoCommit(false);

			update2(object);

			getConnection().commit();

		} catch (Exception e) {

			getConnection().rollback();

			throw e;

		} finally {

			getConnection().setAutoCommit(true);

		}


	} 


	private void update2(final Object object) throws Exception {


		int size = 0;

		Object[] subObjects = new Object[size];

		Object subObject = object.getClass().newInstance();


		while(subObject.getClass().isAnnotationPresent(Table.class)) {

			Field[] fields = subObject.getClass().getDeclaredFields();

			for (Field field : fields) {

				if (field.isAnnotationPresent(Column.class)

						|| field.isAnnotationPresent(Identifier.class)

						|| field.isAnnotationPresent(ManyToOne.class)

						|| field.isAnnotationPresent(OneToMany.class)

						|| field.isAnnotationPresent(OneToOne.class)){

					field.setAccessible(true);

					field.set(subObject, field.get(object));

				}

			}

			subObjects = Arrays.copyOf(subObjects, size + 1);

			subObjects[size++] = subObject;

			Class<?> superClass = subObject.getClass().getSuperclass();

			if (Modifier.isAbstract(superClass.getModifiers()))

				break;

			else

				subObject = superClass.newInstance();

		}

		for (int i = subObjects.length-1; i >= 0; i--)

			update3(object, subObjects[i]);


	}


	@SuppressWarnings("unchecked")
	private void update3(final Object object, Object subObject) throws Exception{


		HashMap<String, Object> columns = new HashMap<String, Object>();

		List<Field> oneToOneFields = new ArrayList<Field>();

		List<Field> oneToManyFields = new ArrayList<Field>();

		Field fieldOfIdentifier = null;


		for (Field field : subObject.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			if (field.isAnnotationPresent(Column.class)) {

				Object value = field.get(subObject);

				if (value instanceof Date)

					value = new java.sql.Date(((Date) value).getTime());

				columns.put(field.getName(), value);

			} else {

				if (field.isAnnotationPresent(Identifier.class)){

					fieldOfIdentifier = field;

				} else {

					if (field.isAnnotationPresent(ManyToOne.class)){

						Object value = field.get(subObject);

						if (value != null){

							List<CascadeType> cascadeList = Arrays.asList(field.

									getAnnotation(ManyToOne.class).cascade());

							if (cascadeList.contains(CascadeType.ALL))

								createOrUpdate2(value);

							else {

								boolean createdNow = false;

								if (cascadeList.contains(CascadeType.CREATE)) {

									if (!exist(value.getClass(), SessionUtils.getIdentifier(value))) {

										create2(value);

										createdNow = true;

									}

								}

								if (!createdNow)

									if (cascadeList.contains(CascadeType.UPDATE))

										update2(value);

							}

							value = SessionUtils.getIdentifier(value);

							if (value instanceof Date)

								value = new java.sql.Date(((Date) value).getTime());

							columns.put(field.getName(), value);

						}

					} else {

						if (field.isAnnotationPresent(OneToOne.class)){

							oneToOneFields.add(field);

						} else {

							if (field.isAnnotationPresent(OneToMany.class)){

								oneToManyFields.add(field);

							}						

						}

					}

				}

			}

		}

		if (!columns.isEmpty()) {

			String statement = "UPDATE " + '"' + subObject.getClass().getSimpleName() + '"' + " SET ";

			for (Iterator<String> i = columns.keySet().iterator(); i.hasNext();) {

				String key = i.next();

				statement += '"' + key + '"' + " = ?" + (i.hasNext() ? ", " : "");

			}

			HashMap<String, Object> fieldsOfIdentifier = new HashMap<String, Object>();

			Object valueOfIdentifier = fieldOfIdentifier == null ? SessionUtils.getIdentifier(object) : fieldOfIdentifier.get(subObject);

			if (valueOfIdentifier.getClass().isAnnotationPresent(Embeddable.class)){

				for (Field fieldOfEmbeddable : valueOfIdentifier.getClass().getDeclaredFields()) {

					fieldOfEmbeddable.setAccessible(true);

					Object value = fieldOfEmbeddable.get(valueOfIdentifier);

					if (fieldOfEmbeddable.isAnnotationPresent(Column.class)){

						if (value instanceof Date)

							value = new java.sql.Date(((Date) value).getTime());

						fieldsOfIdentifier.put(fieldOfEmbeddable.getName(), value);

					} else {

						if (fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

							Object valueOfIdentifierOfManyToOne = SessionUtils.getIdentifier(value);

							if (valueOfIdentifierOfManyToOne.getClass().isAnnotationPresent(Embeddable.class)){

								for(Field myField : valueOfIdentifierOfManyToOne.getClass().getDeclaredFields()){

									myField.setAccessible(true);

									if (myField.isAnnotationPresent(Column.class)){

										Object myValue = myField.get(valueOfIdentifierOfManyToOne);

										fieldsOfIdentifier.put(fieldOfEmbeddable.getName() + "_" + myField.getName(), myValue);

									} else {

										if (myField.isAnnotationPresent(ManyToOne.class)){

											Object myValue = SessionUtils.getIdentifier(myField.get(valueOfIdentifierOfManyToOne));

											fieldsOfIdentifier.put(fieldOfEmbeddable.getName() + "_" + myField.getName(), myValue);	

										}

									}

								}

							} else {

								if (valueOfIdentifierOfManyToOne instanceof Date)

									valueOfIdentifierOfManyToOne = new java.sql.Date(((Date) valueOfIdentifierOfManyToOne).getTime());

								fieldsOfIdentifier.put(fieldOfEmbeddable.getName(), valueOfIdentifierOfManyToOne);

							}

						}

					}

				}

			} else {

				if (valueOfIdentifier instanceof Date)

					valueOfIdentifier = new java.sql.Date(((Date) valueOfIdentifier).getTime());

				if (fieldOfIdentifier == null)

					fieldsOfIdentifier.put(subObject.getClass().getSuperclass().getSimpleName().substring(0,1).toLowerCase() +

							subObject.getClass().getSuperclass().getSimpleName().substring(1)

							, valueOfIdentifier);

				else

					fieldsOfIdentifier.put(fieldOfIdentifier.getName(), valueOfIdentifier);

			}

			String parameter = " WHERE ";

			for (Iterator<String> i = fieldsOfIdentifier.keySet().iterator(); i.hasNext();) {

				String key = i.next();

				parameter += '"' + key + '"' + " = ?" + (i.hasNext() ? " AND " : "");

			}

			statement += parameter;

			System.out.println(statement);

			PreparedStatement preparedStatement = getConnection().prepareStatement(statement);

			int x = 1;

			for (String key : columns.keySet()) 

				preparedStatement.setObject(x++, columns.get(key));

			for (String key : fieldsOfIdentifier.keySet()) 

				preparedStatement.setObject(x++, fieldsOfIdentifier.get(key));

			preparedStatement.executeUpdate();

			preparedStatement.close();

		}

		for (Field field : oneToOneFields) {

			field.setAccessible(true);

			Object oneToOne = field.get(subObject);

			if (oneToOne != null){

				SessionUtils.setIdentifier(oneToOne, SessionUtils.getIdentifier(object));

				List<CascadeType> cascadeList = Arrays.asList(field.

						getAnnotation(OneToOne.class).cascade());

				if (cascadeList.contains(CascadeType.ALL))

					createOrUpdate2(oneToOne);

				else {

					boolean createdNow = false;

					if (cascadeList.contains(CascadeType.CREATE)) {

						if (!exist(oneToOne.getClass(), SessionUtils.getIdentifier(oneToOne))) {

							create2(oneToOne);

							createdNow = true;

						}

					}

					if (!createdNow)

						if (cascadeList.contains(CascadeType.UPDATE))

							update2(oneToOne);

				}

			}

		}

		for (Field field : oneToManyFields){

			field.setAccessible(true);

			List<CascadeType> cascadeList = Arrays.asList(field.getAnnotation(OneToMany.class).cascade());

			if (cascadeList.contains(CascadeType.ALL)) {

				List<Object> oneToMany = (List<Object>) field.get(subObject);

				for (Object onMemory : oneToMany)

					createOrUpdate2(onMemory);

				Type myType = field.getGenericType();

				if (myType instanceof ParameterizedType){

					ParameterizedType pt = (ParameterizedType) myType;

					Type typeArgument = pt.getActualTypeArguments()[0];

					Class<?> from = (Class<?>) typeArgument;

					Query query = SessionUtils.buildQuery(

							from, 

							SessionUtils.getIdentifier(object), 

							field.getAnnotation(OneToMany.class).mappedBy()

							);

					List<Object> list = list(query);

					for (Object onDatabase : list) {

						boolean finded = false;

						for (Object onMemory : oneToMany){

							finded =  onMemory.hashCode()==onDatabase.hashCode();

							if (finded)

								break;

						}

						if (!finded)

							delete2(onDatabase);

					}

				}

			} else {

				List<Object> oneToMany = (List<Object>) field.get(subObject);

				for (Object row : oneToMany){

					boolean createdNow = false;

					if (cascadeList.contains(CascadeType.CREATE)) {

						if (!exist(row.getClass(), SessionUtils.getIdentifier(row))) {

							create2(row);

							createdNow = true;

						}

					}

					if (!createdNow)

						if (cascadeList.contains(CascadeType.UPDATE))

							update2(row);

				}

				if (cascadeList.contains(CascadeType.DELETE)){

					Type myType = field.getGenericType();

					if (myType instanceof ParameterizedType){

						ParameterizedType pt = (ParameterizedType) myType;

						Type typeArgument = pt.getActualTypeArguments()[0];

						Class<?> from = (Class<?>) typeArgument;

						Query query = SessionUtils.buildQuery(

								from, 

								SessionUtils.getIdentifier(object), 

								field.getAnnotation(OneToMany.class).mappedBy()

								);

						List<Object> list = list(query);

						for (Object onDatabase : list) {

							boolean finded = false;

							for (Object onMemory : oneToMany){

								finded =  onMemory.hashCode()==onDatabase.hashCode();

								if (finded)

									break;

							}

							if (!finded)

								delete2(onDatabase);

						}

					}

				}

			}

		}


	}


	public void create(final Object object) throws Exception {


		try{

			getConnection().setAutoCommit(false);

			create2(object);

			getConnection().commit();

		} catch (Exception e) {

			getConnection().rollback();

			throw e;

		} finally {

			getConnection().setAutoCommit(true);

		}


	}


	private void create2(final Object object) throws Exception {


		int size = 0;

		Object[] subObjects = new Object[size];

		Object subObject = object.getClass().newInstance();


		while(subObject.getClass().isAnnotationPresent(Table.class)) {

			for (Field field : subObject.getClass().getDeclaredFields()) {

				if (field.isAnnotationPresent(Column.class)

						|| field.isAnnotationPresent(Identifier.class)

						|| field.isAnnotationPresent(ManyToOne.class)

						|| field.isAnnotationPresent(OneToMany.class)

						|| field.isAnnotationPresent(OneToOne.class)){

					field.setAccessible(true);

					field.set(subObject, field.get(object));

				}

			}

			subObjects = Arrays.copyOf(subObjects, size + 1);

			subObjects[size++] = subObject;

			Class<?> superClass = subObject.getClass().getSuperclass();

			if (Modifier.isAbstract(superClass.getModifiers()))

				break;

			else

				subObject = superClass.newInstance();

		}

		for (int i = subObjects.length-1; i >= 0; i--)

			create3(object, subObjects[i]);


	}


	@SuppressWarnings("unchecked")
	private void create3(final Object object, Object subObject) throws Exception{


		HashMap<String, Object> columns = new HashMap<String, Object>();

		List<Field> oneToOneFields = new ArrayList<Field>();

		List<Field> oneToManyFields = new ArrayList<Field>();

		Field fieldOfIdentifier = null;


		for (Field field : subObject.getClass().getDeclaredFields()) {

			if (field.isAnnotationPresent(Column.class)) {

				field.setAccessible(true);

				Object value = field.get(subObject);

				if (value instanceof Date)

					value = new java.sql.Date(((Date) value).getTime());

				columns.put(field.getName(), value);

			} else {

				if (field.isAnnotationPresent(Identifier.class)){

					field.setAccessible(true);

					fieldOfIdentifier = field;

					if (fieldOfIdentifier.getAnnotation(Identifier.class).sequence())

						continue;

					else {

						Object valueOfIdentifier = fieldOfIdentifier.get(subObject);

						if (valueOfIdentifier.getClass().isAnnotationPresent(Embeddable.class)){

							for (Field fieldOfEmbeddable : valueOfIdentifier.getClass().getDeclaredFields()) {

								fieldOfEmbeddable.setAccessible(true);

								Object value = fieldOfEmbeddable.get(valueOfIdentifier);

								if (fieldOfEmbeddable.isAnnotationPresent(Column.class)){

									if (value instanceof Date)

										value = new java.sql.Date(((Date) value).getTime());

									columns.put(fieldOfEmbeddable.getName(), value);

								} else {

									if (fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

										if (SessionUtils.getIdentifier(value) == null){

											List<CascadeType> cascadeList = Arrays.asList(fieldOfEmbeddable.

													getAnnotation(ManyToOne.class).cascade());

											if (cascadeList.contains(CascadeType.ALL) || 

													cascadeList.contains(CascadeType.CREATE))

												create2(value);

										}

										Object valueOfIdentifierOfManyToOne = SessionUtils.getIdentifier(value);

										if (valueOfIdentifierOfManyToOne.getClass().isAnnotationPresent(Embeddable.class)){

											for(Field myField : valueOfIdentifierOfManyToOne.getClass().getDeclaredFields()){

												myField.setAccessible(true);

												if (myField.isAnnotationPresent(Column.class)){

													Object myValue = myField.get(valueOfIdentifierOfManyToOne);

													if (myValue instanceof Date)

														myValue = new java.sql.Date(((Date) myValue).getTime());

													columns.put(fieldOfEmbeddable.getName() + "_" + myField.getName(), myValue);

												} else {

													if (myField.isAnnotationPresent(ManyToOne.class)){

														Object myValue = SessionUtils.getIdentifier(myField.get(valueOfIdentifierOfManyToOne));

														if (myValue instanceof Date)

															myValue = new java.sql.Date(((Date) myValue).getTime());

														columns.put(fieldOfEmbeddable.getName() + "_" + myField.getName(), myValue);

													}

												}

											}

										} else {

											if (valueOfIdentifierOfManyToOne instanceof Date)

												valueOfIdentifierOfManyToOne = new java.sql.Date(((Date) valueOfIdentifierOfManyToOne).getTime());

											columns.put(fieldOfEmbeddable.getName(), valueOfIdentifierOfManyToOne);

										}

									}

								}

							}

						} else {

							if (valueOfIdentifier instanceof Date)

								valueOfIdentifier = new java.sql.Date(((Date) valueOfIdentifier).getTime());

							columns.put(field.getName(), valueOfIdentifier);

						}

					}

				} else {

					if (field.isAnnotationPresent(ManyToOne.class)){

						field.setAccessible(true);

						Object value = field.get(subObject);

						if (value != null){

							List<CascadeType> cascadeList = Arrays.asList(field.

									getAnnotation(ManyToOne.class).cascade());

							if (cascadeList.contains(CascadeType.ALL))

								createOrUpdate2(value);

							else {

								boolean createdNow = false;

								if (cascadeList.contains(CascadeType.CREATE)) {

									if (!exist(value.getClass(), SessionUtils.getIdentifier(value))) {

										create2(value);

										createdNow = true;

									}

								}

								if (!createdNow)

									if (cascadeList.contains(CascadeType.UPDATE))

										update2(value);

							}

							value = SessionUtils.getIdentifier(value);

						}

						if (value instanceof Date)

							value = new java.sql.Date(((Date) value).getTime());

						columns.put(field.getName(), value);

					} else {

						if (field.isAnnotationPresent(OneToMany.class)){

							oneToManyFields.add(field);

						} else {

							if (field.isAnnotationPresent(OneToOne.class)){

								oneToOneFields.add(field);

							}

						}

					}

				}

			}

		}

		if (fieldOfIdentifier == null){

			Object valueOfIdentifier = SessionUtils.getIdentifier(object);

			if (valueOfIdentifier.getClass().isAnnotationPresent(Embeddable.class))

				for (Field fieldOfEmbeddable : valueOfIdentifier.getClass().getDeclaredFields()) {

					fieldOfEmbeddable.setAccessible(true);

					Object value = fieldOfEmbeddable.get(valueOfIdentifier);

					if (fieldOfEmbeddable.isAnnotationPresent(Column.class)){

						if (value instanceof Date)

							value = new java.sql.Date(((Date) value).getTime());

						columns.put(fieldOfEmbeddable.getName(), value);

					} else {

						if (fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

							Object identifierOfValue = SessionUtils.getIdentifier(value);

							if (identifierOfValue instanceof Date)

								identifierOfValue = new java.sql.Date(((Date) identifierOfValue).getTime());

							columns.put(fieldOfEmbeddable.getName(), identifierOfValue);

						}

					}

				}

			else {

				if (valueOfIdentifier instanceof Date)

					valueOfIdentifier = new java.sql.Date(((Date) valueOfIdentifier).getTime());

				columns.put(subObject.getClass().getSuperclass().getSimpleName().substring(0,1).toLowerCase() + 

						subObject.getClass().getSuperclass().getSimpleName().substring(1)

						, valueOfIdentifier);

			}

		}

		String statement = "INSERT INTO " + '"' + subObject.getClass().getSimpleName() + '"' + " (";

		String parameter = " VALUES (";

		for (Iterator<String> i = columns.keySet().iterator(); i.hasNext();) {

			String key = i.next();

			statement += '"' + key + '"' + (i.hasNext() ? ", " : ")");

			parameter += "?" + (i.hasNext() ? ", " : ")");

		}

		statement += parameter;

		System.out.println(statement);

		PreparedStatement preparedStatement;

		if (fieldOfIdentifier != null && fieldOfIdentifier.getAnnotation(Identifier.class).sequence())

			preparedStatement = getConnection().prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);

		else

			preparedStatement = getConnection().prepareStatement(statement);

		int x = 1;

		for (String key : columns.keySet())

			preparedStatement.setObject(x++, columns.get(key));

		preparedStatement.executeUpdate();

		if (fieldOfIdentifier != null && fieldOfIdentifier.getAnnotation(Identifier.class).sequence()){

			ResultSet rs = preparedStatement.getGeneratedKeys();

			rs.next();

			Object generatedIdentifier = rs.getLong(1);

			fieldOfIdentifier.set(object, generatedIdentifier);

		}

		preparedStatement.close();

		for (Field field : oneToOneFields) {

			field.setAccessible(true);

			Object oneToOne = field.get(subObject);

			if (oneToOne != null){

				SessionUtils.setIdentifier(oneToOne, SessionUtils.getIdentifier(object));

				List<CascadeType> cascadeList = Arrays.asList(field.

						getAnnotation(OneToOne.class).cascade());

				if (cascadeList.contains(CascadeType.ALL) || 

						cascadeList.contains(CascadeType.CREATE))

					create2(oneToOne);

			}

		}

		for (Field field : oneToManyFields) {

			field.setAccessible(true);

			Object oneToMany = field.get(subObject);

			if (oneToMany != null){

				List<CascadeType> cascadeList = Arrays.asList(field.

						getAnnotation(OneToMany.class).cascade());

				if (cascadeList.contains(CascadeType.ALL) || 

						cascadeList.contains(CascadeType.CREATE))

					for (Object value : ((List<Object>)oneToMany))

						create2(value);

			}

		}		


	}


	public void createOrUpdate(final Object object) throws Exception {


		try{

			getConnection().setAutoCommit(false);

			createOrUpdate2(object);

			getConnection().commit();

		} catch (Exception e) {

			getConnection().rollback();

			throw e;

		} finally {

			getConnection().setAutoCommit(true);

		}


	}


	private void createOrUpdate2(final Object object) throws Exception {


		if (exist(object.getClass(), SessionUtils.getIdentifier(object)))

			update2(object);

		else

			create2(object);


	}


	private boolean exist(Class<?> from, Object identifier) throws Exception{


		if (identifier==null)

			return false;

		CountQuery query = new CountQuery(from);

		Class<?> subClass = from;

		while(subClass.isAnnotationPresent(Table.class)){

			for (Field field : subClass.getDeclaredFields()){

				if (field.isAnnotationPresent(Identifier.class)){

					field.setAccessible(true);

					Class<?> identifierClass = field.getType();

					if (identifierClass.isAnnotationPresent(Embeddable.class)){

						for (Field fieldOfEmbeddable : identifierClass.getDeclaredFields()) {

							fieldOfEmbeddable.setAccessible(true);

							if (fieldOfEmbeddable.isAnnotationPresent(Column.class)){

								Object value = fieldOfEmbeddable.get(identifier);

								if (value==null)

									return false;

								if (value instanceof Date)

									value = new java.sql.Date(((Date) value).getTime());

								query.add(new Equal(

										new developen.common.persistence.query.Column(

												fieldOfEmbeddable.getName(), 

												subClass), 

												value));

							} else {

								if (fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

									Object myIdentifier = fieldOfEmbeddable.get(identifier);

									if (myIdentifier==null)

										return false;

									Object value = SessionUtils.getIdentifier(myIdentifier);

									if (value==null)

										return false;

									if (value.getClass().isAnnotationPresent(Embeddable.class)){

										for(Field myField : value.getClass().getDeclaredFields()){

											myField.setAccessible(true);

											if (myField.isAnnotationPresent(Column.class)){

												Object myValue = myField.get(value);

												if (myValue==null)

													return false;

												if (myValue instanceof Date)

													myValue = new java.sql.Date(((Date) myValue).getTime());

												query.add(new Equal(

														new developen.common.persistence.query.Column(

																fieldOfEmbeddable.getName() + "_" + myField.getName(), 

																subClass),

																myValue));

											} else {

												if (myField.isAnnotationPresent(ManyToOne.class)){

													Object mySubIdentifier = myField.get(value);

													if (mySubIdentifier==null)

														return false;

													Object myValue = SessionUtils.getIdentifier(mySubIdentifier);

													if (myValue==null)

														return false;

													if (myValue instanceof Date)

														myValue = new java.sql.Date(((Date) myValue).getTime());

													query.add(new Equal(

															new developen.common.persistence.query.Column(

																	fieldOfEmbeddable.getName() + "_" + myField.getName(), 

																	subClass),

																	myValue));

												}

											}

										}

									} else {

										if (value instanceof Date)

											value = new java.sql.Date(((Date) value).getTime());

										query.add(new Equal(

												new developen.common.persistence.query.Column(

														fieldOfEmbeddable.getName(), 

														subClass), 

														value));

									}

								}

							}

						}

					} else {

						query.add(new Equal(

								new developen.common.persistence.query.Column(

										field.getName(), 

										subClass), 

										identifier));

					}

				}

			}

			subClass = subClass.getSuperclass();

		}

		int count = 0;

		System.out.println(query.toString());

		PreparedStatement preparedStatement = getConnection().prepareStatement(query.toString());

		for (Condition condition : query.getWhere())

			if (condition instanceof Parameterized)

				for (Param param : ((Parameterized) condition).getParams())

					preparedStatement.setObject(param.getIndex(), param.getValue());

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next())

			count = resultSet.getInt("COUNT");

		return count > 0;


	}


	public Object read(Class<?> from, Object identifier) throws Exception {


		if (identifier==null)

			return null;

		ColumnQuery query = new ColumnQuery(from);

		Class<?> subClass = from;

		while(subClass.isAnnotationPresent(Table.class)){

			for (Field field : subClass.getDeclaredFields()){

				if (field.isAnnotationPresent(Identifier.class)){

					field.setAccessible(true);

					Class<?> identifierClass = field.getType();

					if (identifierClass.isAnnotationPresent(Embeddable.class)){

						for (Field fieldOfEmbeddable : identifierClass.getDeclaredFields()) {

							fieldOfEmbeddable.setAccessible(true);

							if (fieldOfEmbeddable.isAnnotationPresent(Column.class)){

								Object identifierValue = fieldOfEmbeddable.get(identifier);

								if (identifierValue instanceof Date)

									identifierValue = new java.sql.Date(((Date) identifierValue).getTime());

								query.add(new Equal(

										new developen.common.persistence.query.Column(

												fieldOfEmbeddable.getName(), 

												subClass), 

												identifierValue));

							} else {

								if (fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

									Object value = SessionUtils.getIdentifier(fieldOfEmbeddable.get(identifier));

									if (value.getClass().isAnnotationPresent(Embeddable.class)){

										for(Field myField : value.getClass().getDeclaredFields()){

											myField.setAccessible(true);

											if (myField.isAnnotationPresent(Column.class)){

												Object myValue = myField.get(value);

												if (myValue instanceof Date)

													myValue = new java.sql.Date(((Date) myValue).getTime());

												query.add(new Equal(

														new developen.common.persistence.query.Column(

																fieldOfEmbeddable.getName() + "_" + myField.getName(), 

																subClass), 

																myValue));

											} else {

												if (myField.isAnnotationPresent(ManyToOne.class)){

													Object myValue = SessionUtils.getIdentifier(myField.get(value));

													if (myValue instanceof Date)

														myValue = new java.sql.Date(((Date) myValue).getTime());

													query.add(new Equal(

															new developen.common.persistence.query.Column(

																	fieldOfEmbeddable.getName() + "_" + myField.getName(), 

																	subClass), 

																	myValue));

												}

											}

										}

									} else {

										if (value instanceof Date)

											value = new java.sql.Date(((Date) value).getTime());

										query.add(new Equal(

												new developen.common.persistence.query.Column(

														fieldOfEmbeddable.getName(), 

														subClass), 

														value));

									}

								}

							}

						}

					} else {

						query.add(new Equal(

								new developen.common.persistence.query.Column(

										field.getName(), 

										subClass), 

										identifier));

					}

				}

			}

			subClass = subClass.getSuperclass();

		}

		Object object = null; 

		System.out.println(query.toString());

		PreparedStatement preparedStatement = getConnection().

				prepareStatement(

						query.toString()

						);

		for (Condition condition : query.getWhere())

			if (condition instanceof Parameterized)

				for (Param param : ((Parameterized) condition).getParams())

					preparedStatement.setObject(param.getIndex(), param.getValue());

		ResultSet resultSet = preparedStatement.executeQuery();

		Transformer transformer = new Transformer(resultSet, from, this);

		object = transformer.unique();

		resultSet.close();

		preparedStatement.close();

		return object;


	}


	public List<Object> list(Class<?> from) throws Exception{


		List<Object> list = new ArrayList<Object>();

		String statement = new ColumnQuery(from).toString();

		System.out.println(statement);

		PreparedStatement preparedStatement = getConnection().

				prepareStatement(statement);

		ResultSet resultSet = preparedStatement.executeQuery();

		Transformer t = new Transformer(resultSet, from, this);

		list = t.list();

		resultSet.close();

		preparedStatement.close();

		return list;


	}


	public List<Object> list(Query query) throws Exception {


		List<Object> list = new ArrayList<Object>();


		String statement = query.toString();

		System.out.println(statement);

		PreparedStatement preparedStatement = getConnection().

				prepareStatement(statement);

		for (Condition condition : query.getWhere())

			if (condition instanceof Parameterized)

				for (Param param : ((Parameterized) condition).getParams())

					preparedStatement.setObject(param.getIndex(), param.getValue());

		ResultSet resultSet = preparedStatement.executeQuery();

		Transformer t = new Transformer(resultSet, query.getFrom(), this);

		list = t.list();

		resultSet.close();

		preparedStatement.close();

		return list;


	}


	private Connection getConnection() {

		return connection;

	}


	private void setConnection(Connection connection) {

		this.connection = connection;

	}


	private class Transformer {


		private ResultSet resultSet;

		private Class<?> from;

		private Session session;


		public Transformer(ResultSet resultSet, Class<?> from, Session session){


			setResultSet(resultSet);

			setFrom(from);

			setSession(session);


		}


		public ResultSet getResultSet() {

			return resultSet;

		}


		public void setResultSet(ResultSet resultSet) {

			this.resultSet = resultSet;

		}


		public Class<?> getFrom() {

			return from;

		}


		public void setFrom(Class<?> from) {

			this.from = from;

		}


		public Session getSession() {

			return session;

		}


		public void setSession(Session session) {

			this.session = session;

		}


		public Object unique() throws Exception{


			List<Object> list = list();

			if (list.size() > 0)

				return list.get(0);

			else

				return null;


		}


		public List<Object> list() throws Exception{


			List<Object> list = new ArrayList<Object>();


			while (getResultSet().next()) {

				List<Field> oneToOneFields = new ArrayList<Field>();

				List<Field> oneToManyFields = new ArrayList<Field>();

				Object row = getFrom().newInstance();

				Class<?> subClass = getFrom();

				while(subClass.isAnnotationPresent(Table.class)

						|| subClass.isAnnotationPresent(View.class)) {

					for (Field field : subClass.getDeclaredFields()) {

						field.setAccessible(true);

						if (field.isAnnotationPresent(Column.class)){

							field.set(row, readColumn(subClass, field));

						} else {

							if (field.isAnnotationPresent(Identifier.class)){

								if (field.getType().isAnnotationPresent(Embeddable.class)){

									Object identifierValue = field.getType().newInstance();

									for (Field fieldOfEmbeddable : identifierValue.getClass().getDeclaredFields()) {

										fieldOfEmbeddable.setAccessible(true);

										if (fieldOfEmbeddable.isAnnotationPresent(Column.class)){

											fieldOfEmbeddable.set(identifierValue, 

													readColumn(subClass, fieldOfEmbeddable));

										} else {

											if (fieldOfEmbeddable.isAnnotationPresent(ManyToOne.class)){

												Class<?> myClass = fieldOfEmbeddable.getType();  

												Class<?> myClassIdentifier = null;

												while(myClass.isAnnotationPresent(Table.class)){

													for(Field myField : myClass.getDeclaredFields())

														if (myField.isAnnotationPresent(Identifier.class)){

															myClassIdentifier = myField.getType();  

															break;

														}

													if (myClassIdentifier != null)

														break;

													myClass = myClass.getSuperclass();

												}

												if (myClassIdentifier.isAnnotationPresent(Embeddable.class)){

													Object myValue = myClassIdentifier.newInstance();

													for(Field myField : myClassIdentifier.getDeclaredFields()){

														myField.setAccessible(true);

														if (myField.isAnnotationPresent(Column.class)){

															myField.set(myValue, readColumn(subClass, 

																	fieldOfEmbeddable, myField));

														} else {

															if (myField.isAnnotationPresent(ManyToOne.class)){

																myField.set(myValue, getSession().read(

																		myField.getType(),

																		readColumn(

																				subClass, 

																				fieldOfEmbeddable, 

																				myField

																				)

																		));

															}

														}

													}

													fieldOfEmbeddable.set(identifierValue, 

															getSession().read(

																	fieldOfEmbeddable.getType(),

																	myValue));

												} else { 

													fieldOfEmbeddable.set(identifierValue, 

															getSession().read(

																	fieldOfEmbeddable.getType(),

																	readColumn(subClass, fieldOfEmbeddable)));

												}

											}

										}

									}

									field.set(row, identifierValue);

								} else {

									field.set(row, readColumn(subClass, field));

								}

							} else {

								if (field.isAnnotationPresent(ManyToOne.class)){

									Object myObject = getSession().read(

											field.getType(), 

											readColumn(subClass, field));

									field.set(row, myObject);

								} else {

									if (field.isAnnotationPresent(OneToMany.class)){

										oneToManyFields.add(field);

									} else {

										if (field.isAnnotationPresent(OneToOne.class)){

											oneToOneFields.add(field);

										}

									}

								}

							}

						}

					}

					subClass = subClass.getSuperclass();

				}

				for (Field field : oneToOneFields)

					field.set(row, 

							getSession().read(

									field.getType(), 

									SessionUtils.getIdentifier(row)));

				for (Field field : oneToManyFields) {

					Type myType = field.getGenericType();

					if (myType instanceof ParameterizedType){

						ParameterizedType pt = (ParameterizedType) myType;

						Type typeArgument = pt.getActualTypeArguments()[0];

						Class<?> from = (Class<?>) typeArgument;

						Query query = SessionUtils.buildQuery(

								from, 

								SessionUtils.getIdentifier(row), 

								field.getAnnotation(OneToMany.class).mappedBy()

								);

						List<Object> result = new ProxyList<Object>(query);

						if (field.getAnnotation(OneToMany.class).fetch().equals(FetchType.EAGER))

							result.size();

						field.set(row, result);

					}

				}

				list.add(row);

			}

			return list;


		}


		public Object readColumn(Class<?> subClass, Field field) throws SQLException{


			Object value = null;


			if (field.getType().equals(Long.class))

				value = getResultSet().getLong(subClass.getSimpleName() + "." + field.getName());

			else

				if (field.getType().equals(Integer.class))

					value = getResultSet().getInt(subClass.getSimpleName() + "." + field.getName());

				else

					if (field.getType().equals(Double.class))

						value = getResultSet().getDouble(subClass.getSimpleName() + "." + field.getName());

					else

						if (field.getType().equals(Date.class)){

							java.sql.Date myOldValue = getResultSet().getDate(subClass.getSimpleName() + "." + field.getName());

							if (myOldValue!=null)

								value = new Date(myOldValue.getTime());

						}else

							value = getResultSet().getObject(subClass.getSimpleName() + "." + field.getName());

			return value;


		}


		public Object readColumn(Class<?> subClass, Field field, Field subField) throws SQLException{


			Object value = null;


			if (subField.getType().equals(Long.class))

				value = getResultSet().getLong(subClass.getSimpleName() + "." + field.getName() + "_" + subField.getName());

			else

				if (subField.getType().equals(Integer.class))

					value = getResultSet().getInt(subClass.getSimpleName() + "." + field.getName() + "_" + subField.getName());

				else

					if (subField.getType().equals(Double.class))

						value = getResultSet().getDouble(subClass.getSimpleName() + "." + field.getName() + "_" + subField.getName());

					else

						if (subField.getType().equals(Date.class)){

							java.sql.Date myOldValue = getResultSet().getDate(subClass.getSimpleName() + "." + field.getName() + "_" + subField.getName());

							if (myOldValue!=null)

								value = new Date(myOldValue.getTime());

						}else

							value = getResultSet().getObject(subClass.getSimpleName() + "." + field.getName() + "_" + subField.getName());

			return value;


		}


	}


}