package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import developen.common.framework.mvc.Search;
import developen.common.framework.widget.Table;

public class DBTable<E extends Search> extends Table implements DBComponent {


	private static final long serialVersionUID = -6247952889309717339L;

	private String propertyName;

	private boolean fixedValue;

	private Condition condition;


	public DBTable(String propertyName){


		super();

		setPropertyName(propertyName);


	}


	public DBTable(String propertyName, String[][] data, String[] columns) {


		super(data, columns);

		setPropertyName(propertyName);


	}


	public DBTable(String propertyName, DefaultTableModel tableModel) {


		super(tableModel);

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

			clear();

			List<E> list = (List<E>) event.getNewValue();

			if (event.getNewValue() != null && list.size() > 0) {

				DefaultTableModel model = (DefaultTableModel) getModel();

				for (E row : list)

					model.addRow(row.toColumns());

				if (model.getRowCount()>0)
					
					getSelectionModel().addSelectionInterval(0, 0);
				
			}			

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
