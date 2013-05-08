package developen.common.framework.widget;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

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

		
		getCaption(caption);
		
		init();
		

	}

	
	public Button(Action action){
	
		
		super(action);
		
		init();
		
		
	}
	
	
	public Button(Tag caption, Icon icon){

		
		getCaption(caption);
		
		setIcon(icon);
		
		init();
		

	}

	
	protected void init(){

		
		I18N.addLanguageChangeListener(this);
		
		setPreferredSize(new Dimension(100,26));
		

	}

	
	protected void onLoadLanguage() {

		
		if (getCaption()!=null && !getCaption().getKey().isEmpty()){
			
			setText(getCaption().toString());
			
			if (getCaption().hasHotKey())
				
				setMnemonic(getCaption().getHotKey());
			
			if (getCaption().hasToolTip())
				
				setToolTipText(getCaption().getToolTip());
			
		}
		

	}

	
	public void languageChanged(LanguageChangedEvent e) {

		onLoadLanguage();

	}

	
	public void getCaption(Tag caption) {

		
		this.caption = caption;
		
		onLoadLanguage();
		

	}
	

	public Tag getCaption() {

		return caption;

	}
	

}