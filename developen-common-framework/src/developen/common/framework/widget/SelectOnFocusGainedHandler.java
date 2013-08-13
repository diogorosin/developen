package developen.common.framework.widget;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;


public class SelectOnFocusGainedHandler extends FocusAdapter {

	
	public void focusGained(FocusEvent e) {


		Component comp = e.getComponent();

		if (comp instanceof JTextComponent) {

			final JTextComponent textComponent = (JTextComponent) comp;

			new Thread(new Runnable() {

				public void run() {

					try {

						Thread.sleep(25);

					} catch (InterruptedException ex) {

					}

					SwingUtilities.invokeLater(new Runnable() {

						public void run() {

							textComponent.selectAll();

						}

					});

				}

			}).start();

			
		}   

	}

}