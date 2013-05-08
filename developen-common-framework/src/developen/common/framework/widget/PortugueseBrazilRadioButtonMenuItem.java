package developen.common.framework.widget;

import java.util.Locale;

import developen.common.framework.action.PortugueseBrazilAction;
import developen.common.framework.utils.LanguageChangedEvent;

public class PortugueseBrazilRadioButtonMenuItem extends RadioButtonMenuItem {


	private static final long serialVersionUID = -4691988289483036511L;

	
	public PortugueseBrazilRadioButtonMenuItem() {

	
		super(new PortugueseBrazilAction());
		
		this.setSelected(Locale.getDefault().getLanguage().equals("pt")
				
				&& Locale.getDefault().getCountry().equals("BR"));
		
		
	}

	
	public void languageChanged(LanguageChangedEvent event) {

		
		super.onLoadLanguage();
		
		this.setSelected(Locale.getDefault().getLanguage().equals("pt")
				
				&& Locale.getDefault().getCountry().equals("BR"));
		

	}
	

}