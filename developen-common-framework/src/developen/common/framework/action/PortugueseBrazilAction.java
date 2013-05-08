package developen.common.framework.action;

import java.awt.event.ActionEvent;
import java.util.Locale;

import developen.common.framework.i18n.PortugueseBrazilTag;
import developen.common.framework.utils.I18N;
import developen.common.framework.widget.Action;

public class PortugueseBrazilAction extends Action {

	
	private static final long serialVersionUID = -7547258492565071770L;

	
	public PortugueseBrazilAction() {

		super(new PortugueseBrazilTag());

	}

	
	public void actionPerformed(ActionEvent e) {

		I18N.setLanguage(new Locale("pt", "BR"));
		
	}
	

}