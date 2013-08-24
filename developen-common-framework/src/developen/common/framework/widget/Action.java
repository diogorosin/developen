package developen.common.framework.widget;



import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;


public abstract class Action extends AbstractAction implements LanguageChangeListener {


	private static final long serialVersionUID = -6726031588478543340L;

	private Tag caption;


	public Action(Tag caption){


		setCaption(caption);

		init();


	}

	
	protected void init(){

		I18N.addLanguageChangeListener(this);

	}


	public String toString(){


		if (getCaption() != null && !getCaption().getKey().isEmpty())

			return getCaption().toString();

		else

			return super.toString();


	}


	protected void onLoadLanguage() {


		if (getCaption() != null && !getCaption().getKey().isEmpty()){

			putValue(AbstractAction.NAME, getCaption().toString());

			if (getCaption().hasToolTip())

				putValue(AbstractAction.SHORT_DESCRIPTION, getCaption().getToolTip());

			if (getCaption().hasHotKey())

				putValue(AbstractAction.MNEMONIC_KEY, getCharCode(getCaption().getHotKey()));

			if (getCaption().hasSmallIcon())

				putValue(AbstractAction.SMALL_ICON, getCaption().getSmallIcon());
			
			if (getCaption().hasLargeIcon())

				putValue(AbstractAction.LARGE_ICON_KEY, getCaption().getLargeIcon());


		}


	}


	public int getCharCode(Character character){


		int code = 0;

		switch (getCaption().getHotKey()) {

		case 'A': code = KeyEvent.VK_A; break;

		case 'B': code = KeyEvent.VK_B; break;

		case 'C': code = KeyEvent.VK_C; break;

		case 'D': code = KeyEvent.VK_D; break;

		case 'E': code = KeyEvent.VK_E; break;

		case 'F': code = KeyEvent.VK_F; break;

		case 'G': code = KeyEvent.VK_G; break;

		case 'H': code = KeyEvent.VK_H; break;

		case 'I': code = KeyEvent.VK_I; break;

		case 'J': code = KeyEvent.VK_J; break;

		case 'K': code = KeyEvent.VK_K; break;

		case 'L': code = KeyEvent.VK_L; break;

		case 'M': code = KeyEvent.VK_M; break;

		case 'N': code = KeyEvent.VK_N; break;

		case 'O': code = KeyEvent.VK_O; break;

		case 'P': code = KeyEvent.VK_P; break;

		case 'Q': code = KeyEvent.VK_Q; break;

		case 'R': code = KeyEvent.VK_R; break;

		case 'S': code = KeyEvent.VK_S; break;

		case 'T': code = KeyEvent.VK_T; break;

		case 'U': code = KeyEvent.VK_U; break;

		case 'X': code = KeyEvent.VK_X; break;

		case 'Y': code = KeyEvent.VK_Y; break;

		case 'Z': code = KeyEvent.VK_Z; break;

		}
		
		return code;
		

	}

	
	public void languageChanged(LanguageChangedEvent event) {

		onLoadLanguage();

	}

	
	public void setCaption(Tag tag) {

		
		this.caption = tag;
		
		onLoadLanguage();
		

	}
	

	public Tag getCaption() {

		return caption;

	}

	
}