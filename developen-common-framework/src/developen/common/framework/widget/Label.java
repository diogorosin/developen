package developen.common.framework.widget;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JLabel;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;

public class Label extends JLabel implements LanguageChangeListener {


	private static final long serialVersionUID = 2008942807587020195L;

	private Tag caption;


	public Label(){

		init();

	}


	public Label(Icon icon){


		super(icon);

		init();


	}


	public Label(Tag fieldName){


		setCaption(fieldName);

		init();


	}


	public Label(Tag fieldName, Icon icon){


		super(icon);

		setCaption(fieldName);

		init();


	}


	protected void init(){

		I18N.addLanguageChangeListener(this);

	}


	protected void onLoadLanguage() {


		if (getCaption() != null && !getCaption().getKey().isEmpty()){

			setText(getCaption().toString());

			if (getCaption().hasHotKey())

				setDisplayedMnemonic(getCaption().getHotKey());

		}


	}


	public void setLabelFor(Component component){

		
		super.setLabelFor(component);

		if (component!=null){

			component.addPropertyChangeListener(new PropertyChangeListener() {

				public void propertyChange(PropertyChangeEvent evt) {

					if (evt.getPropertyName().equals("enabled"))

						Label.this.setEnabled((Boolean)evt.getNewValue());

				}

			});

		}
		

	}


	public void languageChanged(LanguageChangedEvent event) {

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
