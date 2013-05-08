package developen.common.framework.widget;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.Tag;

public class PasswordField extends JPasswordField implements Nameable, Checkable {

	
	private static final long serialVersionUID = 8863940381781345728L;
	
	private ArrayList<CheckListener> registeredEvents;
	
	private boolean checked;
	
	private Tag caption;
	
	private boolean selectAllOnFocusGained;
	

	public PasswordField(Tag caption){
		
		
		setCaption(caption);
		
		init();
		
		
	}

	
	public void setText(String text){

		
		super.setText(text);
		
		setChecked(true);
		

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
						
						if (isSelectAllOnFocusGained())
							
							selectAll();
						
					}
					
				});
				
			}
			
		});
		
		setSelectAllOnFocusGained(true);

		
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

	
}