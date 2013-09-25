package developen.common.framework.widget;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.Format;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;

public class FormattedTextField extends JFormattedTextField implements Nameable, Checkable {


	private static final long serialVersionUID = -6568696224656744567L;

	private ArrayList<CheckListener> registeredEvents;

	private boolean checked;

	private Tag caption;


	public FormattedTextField(Tag caption) {


		super();

		setCaption(caption);

		init();


	}


	public FormattedTextField(Tag caption, Format format) {


		super(format);

		setCaption(caption);

		init();


	}


	public FormattedTextField(Tag caption, AbstractFormatter format){


		super(format);

		setCaption(caption);

		init();


	}


	public FormattedTextField(Tag caption, AbstractFormatterFactory formatFactory){


		super(formatFactory);

		setCaption(caption);

		init();


	}


	protected void init(){


		setPreferredSize(new Dimension(115,UIConstants.DEFAULT_FIELD_HEIGHT));

		addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent event) {

				if (event.getKeyCode() == KeyEvent.VK_ENTER){

					if (isChecked())

						transferFocus();

					else {

						SwingUtilities.invokeLater(new Runnable() {

							public void run() {

								try {

									check();

									transferFocus();

									SwingUtilities.invokeLater(new Runnable() {

										public void run() {

											setChecked(true);

										}

									});

								} catch (Exception e) {

									if (e instanceof NumberFormatException){

										transferFocus();

										setChecked(true);

									} else

										Messenger.show(e);

								}

							}

						});

					}

				}

			}

		});

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

				SwingUtilities.invokeLater(new Runnable() {

					public void run() {

						select(0, 0);

					}

				});

			}

			public void focusGained(FocusEvent arg0) {

				SwingUtilities.invokeLater(new Runnable() {

					public void run() {

						selectAll();

					}

				});

			}

		});


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

	
	public void setCaption(Tag fieldName) {

		this.caption = fieldName;

	}

	
	public Tag getCaption() {

		return caption;

	}

	
}