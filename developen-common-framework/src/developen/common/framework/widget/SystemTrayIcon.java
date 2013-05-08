package developen.common.framework.widget;

import java.awt.Image;
import java.awt.TrayIcon;


import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;


public abstract class SystemTrayIcon extends TrayIcon implements View, LanguageChangeListener{


	private Controller controller;

	private Tag caption;


	public SystemTrayIcon(Image image) {


		super(image);
		
		init();
		

	}


	public SystemTrayIcon(Image image, Tag caption) {


		super(image);

		setCaption(caption);

		init();


	}


	public SystemTrayIcon(Image image, Tag tooltip, Controller controller) {


		super(image);

		setCaption(tooltip);

		setController(controller);

		init();


	}


	public void setController(Controller controller) {

		this.controller = controller;

	}


	public Controller getController() {

		return controller;

	}


	protected void init(){

		I18N.addLanguageChangeListener(this);

	}


	protected void onLoadLanguage() {


		if (this.getCaption()!=null && this.getCaption().getKey()!="")

			this.setToolTip(this.getCaption().toString());


	}


	public void languageChanged(LanguageChangedEvent event) {

		this.onLoadLanguage();

	}


	public void setCaption(Tag tag) {

		
		this.caption = tag;
		
		this.onLoadLanguage();
		

	}


	public Tag getCaption() {

		return caption;

	}

	
}