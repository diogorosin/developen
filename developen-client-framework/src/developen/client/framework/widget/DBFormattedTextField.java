package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;
import java.text.Format;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.framework.search.Search;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.FormattedTextField;

public class DBFormattedTextField extends FormattedTextField implements DBField{

	
	private static final long serialVersionUID = -9047726148471732329L;

	private String propertyName;
	
	private boolean primaryKey;
	
	private boolean foreignKey;
	
	private boolean fixedValue;
	
	private Search search;
	
	private Condition condition;
	

	public DBFormattedTextField(Tag componentName) {

		super(componentName);

	}

	
	public DBFormattedTextField(Tag componentName, String propertyName) {

		
		super(componentName);
		
		setPropertyName(propertyName);
		

	}

	
	public DBFormattedTextField(Tag componentName, String propertyName, Format format) {

		
		super(componentName, format);
		
		setPropertyName(propertyName);
		

	}
	

	public DBFormattedTextField(Tag componentName, String propertyName, AbstractFormatter format) {

		
		super(componentName, format);
		
		setPropertyName(propertyName);
		

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
					
					Theme.DEFAULT_BACKGROUND_FIELD_COLOR );
		

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

	
	public void modelPropertyChanged(PropertyChangeEvent event) {

		
		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName()))

			setValue(event.getNewValue());
		

	}

	
	public Search getSearch() {

		return search;

	}

	
	public void setSearch(Search search) {

		
		this.search = search;
		
		this.search.setComponent(this);
		

	}

	
	public JDesktopPane getDesktopPane() {

		
		JDesktopPane desktop = null;

		JComponent frame = (JComponent) this.getParent();
		
		while (frame.getParent() != null){

			frame = (JComponent) frame.getParent();
			
			if (frame instanceof JInternalFrame) break;

		}

		if (frame instanceof JInternalFrame)
			
			desktop = ((JInternalFrame) frame).getDesktopPane();

		return desktop;
		

	}

	
	public String getFindByString() {

		return getText();

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