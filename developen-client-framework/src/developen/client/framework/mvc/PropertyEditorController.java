package developen.client.framework.mvc;

import java.util.ArrayList;

import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.PropertyEditor;
import developen.common.framework.mvc.PropertyEditorState;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.Checkable;

public abstract class PropertyEditorController extends Controller {


	private ArrayList<PropertyEditorListener> registeredEditorListeners;

	public static final String MODEL_STATE_PROPERTY = "ModelState";
	
	
	private ArrayList<PropertyEditorListener> getRegisteredEditorListeners(){

		
		if (registeredEditorListeners == null)
			
			registeredEditorListeners = new ArrayList<PropertyEditorListener>();
		
		return registeredEditorListeners;

		
	}

	
	public void addEditorListener(PropertyEditorListener listener) {

		getRegisteredEditorListeners().add(listener);

	}

	
	public void removeEditorListener(PropertyEditorListener listener) {

		getRegisteredEditorListeners().remove(listener);

	}
	
	
	public boolean isEditing(){

		return ((PropertyEditor)getModel()).getModelState().equals(PropertyEditorState.EDITING); 

	}

	
	public void edit() throws Exception{

		
		onBeforeEdit();
		
		onEdit();
		
		onAfterEdit();
		

	}


	protected void onBeforeEdit() throws Exception{}

	
	protected void onEdit() throws Exception{}

	
	protected void onAfterEdit() throws Exception{

		setModelProperty(MODEL_STATE_PROPERTY, PropertyEditorState.EDITING);

	}

	
	public void confirm() throws Exception{

		
		onBeforeConfirm();
		
		onConfirm();
		
		onAfterConfirm();
		

	}

	
	protected void onBeforeConfirm() throws Exception{

		
		for (View view : getRegisteredViews()) {

			if (view instanceof Checkable){

				Checkable component = (Checkable) view;
				
				if (!component.isChecked())
					
					component.check();

			}

		}
		

	}

	
	protected void onConfirm() throws Exception{

		
		PropertyEditorEvent event = getEventOnConfirm();
		
		for (PropertyEditorListener l : getRegisteredEditorListeners())
			
			l.onEditionConfirmed(event);
		
		
	}

	
	protected void onAfterConfirm() throws Exception{

		setModelProperty(MODEL_STATE_PROPERTY, PropertyEditorState.CONFIRMED);

	}

	
	public void cancel() throws Exception{

		
		onBeforeCancel();
		
		onCancel();
		
		onAfterCancel();
		

	}

	
	protected void onBeforeCancel() throws Exception{}

	
	protected void onCancel() throws Exception{
		
	
		PropertyEditorEvent event = getEventOnCancel();
		
		for (PropertyEditorListener l : getRegisteredEditorListeners())
			
			l.onEditionCanceled(event);
		

	}

	
	protected void onAfterCancel() throws Exception{

		setModelProperty(MODEL_STATE_PROPERTY, PropertyEditorState.CANCELED);

	}
	
	
	public abstract PropertyEditorEvent getEventOnConfirm();
	
	
	public abstract PropertyEditorEvent getEventOnCancel();
	

}