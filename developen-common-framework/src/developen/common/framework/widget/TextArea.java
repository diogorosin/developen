package developen.common.framework.widget;


import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.Tag;


public class TextArea extends JTextArea implements Nameable, Checkable {


	private static final long serialVersionUID = -2033152566144781625L;

	private ArrayList<CheckListener> registeredEvents;

	private boolean checked;

	private Tag caption;

	private boolean selectAllOnFocusGained;


	public TextArea(Tag caption) {


		setCaption(caption);

		init();


	}


	public void setText(String text){


		super.setText(text);

		if (text!=null && !text.isEmpty())

			setChecked(true);


	}


	protected void init(){


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

		addFocusListener(new FocusAdapter() {

			
			public void focusLost(FocusEvent arg0) {

				if (!isChecked())

					try {

						check();

						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								
								select(0, 0);
								
							}
							
						});
						
					} catch (Exception e) {

						requestFocus();
						
						Messenger.show(e);

					}

			}
			
			public void focusGained(FocusEvent arg0) {
				
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						
						if (isSelectAllOnFocusGained())
							
							selectAll();
						
					}
					
				});
				
			}

		});

		setSelectAllOnFocusGained(false);


	}


	public void check() throws Exception {


		for (CheckListener listener : getRegisteredEvents())

			listener.onCheck(new CheckEvent(this));


	}


	public boolean isChecked() {

		return checked;

	}


	public void setChecked(boolean checked) {

		this.checked = checked;

	}


	protected ArrayList<CheckListener> getRegisteredEvents(){


		if (registeredEvents == null)

			registeredEvents = new ArrayList<CheckListener>();

		return registeredEvents;


	}


	public void addCheckListener(CheckListener event) {

		getRegisteredEvents().add(event);

	}


	public void removeCheckListener(CheckListener event) {

		getRegisteredEvents().remove(event);

	}


	public void setCaption(Tag caption) {

		this.caption = caption;

	}


	public Tag getCaption() {

		return caption;

	}


	public boolean isSelectAllOnFocusGained() {

		return selectAllOnFocusGained;

	}


	public void setSelectAllOnFocusGained(boolean selectAllOnFocusGained) {

		this.selectAllOnFocusGained = selectAllOnFocusGained;

	}


	class MyDocument extends PlainDocument{


		private static final long serialVersionUID = -207052170404638896L;


		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{

			super.insertString(offs, str.toUpperCase(), a);

		}


	}

	
}