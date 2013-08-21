package developen.common.framework.widget;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JSlider;

import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;

public class Slider extends JSlider implements Nameable, LanguageChangeListener {


	private static final long serialVersionUID = 7921224875562168412L;
	
	private Tag caption;


	public Slider(Tag caption){

		
		super();
		
		setCaption(caption);

		init();


	}


	public Slider(Tag caption, int horientation, int min, int max, int value){


		super(horientation, min, max, value);

		setCaption(caption);

		init();


	}


	private void init() {


		I18N.addLanguageChangeListener(this);

		setPreferredSize(new Dimension(115, 24));

		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode()==KeyEvent.VK_ENTER)

						Slider.this.transferFocus();

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