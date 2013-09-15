package developen.common.framework.widget;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;

public class Button extends JButton implements LanguageChangeListener {

	
	private static final long serialVersionUID = 6383982614895195383L;
	
	private Tag caption;
	

	public Button(Icon icon){

		
		super(icon);
		
		init();
	

	}

	
	public Button(Tag caption){

		
		setCaption(caption);
		
		init();
		

	}

	
	public Button(Action action){
	
		
		super(action);
		
		init();
		
		
	}
	
	
	public Button(Tag caption, Icon icon){

		
		setCaption(caption);
		
		setIcon(icon);
		
		init();
		

	}

	
	protected void init(){

		
		I18N.addLanguageChangeListener(this);
		
		setPreferredSize(new Dimension(100,26));
		
		registerKeyboardAction(getActionForKeyStroke(
				
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                
                JComponent.WHEN_FOCUSED);

		registerKeyboardAction(getActionForKeyStroke(
				
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                
                JComponent.WHEN_FOCUSED);

		
	}

	
	protected void onLoadLanguage() {

		
		if (getCaption()!=null && !getCaption().getKey().isEmpty()){
			
			setText(getCaption().toString());
			
			if (getCaption().hasHotKey())
				
				setMnemonic(getCaption().getHotKey());
			
			if (getCaption().hasToolTip())
				
				setToolTipText(getCaption().getToolTip());

			if (getCaption().hasSmallIcon())
				
				setIcon(getCaption().getSmallIcon());

		}
		

	}

	
	public void languageChanged(LanguageChangedEvent e) {

		onLoadLanguage();

	}

	
	public void setCaption(Tag caption) {

		
		this.caption = caption;
		
		onLoadLanguage();
		

	}
	

	public Tag getCaption() {

		return caption;

	}
	

}