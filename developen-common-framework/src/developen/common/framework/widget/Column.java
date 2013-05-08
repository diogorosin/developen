package developen.common.framework.widget;

import javax.swing.table.TableColumn;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;


public class Column extends TableColumn implements LanguageChangeListener, Nameable {

	
	private static final long serialVersionUID = 1L;
	
	private Tag caption;
	

	public Column(Tag caption){
		
		
		setCaption(caption);
		
		init();
		
		
	}
	
	
	public Column(Tag caption, int index){
		
		
		super(index);
		
		setCaption(caption);
		
		init();
		
		
	}

	
	public void languageChanged(LanguageChangedEvent event) {
		
		this.onLoadLanguage();
		
	}
	

	protected void onLoadLanguage() {
		
		
		if (getCaption()!=null && !getCaption().getKey().isEmpty())
			
			setHeaderValue(getCaption().toString());
			
		
	}
	
	
	protected void init(){
		
		I18N.addLanguageChangeListener(this);
		
	}
	
	
	public Tag getCaption() {

		return caption;

	}
	

	public void setCaption(Tag caption) {

		
		this.caption = caption;
		
		this.onLoadLanguage();
		
		
	}
	
	
	public String toString(){
		
		return getCaption() != null ? getCaption().toString() : "";
		
	}

	
}