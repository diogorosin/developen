package developen.common.framework.widget;

import java.util.Locale;

import developen.common.framework.action.EnglishUSAction;
import developen.common.framework.utils.LanguageChangedEvent;

public class EnglishUSARadioButtonMenuItem extends RadioButtonMenuItem {


	private static final long serialVersionUID = -2740551845894768705L;

	
	public EnglishUSARadioButtonMenuItem() {

		
		super(new EnglishUSAction());
		
		this.setSelected(Locale.getDefault().getLanguage().equals("en")
				
				&& Locale.getDefault().getCountry().equals("US"));
		

	}
	

	public void languageChanged(LanguageChangedEvent event) {

		
		super.onLoadLanguage();
		
		this.setSelected(Locale.getDefault().getLanguage().equals("en")
				
				&& Locale.getDefault().getCountry().equals("US"));
		

	}
	

}