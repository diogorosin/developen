package developen.client.framework.widget;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import developen.common.framework.utils.Tag;

public class DBIdentifierField extends DBTextField {
	
	
	private static final long serialVersionUID = -3575939423788072703L;

	
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

		
		super(componentName, propertyName);
		
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

	
}