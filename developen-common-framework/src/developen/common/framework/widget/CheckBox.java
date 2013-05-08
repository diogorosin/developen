package developen.common.framework.widget;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;


public class CheckBox extends JCheckBox implements Nameable, LanguageChangeListener {


	private static final long serialVersionUID = 5502708368398275103L;

	private Tag caption;


	public CheckBox(Tag caption) {


		setCaption(caption);

		init();


	}


	protected void init(){


		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent keyEvent) {

				if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {

					SwingUtilities.invokeLater(new Runnable() {

						public void run() {

							transferFocus();

						}

					});

				}

			}

		});

		I18N.addLanguageChangeListener(this);


	}


	public void setCaption(Tag caption) {

	
		this.caption = caption;
		
		onLoadLanguage();
		

	}

	
	public Tag getCaption() {

		return caption;

	}
	

	public void languageChanged(LanguageChangedEvent event) {

		onLoadLanguage();

	}
	

	protected void onLoadLanguage() {

		
		if (getCaption() != null && !getCaption().getKey().isEmpty()){
			
			setText(getCaption().toString());
			
			if (getCaption().hasHotKey())
				
				setMnemonic(getCaption().getHotKey());
			
			if (getCaption().hasToolTip())
				
				setToolTipText(getCaption().getToolTip());
			
		}

		
	}

	
}