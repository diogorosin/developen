package developen.common.framework.widget;

import javax.swing.JFrame;

import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;

public abstract class Frame extends JFrame implements LanguageChangeListener, View {


	private static final long serialVersionUID = 5650347505821772951L;
	
	private Controller controller;
	

	public Frame(Controller controller) {

		
		setController(controller);
		
		I18N.addLanguageChangeListener(this);
		
		init();
		

	}

	
	public abstract void init();
	

	public abstract Tag getCaption();
	
	
	public void loadLanguage(){

		
		if (getCaption()!=null)
			
			setTitle(getCaption().toString());
		

	}

	
	public Controller getController() {

		return controller;

	}
	

	public void setController(Controller controller) {

		this.controller = controller;

	}
	

	public void languageChanged(LanguageChangedEvent event) {

		loadLanguage();

	}
	

}