package developen.common.framework.widget;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;


public class ButtonPanel extends JPanel{


	private static final long serialVersionUID = 2811646104378019371L;

	private JPanel east;

	private JPanel center;

	private JPanel west;


	public ButtonPanel(){


		setLayout(new BorderLayout());

		add(getEast(), BorderLayout.EAST);

		add(getCenter(), BorderLayout.CENTER);

		add(getWest(), BorderLayout.WEST);

		JPanel panel = new JPanel();

		panel.setBackground(Color.LIGHT_GRAY);

		panel.setPreferredSize(new Dimension(1, 1));

		add(panel, BorderLayout.NORTH);


	}


	public JPanel getEast() {


		if (east==null)

			east = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		return east;


	}


	public JPanel getCenter() {


		if (center==null)

			center = new JPanel(new FlowLayout(FlowLayout.CENTER));

		return center;


	}


	public JPanel getWest() {


		if (west==null)

			west = new JPanel(new FlowLayout(FlowLayout.LEFT));

		return west;


	}


	public void add(Button button, ButtonPanelAligment aligment){


		switch (aligment) {

		case RIGHT: getEast().add(button);

		break;

		case CENTER: getCenter().add(button);

		break;

		case LEFT: getWest().add(button);

		break;

		default: getEast().add(button);

		break;

		}


	}


	public void removeButton(Button button){

		button.getParent().remove(button);
		
	}


}