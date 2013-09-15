package developen.client.framework.mvc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import developen.client.framework.exception.RecordNotFoundException;
import developen.client.framework.i18n.QuestionOnBeforeCancelTag;
import developen.client.framework.i18n.QuestionOnBeforeDeleteTag;
import developen.client.framework.widget.DBField;
import developen.common.framework.exception.OperationCanceledByUserException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.messenger.Question;
import developen.common.framework.messenger.SimplifiedQuestion;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.Checkable;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.OneToOne;
import developen.common.persistence.annotation.Table;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;
import developen.common.persistence.session.SessionUtils;

public abstract class EntryController extends Controller {


	private void changeModelState(EntryState modelState) {

		setModelProperty("ModelState", modelState);

	}


	public boolean isClean(){

		return ((Entry)getModel()).getModelState().equals(EntryState.CLEAN);

	}


	public boolean isEditing(){

		return ((Entry)getModel()).getModelState().equals(EntryState.EDITING); 

	}


	public boolean isIncluding(){

		return ((Entry)getModel()).getModelState().equals(EntryState.INCLUDING);

	}


	public void refresh() throws Exception{


		onBeforeRefresh();

		onRefresh();

		onAfterRefresh();


	}


	public void clear() throws Exception{


		onBeforeClear();

		onClear();

		onAfterClear();


	}


	public void save() throws Exception{


		onBeforeSave();

		onSave();

		onAfterSave();


	}


	public void delete() throws Exception{


		onBeforeDelete();

		onDelete();

		onAfterDelete();


	}


	public void include() throws Exception{


		onBeforeInclude();

		onInclude();

		onAfterInclude();


	}


	public void edit() throws Exception{


		onBeforeEdit();

		onEdit();

		onAfterEdit();


	}


	public void cancel() throws Exception{


		onBeforeCancel();

		onCancel();

		onAfterCancel();


	}


	protected void onBeforeRefresh() throws Exception{};


	protected void onRefresh() throws Exception{


		Session session = DPA.getSessionFactory().createSession();

		Object newObject = session.read(

				getModel().getClass(),

				SessionUtils.getIdentifier(getModel()));

		session.close();

		if (newObject == null)

			throw new RecordNotFoundException();

		else{

			Class<?> myClass = newObject.getClass();

			while (myClass.isAnnotationPresent(Table.class)){

				for(Field field : myClass.getDeclaredFields()){

					if (field.isAnnotationPresent(Column.class) 

							|| field.isAnnotationPresent(OneToMany.class)){

						field.setAccessible(true);

						Method setter = null;

						setter = myClass.getMethod(

								"set" + field.getName().substring(0, 1).toUpperCase() +

								field.getName().substring(1)

								, new Class[]{field.getType()});

						setter.invoke(getModel(), field.get(newObject));

					} else {

						if (field.isAnnotationPresent(ManyToOne.class)) {

							field.setAccessible(true);

							Method setter = null;

							Class<?> cast = field.getAnnotation(ManyToOne.class).cast();

							if (cast!=Object.class)

								setter = myClass.getDeclaredMethod("set" + field.getName().substring(0, 1).toUpperCase() +

										field.getName().substring(1)

										, new Class[] { cast });

							else

								setter = myClass.getDeclaredMethod("set" + field.getName().substring(0, 1).toUpperCase() +

										field.getName().substring(1)

										, new Class[] { field.getType() });

							setter.invoke(getModel(), field.get(newObject));

						} else {

							if (field.isAnnotationPresent(OneToOne.class)){

								field.setAccessible(true);

								Object newValue = field.get(newObject);

								Object oldValue = field.get(getModel());

								if (oldValue==null){

									Method setter = myClass.getMethod(

											"set" + field.getName().substring(0, 1).toUpperCase() +

											field.getName().substring(1)

											, new Class[]{field.getType()});

									setter.invoke(getModel(), field.get(newObject));

								} else {

									Class<?> oldValueClass = oldValue.getClass();

									for (Field oldValueClassField : oldValueClass.getDeclaredFields()) {

										if (oldValueClassField.isAnnotationPresent(Column.class)

												|| oldValueClassField.isAnnotationPresent(Identifier.class)

												|| oldValueClassField.isAnnotationPresent(ManyToOne.class)

												|| oldValueClassField.isAnnotationPresent(OneToMany.class)){

											oldValueClassField.setAccessible(true);

											Method setter = oldValueClass.getMethod(

													"set" + oldValueClassField.getName().substring(0, 1).toUpperCase() +

													oldValueClassField.getName().substring(1)

													, new Class[]{oldValueClassField.getType()});

											setter.invoke(oldValue, oldValueClassField.get(newValue));

										}

									}

								}

							}

						}

					}

				}

				myClass = myClass.getSuperclass();

			}

		}


	};


	protected void onAfterRefresh() throws Exception{


		changeModelState(EntryState.REFRESHED);

		changeModelState(EntryState.EDITING);


	}


	protected void onBeforeClear() throws Exception{};


	protected void onClear() throws Exception{};


	protected void onAfterClear() throws Exception{


		for (View view : getRegisteredViews()) 

			if (view instanceof Checkable){

				if (view instanceof DBField){

					if (!((DBField) view).isFixedValue())

						((Checkable) view).setChecked(false);

				} else

					((Checkable) view).setChecked(false);

			}

		changeModelState(EntryState.CLEAN);


	}


	protected void onBeforeSave() throws Exception{


		for (View view : getRegisteredViews())

			if (view instanceof Checkable)

				if (!((Checkable) view).isChecked())

					((Checkable) view).check();


	}


	protected void onSave() throws Exception{


		Session session = DPA.getSessionFactory().createSession(); 

		if (isIncluding())

			session.create(getModel());

		else

			session.update(getModel());

		session.close();


	}


	protected void onAfterSave() throws Exception{


		changeModelState(EntryState.SAVED);

		clear();


	}


	protected void onBeforeDelete() throws Exception{


		if (!Messenger.ask(

				new SimplifiedQuestion(

						new QuestionOnBeforeDeleteTag())).equals(

								Question.YES))

			throw new OperationCanceledByUserException();


	}


	protected void onDelete() throws Exception{


		Session session = DPA.getSessionFactory().createSession(); 

		session.delete(getModel());

		session.close();


	}


	protected void onAfterDelete() throws Exception{


		changeModelState(EntryState.DELETED);

		clear();


	}


	protected void onBeforeInclude() throws Exception{}


	protected void onInclude() throws Exception{}


	protected void onAfterInclude() throws Exception{

		changeModelState(EntryState.INCLUDING);

	}


	protected void onBeforeEdit() throws Exception{}


	protected void onEdit() throws Exception{}


	protected void onAfterEdit() throws Exception{

		changeModelState(EntryState.EDITING);

	}


	protected void onBeforeCancel() throws Exception{


		if (isEditing() || isIncluding())

			if (!Messenger.ask(

					new SimplifiedQuestion(

							new QuestionOnBeforeCancelTag())).equals(

									Question.YES))

				throw new OperationCanceledByUserException();


	}


	protected void onCancel() throws Exception{};


	protected void onAfterCancel() throws Exception{

		changeModelState(EntryState.CANCELED);

	}


}