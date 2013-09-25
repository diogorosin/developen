package developen.client.framework.widget;


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.framework.i18n.SearchTag;
import developen.client.framework.search.Search;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Label;

public class DBSearchField extends DBTextField implements DBSearchableField{


	private static final long serialVersionUID = 1417291743640322016L;

	private Search search;

	private Label label;


	public DBSearchField(Tag componentName) {

		super(componentName);

	}


	public DBSearchField(Tag componentName, String propertyName) {

		super(componentName, propertyName);

	}


	protected void init(){


		super.init();

		setLayout(new BorderLayout());

		add(getLabel(), BorderLayout.EAST);


	}


	public Search getSearch() {

		return search;

	}


	public void setSearch(Search search) {


		this.search = search;

		this.search.setComponent(this);


	}


	public JDesktopPane getDesktopPane() {


		JDesktopPane desktop = null;

		JComponent frame = (JComponent) this.getParent();

		while (frame.getParent() != null){

			frame = (JComponent) frame.getParent();

			if (frame instanceof JInternalFrame) 

				break;

		}

		if (frame instanceof JInternalFrame)

			desktop = ((JInternalFrame) frame).getDesktopPane();

		return desktop;


	}


	public String getFindByString() {

		return getText();

	}


	public Label getLabel(){


		if (label==null){

			label = new Label(new SearchTag());

			label.setPreferredSize(new Dimension(16,26));

			label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			label.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent arg0) {

					if (isEnabled())

						getSearch().openSearchView(getDesktopPane());

				}

			});

		}

		return label;


	}


	public void setEnabled(boolean b){


		super.setEnabled(b);

		getLabel().setEnabled(b);


	}


}