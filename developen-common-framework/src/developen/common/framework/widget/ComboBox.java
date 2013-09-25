package developen.common.framework.widget;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;

@SuppressWarnings("rawtypes")
public class ComboBox extends JComboBox implements Nameable, LanguageChangeListener {


	private static final long serialVersionUID = 7099415485484189062L;

	private Tag caption;


	public ComboBox(Tag caption){


		setCaption(caption);

		init();


	}


	@SuppressWarnings("unchecked")
	public ComboBox(Tag caption, Object[] list){


		super(list);

		setCaption(caption);

		init();


	}


	private void init() {


		I18N.addLanguageChangeListener(this);

		setPreferredSize(new Dimension(115, UIConstants.DEFAULT_FIELD_HEIGHT));

		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode()==KeyEvent.VK_ENTER)

					if (!ComboBox.this.isPopupVisible())

						ComboBox.this.transferFocus();

			}

		});


	}


	public void setCaption(Tag caption) {


		this.caption = caption;

		onLoadLanguage();


	}


	public Tag getCaption() {

		return caption;

	}


	protected void onLoadLanguage() {


		if (getCaption()!=null && !getCaption().getKey().isEmpty())

			if (getCaption().hasToolTip())

				setToolTipText(getCaption().getToolTip());


	}

	
	public void languageChanged(LanguageChangedEvent e) {

		onLoadLanguage();

	}

	
}