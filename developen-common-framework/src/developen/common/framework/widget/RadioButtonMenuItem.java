package developen.common.framework.widget;

import javax.swing.JRadioButtonMenuItem;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;

public class RadioButtonMenuItem extends JRadioButtonMenuItem implements LanguageChangeListener {

	
	private static final long serialVersionUID = -3570616812858827494L;
	
	private Tag caption;
	

	public RadioButtonMenuItem(Action action){

		
		super(action);
		
		setCaption(action.getCaption());
		
		init();
		

	}

	
	protected void init(){

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