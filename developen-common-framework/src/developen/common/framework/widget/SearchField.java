package developen.common.framework.widget;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import developen.common.framework.i18n.FindTag;
import developen.common.framework.utils.Tag;

public class SearchField extends TextField {


	private static final long serialVersionUID = 7170642132465521457L;

	private Label label;


	public SearchField(Tag caption) {

		super(caption);

	}


	public void init(){


		super.init();

		setSelectAllOnFocusGained(false);

		setLayout(new BorderLayout());

		add(getLabel(), BorderLayout.EAST);		


	}


	public Label getLabel(){


		if (label==null){

			label = new Label(new FindTag());

			label.setPreferredSize(new Dimension(16,26));

			label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			label.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent arg0) {

					if (isEnabled())

						try {

							check();

						} catch (Exception e) {

							e.printStackTrace();

						}

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