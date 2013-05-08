package developen.client.framework.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.Table;


public abstract class TableSearchView extends SearchView {


	private static final long serialVersionUID = -3407326248053126030L;


	public TableSearchView(SearchController controller) {

		super(controller);

	}


	protected abstract Table getResultComponent();


	public void init(){


		super.init();

		getScrollPane().getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			public void adjustmentValueChanged(AdjustmentEvent e) {

				if (e.getAdjustable().getVisibleAmount() + 

						e.getAdjustable().getValue() ==

						e.getAdjustable().getMaximum())

					try {

						getController().more();

					} catch (Exception exception) {

						Messenger.show(exception);

					}

			}

		});

		getResultComponent().getActionMap().put(SearchAction.SELECT, new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {

				try {

					getController().select(getSelectedRows());

				} catch (Exception exception) {

					Messenger.show(exception);

				}

			}

		});

		getResultComponent().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), SearchAction.SELECT);

		getResultComponent().addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if ((Character.isLetter(e.getKeyChar()) 

						|| Character.isDigit(e.getKeyChar())) 

						&& !(e.getModifiers() == KeyEvent.ALT_MASK)) {

					setSearchFieldVisible(true);

					getSearchField().setText(String.valueOf(e.getKeyChar()));

					getSearchField().setChecked(false);

				} else 
 
					if ((e.getKeyCode() == KeyEvent.VK_DOWN 

					|| e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) 

					&& (getResultComponent().isRowSelected(getResultComponent().getRowCount() - 1)))

						try {

							getController().more();

						} catch (Exception exception) {

							Messenger.show(exception);

						}

			}

		});

		getResultComponent().addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {

				if (evt.getClickCount() == 2)

					try {

						getController().select(getSelectedRows());

					} catch (Exception exception) {

						Messenger.show(exception);

					}

			}

		});

	}


	protected int[] getSelectedRows() {

		return getResultComponent().getSelectedRows();

	}


}