package developen.common.framework.widget;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import developen.common.framework.exception.InvalidDateException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;

public class DateField extends FormattedTextField {


	private static final long serialVersionUID = -6761965520931595043L;


	public DateField(Tag caption){

		super(caption, FormatFactory.createDateFormatter());

	}


	protected void init(){


		setPreferredSize(new Dimension(115,24));

		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent event) {

				if (event.getKeyCode() == KeyEvent.VK_ENTER){

					if (isChecked())

						transferFocus();

					else {

						try {

							check();

							transferFocus();

						} catch (Exception e) {

							Messenger.show(e);

						}

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

		setFocusLostBehavior(JFormattedTextField.PERSIST);


	}


	public void setDate(Date date){


		if (date==null)

			setValue(null);

		else {

			setText(FormatFactory.createDateFormatWith4DigitsYear().format(date));

			setChecked(true);

		}


	}

	public Date getDate() throws InvalidDateException{


		Date date = null;

		if (!getText().trim().equals("/  /")){

			if (getText().trim().length() >= 8){

				try {

					date = FormatFactory.createDateFormatWith2DigitsYear().parse(getText());

					setText(FormatFactory.createDateFormatWith4DigitsYear().format(date));

				} catch (ParseException e) {

					throw new InvalidDateException(getText(), getCaption());

				}

			} else {

				throw new InvalidDateException(getText(), getCaption());

			}

		} 

		return date;


	}


}