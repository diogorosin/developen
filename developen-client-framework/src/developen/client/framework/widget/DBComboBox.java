package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.utils.Tag;
import developen.common.framework.widget.ComboBox;

public class DBComboBox extends ComboBox implements DBField {

	
	private static final long serialVersionUID = 3223766840696598407L;
	
	private String propertyName;
	
	private boolean primaryKey;
	
	private boolean foreignKey;
	
	private boolean fixedValue;
	
	private Condition condition;
	

	public DBComboBox(Tag tag, Object[] list) {

		super(tag, list);

	}

	
	public DBComboBox(Tag tag, Object[] list, String propertyName) {

		
		super(tag, list);
		
		setPropertyName(propertyName);
		

	}

	
	public void modelPropertyChanged(PropertyChangeEvent event) {

		
		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName()))

			setSelectedItem(event.getNewValue());


	}

	
	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}

	
	public String getPropertyName() {

		return propertyName;

	}

	
	public void setPrimaryKey(boolean primaryKey) {

		
		this.primaryKey = primaryKey;
		
		this.setBackground(primaryKey ? 
				
				Theme.PRIMARY_KEY_FIELD_COLOR : 
					
					Theme.DEFAULT_BACKGROUND_FIELD_COLOR);
		

	}

	
	public boolean isPrimaryKey() {

		return primaryKey;

	}

	
	public void setForeignKey(boolean foreignKey) {

		this.foreignKey = foreignKey;

	}

	
	public boolean isForeignKey() {

		return foreignKey;

	}

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new EditingOrIncludingCondition();
		
		return condition;
		

	}

	
	public void setCondition(Condition condition){

		this.condition = condition;

	}

	
//	public Search getSearch() {
//
//		return search;
//
//	}

	
//	public void setSearch(Search search) {
//
//		
//		this.search = search;
//		
//		this.search.setComponent(this);
//		
//
//	}

	
//	public JDesktopPane getDesktopPane() {
//
//		
//		JDesktopPane desktop = null;
//
//		JComponent frame = (JComponent) this.getParent();
//		
//		while (frame.getParent() != null){
//
//			frame = (JComponent) frame.getParent();
//			
//			if (frame instanceof JInternalFrame) 
//				
//				break;
//
//		}
//
//		if (frame instanceof JInternalFrame)
//			
//			desktop = ((JInternalFrame) frame).getDesktopPane();
//
//		return desktop;
//		
//
//	}

	
//	public String getFindByString() {
//
//		return getSelectedItem().toString();
//
//	}

	
	public boolean isFixedValue() {
		
		return fixedValue;
		
	}


	public void setFixedValue(boolean fixedValue) {
		
		this.fixedValue = fixedValue;
		
	}


}