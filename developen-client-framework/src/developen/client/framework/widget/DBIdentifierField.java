package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import developen.common.framework.utils.Tag;
import developen.common.framework.widget.TextField;

public class DBIdentifierField extends TextField implements DBField{
	
	
	class MyDocument extends PlainDocument{

		private static final long serialVersionUID = 3148191526843088629L;

		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
			
			char digit;
			
			boolean isNumber = true;
			
			for (int i = 0; i < str.length(); i++) {
				
				digit = str.charAt(i);
				
				if (!Character.isDigit(digit)){
					
					isNumber = false;
					
					break;
					
				}
				
			}
			
			if (isNumber)
				
				super.insertString(offs, str, a);
			
		}
		
	}

	
	private static final long serialVersionUID = 5254764103720680641L;

	private String propertyName;

	private boolean primaryKey;

	private boolean foreignKey;
	
	private boolean fixedValue;

	private Condition condition;


	public DBIdentifierField(Tag componentName) {

		
		super(componentName);
		
		setDocument(new MyDocument());
		
		getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent event) {
				
				setChecked(false);
				
			}
			
			public void insertUpdate(DocumentEvent event) {
				
				setChecked(false);
				
			}
			
			public void changedUpdate(DocumentEvent event) {
				
				setChecked(false);
				
			}
			
		});


	}


	public DBIdentifierField(Tag componentName, String propertyName) {


		super(componentName);

		setPropertyName(propertyName);

		setDocument(new MyDocument());
		
		getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent event) {
				
				setChecked(false);
				
			}
			
			public void insertUpdate(DocumentEvent event) {
				
				setChecked(false);
				
			}
			
			public void changedUpdate(DocumentEvent event) {
				
				setChecked(false);
				
			}
			
		});

		
	}


	public void modelPropertyChanged(PropertyChangeEvent event) {


		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName())){

			setText(event.getNewValue()==null ? "" : 

				event.getNewValue().toString());
		
			setCaretPosition(0);
			
		}


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