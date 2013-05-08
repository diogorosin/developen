package developen.common.framework.mvc;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import developen.common.persistence.annotation.ManyToOne;


public abstract class Controller implements PropertyChangeListener {


	protected ArrayList<View> registeredViews;

	protected Model model;


	public void addView(View view) {

		getRegisteredViews().add(view);

	}


	public void removeView(View view) {

		getRegisteredViews().remove(view);

	}


	protected ArrayList<View> getRegisteredViews(){


		if (registeredViews == null)

			registeredViews = new ArrayList<View>();

		return registeredViews;


	}


	public Model getModel() {

		return model;

	}


	public void setModel(Model model) {


		this.model = model;

		this.model.addPropertyChangeListener(this);


	}


	public void propertyChange(PropertyChangeEvent evt) {


		for (View view: getRegisteredViews())

			view.modelPropertyChanged(evt);


	}


	public void setModelProperty(String propertyName, Object paramValue) {


		try {

			Field field = null;

			Method method = null;

			Class<?> myClass = getModel().getClass();

			while(myClass.getSuperclass() != null){

				for (Field myField : myClass.getDeclaredFields())

					if (myField.getName().equals(

							propertyName.substring(0,1).toLowerCase() + 

							propertyName.substring(1))){

						field = myField;

						if (myField.isAnnotationPresent(ManyToOne.class)){

							Class<?> cast = myField.getAnnotation(ManyToOne.class).cast();

							if (cast!=Object.class)
								
								method = myClass.getDeclaredMethod("set" + propertyName, new Class[] { cast });
							
							else
								
								method = myClass.getDeclaredMethod("set" + propertyName, new Class[] { field.getType() });

						} else 

							method = myClass.getDeclaredMethod("set" + propertyName, new Class[] { field.getType() });

						break;

					}

				myClass = myClass.getSuperclass();

			}

			method.invoke(getModel(), paramValue);

		} catch (Exception exception){

			exception.printStackTrace();

		}


	}


}