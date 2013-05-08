package developen.client.framework.search;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.SearchModel;
import developen.client.framework.mvc.SearchView;
import developen.client.framework.widget.DBField;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.SearchState;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.InternalFrame;
import developen.common.framework.widget.InternalFramePosition;


public abstract class Search<E> implements View {

	
	protected SearchView<E> view;
	
	protected SearchController<E> controller;
	
	protected SearchModel<E> model;

	private DBField component;
	

	public E findBy() throws Exception{

		
		String s = getComponent().getFindByString();

		if (!s.isEmpty())
			
			return (E) getController().find(s);

		return null;
		

	}

	
	public E findBy(String s) throws Exception{

		
		if (!s.isEmpty())
			
			return (E) getController().find(s);

		return null;

		
	}

	
	public void addSearchListener(SearchListener<E> listener) {

		getController().addSearchListener(listener);

	}


	public void removeSearchListener(SearchListener<E> listener) {

		getController().removeSearchListener(listener);

	}

	
	public void openSearchView(JDesktopPane desktop){

		
		try {
			
			controller.reset();
			
			controller.browse();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		if (view.getDesktopPane() == null)
			
			desktop.add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.NORTHEAST);
		

	}

	
	public void openSearchViewWithoutReset(JDesktopPane desktop){

		
		try {
			
			controller.browse();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		if (view.getDesktopPane() == null)
			
			desktop.add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.NORTHEAST);
		

	}

	
	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		if (evt.getPropertyName().equals("ModelState")){

			if (evt.getNewValue().equals(SearchState.CANCELED) || evt.getNewValue().equals(SearchState.SELECTED)){

				if (getComponent() != null){

					JComponent frame = (JComponent) ((JComponent)getComponent()).getParent();

					while (frame.getParent() != null){

						frame = (JComponent) frame.getParent();
						
						if (frame instanceof InternalFrame) 
							
							break;

					}

					if (frame instanceof InternalFrame){
						
						try {
							
							((InternalFrame)frame).setSelected(true);
							
						} catch (PropertyVetoException e) {
							
							e.printStackTrace();
							
						}
						
					}

					if (evt.getNewValue().equals(SearchState.SELECTED))

						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								
								((JComponent) getComponent()).transferFocus();
								
							}
							
						});

					else

						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								
								((JComponent) getComponent()).requestFocus();
								
							}
							
						});

				}

			}

		}

		
	}


	public SearchView<E> getView(){

		return view;

	}	

	
	public SearchController<E> getController(){

		return controller;

	}	

	
	public SearchModel<E> getModel(){

		return model;

	}

	
	public DBField getComponent() {

		return component;

	}

	
	public void setComponent(DBField component) {

		this.component = component;
		
		getController().addView(this);

	}
	

}