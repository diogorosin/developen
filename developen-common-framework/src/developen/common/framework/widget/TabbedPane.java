package developen.common.framework.widget;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

public class TabbedPane extends JTabbedPane {


	private static final long serialVersionUID = 5428622131534848586L;

	public static final String NEXT = "next";

	public static final String PREV = "prev";


	public TabbedPane(){

		
		super();

		getActionMap().put(TabbedPane.NEXT, new AbstractAction() {

			private static final long serialVersionUID = 3450667814406972694L;

			public void actionPerformed(ActionEvent arg0) {

				int nextPosition = getSelectedIndex() + 1;

				if (getTabCount() > nextPosition)

					setSelectedIndex(nextPosition);

				else 

					setSelectedIndex(0);

			}

		});

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

				KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0), 

				TabbedPane.NEXT);
		
		
		getActionMap().put(TabbedPane.PREV, new AbstractAction() {

			private static final long serialVersionUID = -2947099766275928575L;

			public void actionPerformed(ActionEvent arg0) {

				int prevPosition = getSelectedIndex() - 1;

				if (prevPosition >= 0)

					setSelectedIndex(prevPosition);

				else 

					setSelectedIndex(getTabCount()-1);

			}

		});

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

				KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0), 

				TabbedPane.PREV);


	}


}
