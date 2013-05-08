package developen.common.framework.widget;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;


public class ExtendedLayout extends JPanel {

	
	private static final long serialVersionUID = -418214394819979216L;
	
	private Insets insets = new Insets(5, 5, 5, 5);
	
	
	public ExtendedLayout(){

		setLayout(new GridBagLayout());

	}

	
	public ExtendedLayout(Insets insets){

		
		setLayout(new GridBagLayout());
		
		this.insets = insets;
		
		
	}

	
	public void add(JComponent component){

		
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.fill = GridBagConstraints.BOTH;
		
		cons.anchor = GridBagConstraints.NORTHWEST;
		
		cons.insets = getBorderInsets();
		
		cons.weighty = 1;
		
		cons.weightx = 1;
		
		cons.gridheight = GridBagConstraints.REMAINDER;
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		
		add(component, cons);
		

	}

	
	public Insets getBorderInsets() {
		
		return insets;
		
	}

	
	public void setBorderInsets(Insets insets) {
		
		this.insets = insets;
		
	}

	
}