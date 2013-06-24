package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import developen.common.framework.widget.ListView;

public class DBListView<E> extends ListView<E> implements DBComponent{

	
	private static final long serialVersionUID = -1181575906512166752L;
	
	private String propertyName;
	
	private boolean fixedValue;

	private Condition condition;
	

	public DBListView(String propertyName){

		
		super();
		
		setPropertyName(propertyName);
		

	}
	

	public DBListView(ListModel<E> dataModel, String propertyName){

		
		super(dataModel);
		
		setPropertyName(propertyName);
		

	}
	

	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}

	
	public String getPropertyName() {

		return propertyName;

	}

	
	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent event) {

		
		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName())){

			DefaultListModel<E> listModel = (DefaultListModel<E>) getModel();

			listModel.clear();				

			List<E> newList = (List<E>) event.getNewValue();

			if (newList != null)

				for (E object : newList)

					listModel.addElement(object);

			if (listModel.getSize()>0)
				
				getSelectionModel().addSelectionInterval(0, 0);
			
		}

		
	}

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new EditingOrIncludingCondition();
		
		return condition;
		

	}

	
	public void setCondition(Condition condition){

		this.condition = condition;

	}

	
	public boolean isFixedValue() {
		
		return fixedValue;
		
	}


	public void setFixedValue(boolean fixedValue) {
		
		this.fixedValue = fixedValue;
		
	}

	
}