package developen.client.framework.widget;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.framework.search.Search;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.TextField;

public class DBTextField extends TextField implements DBField{


	private static final long serialVersionUID = 5254764103720680641L;

	private String propertyName;

	private boolean primaryKey;

	private boolean foreignKey;
	
	private boolean fixedValue;

	private Search search;

	private Condition condition;


	public DBTextField(Tag componentName) {

		super(componentName);

	}


	public DBTextField(Tag componentName, String propertyName) {


		super(componentName);

		setPropertyName(propertyName);


	}


	public void init(){


		super.init();

		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent event) {

				if (event.getKeyCode() == KeyEvent.VK_F4){

					if (getSearch() != null)

						getSearch().openSearchView(getDesktopPane());

				}

			}

		});


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

		if (event.getPropertyName().equals(getPropertyName())){

			setText(event.getNewValue()==null ? "" : 

				event.getNewValue().toString());
		
			setCaretPosition(0);
			
		}


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
			
			if (frame instanceof JInternalFrame) 
				
				break;

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