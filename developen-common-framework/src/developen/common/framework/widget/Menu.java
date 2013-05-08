package developen.common.framework.widget;

import javax.swing.Icon;
import javax.swing.JMenu;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;

public class Menu extends JMenu implements LanguageChangeListener {


	private static final long serialVersionUID = -3503595124490647104L;
	
	private Tag caption;
	

	public Menu(Tag caption){

		
		setCaption(caption);
		
		init();
		

	}

	
	public Menu(Tag caption, Icon icon){

		
		setCaption(caption);
		
		setIcon(icon);
		
		init();
		

	}

	
	protected void init(){

		I18N.addLanguageChangeListener(this);

	}
	

	protected void onLoadLanguage() {

		
		if (getCaption() != null && !getCaption().getKey().isEmpty()){
			
			setText(getCaption().toString());
			
			if (getCaption().hasHotKey())
				
				setMnemonic(getCaption().getHotKey());
			
			setToolTipText(null);
			
		}
		

	}

	
	public void languageChanged(LanguageChangedEvent event) {

		this.onLoadLanguage();

	}

	
	public void setCaption(Tag caption) {

		
		this.caption = caption;
		
		this.onLoadLanguage();
		

	}

	
	public Tag getCaption() {

		return caption;

	}
	

}
