package developen.common.framework.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import developen.common.framework.utils.Tag;

public class RowLayout extends JPanel {

	
	private static final long serialVersionUID = -5551618573256246879L;

	private int labelWidth = 100;

	
	public RowLayout(){

		setLayout(new GridBagLayout());

	}

	
	public RowLayout(int labelWidth){

		
		setLayout(new GridBagLayout());
		
		setLabelWidth(labelWidth);
		

	}
	

	public void add(JComponent field){

		
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.fill = GridBagConstraints.NONE;
		
		cons.anchor = field instanceof JCheckBox ? GridBagConstraints.NORTHEAST : GridBagConstraints.EAST;
		
		cons.insets = field instanceof JCheckBox ? new Insets(5,2,2,10) : new Insets(2,2,2,12);
		
		cons.weightx = 0;

		Label label = new Label();
		
		if (field instanceof Nameable && !(field instanceof JCheckBox))
			
			label.setCaption(((Nameable) field).getCaption());

		label.setLabelFor(field);
		
		label.setPreferredSize(new Dimension(labelWidth, label.getPreferredSize().height));
		
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		add(label, cons); 

		cons.fill = field instanceof JCheckBox ? GridBagConstraints.BOTH : GridBagConstraints.NONE;
		
		cons.insets = field instanceof JCheckBox ? new Insets(0,-2,0,0) : new Insets(2,2,2,2);
		
		cons.anchor = field instanceof JCheckBox ? GridBagConstraints.WEST : GridBagConstraints.NORTHWEST;
		
		cons.weightx = 1;
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		
		add(field, cons);
		

	}
	
	
	public void add(JComponent field1, JComponent field2){
		
	
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.insets = new Insets(2,2,2,12);
		
		cons.fill = GridBagConstraints.NONE;
		
		cons.anchor = GridBagConstraints.WEST;
		
		cons.weightx = 0;
		
		cons.gridwidth = 1;
		
		Label label1 = new Label();
		
		if (field1 instanceof Nameable && !(field1 instanceof JCheckBox))
			
			label1.setCaption(((Nameable) field1).getCaption());
		
		label1.setLabelFor(field1);
		
		label1.setPreferredSize(new Dimension(labelWidth, label1.getPreferredSize().height));
		
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		add(label1, cons);
		
		cons.weightx = 1;
		
		cons.gridwidth = 1;
		
		cons.fill = GridBagConstraints.NONE;
		
		add(field1, cons);

		cons.fill = GridBagConstraints.NONE;
		
		cons.weightx = 0;
		
		cons.gridwidth = 1;
		
		Label label2 = new Label();
		
		if (field2 instanceof Nameable && !(field2 instanceof JCheckBox))
			
			label2.setCaption(((Nameable) field2).getCaption());
		
		label2.setLabelFor(field2);
		
		label2.setPreferredSize(new Dimension(labelWidth, label2.getPreferredSize().height));
		
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		add(label2, cons);

		cons.weightx = 1;
		
		cons.fill = GridBagConstraints.NONE;
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		
		add(field2, cons);
		

	}

	
	public int getLabelWidth() {

		return labelWidth;

	}

	
	public void setLabelWidth(int labelWidth) {

		this.labelWidth = labelWidth;

	}

	
	public void addSeparator(Tag tag){
		

		GridBagConstraints cons = new GridBagConstraints();
		
		cons.insets = new Insets(4,0,4,12);
		
		cons.anchor = GridBagConstraints.NORTHEAST;
		
		cons.fill = GridBagConstraints.NONE;
		
		cons.weightx = 0;
		
		cons.fill = GridBagConstraints.BOTH;
		
		cons.anchor = GridBagConstraints.CENTER;
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		
		Label label = new Label(tag);
		
		label.setHorizontalAlignment(JLabel.LEFT);
		
		label.setHorizontalTextPosition(JLabel.LEFT);
		
		label.setVerticalTextPosition(JLabel.BOTTOM);
		
		label.setForeground(Color.GRAY);
		
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

		label.setLayout(new BorderLayout());
		
		add(label, cons);

		
	}

	
}