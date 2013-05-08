package developen.common.framework.action;

import java.awt.event.ActionEvent;
import java.util.Locale;

import developen.common.framework.i18n.EnglishUSATag;
import developen.common.framework.utils.I18N;
import developen.common.framework.widget.Action;

public class EnglishUSAction extends Action {


	private static final long serialVersionUID = -5439485755302620516L;

	
	public EnglishUSAction() {

		super(new EnglishUSATag());

	}

	
	public void actionPerformed(ActionEvent e) {

		I18N.setLanguage(new Locale("en","US"));
		
	}

	
}