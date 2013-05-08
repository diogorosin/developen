package developen.client.framework.widget;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.common.framework.widget.Label;
import developen.common.framework.widget.Nameable;
import developen.common.framework.widget.RowLayout;

public class DBRowLayout extends RowLayout {

	
	private static final long serialVersionUID = 1073210692637667197L;

	
	public DBRowLayout(){

		super();
		
	}

	
	public DBRowLayout(int labelWidth){

		super(labelWidth);
		
	}

	
	public void add(JComponent field){

		
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.insets = field instanceof JCheckBox ? new Insets(2,2,2,12) : new Insets(2,2,2,12);
		
		cons.anchor = field instanceof JCheckBox ? GridBagConstraints.NORTHEAST : GridBagConstraints.WEST;
		
		cons.fill = GridBagConstraints.NONE;
		
		cons.weightx = 0;
		
		Label label = ((field instanceof DBField) && (((DBField)field).getSearch() != null)) ? new DBLink() : new Label();
		
		if (field instanceof Nameable && !(field instanceof JCheckBox))
			
			label.setCaption(((Nameable) field).getCaption());
		
		label.setLabelFor(field);
		
		label.setPreferredSize(new Dimension(getLabelWidth(), label.getPreferredSize().height));
		
		label.setHorizontalAlignment(SwingConstants.RIGHT);

		add(label, cons);
		
		cons.fill = field instanceof JCheckBox ? GridBagConstraints.BOTH : GridBagConstraints.NONE;
		
		cons.anchor = field instanceof JCheckBox ? GridBagConstraints.WEST : GridBagConstraints.NORTHWEST;
		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		
		add(field, cons);
		
		
	}
	
	
	public void add(JComponent field1, JComponent field2){

		//FIELD 1		
		GridBagConstraints cons1 = new GridBagConstraints();
		
		cons1.insets = field1 instanceof JCheckBox ? new Insets(2,2,2,12) : new Insets(2,2,2,12);
		
		cons1.anchor = field1 instanceof JCheckBox ? GridBagConstraints.NORTHEAST : GridBagConstraints.WEST;
		
		cons1.fill = GridBagConstraints.NONE;
		
		cons1.weightx = 0;
		
		cons1.gridwidth = 1;
		
		Label label1 = ((field1 instanceof DBField) && (((DBField)field1).getSearch() != null)) ? new DBLink() : new Label();
		
		if (field1 instanceof Nameable && !(field1 instanceof JCheckBox))
			
			label1.setCaption(((Nameable)field1).getCaption());
		
		label1.setLabelFor(field1);
		
		label1.setPreferredSize(new Dimension(getLabelWidth(), label1.getPreferredSize().height));
		
		label1.setHorizontalAlignment(SwingConstants.RIGHT);

		add(label1, cons1);
		
		cons1.fill = field1 instanceof JCheckBox ? GridBagConstraints.BOTH : GridBagConstraints.NONE;
		
		cons1.anchor = field1 instanceof JCheckBox ? GridBagConstraints.WEST : GridBagConstraints.NORTHWEST;
		
		cons1.weightx = 1;
		
		cons1.gridwidth = 1;

		add(field1, cons1);
		
		//FIELD 2
		GridBagConstraints cons2 = new GridBagConstraints();
		
		cons2.insets = field2 instanceof JCheckBox ? new Insets(2,2,2,12) : new Insets(2,2,2,12);
		
		cons2.anchor = field2 instanceof JCheckBox ? GridBagConstraints.NORTHEAST : GridBagConstraints.WEST;
		
		cons2.fill = GridBagConstraints.NONE;
		
		cons2.weightx = 0;
		
		cons2.gridwidth = 1;
		
		Label label2 = ((field2 instanceof DBField) && (((DBField)field2).getSearch() != null)) ? new DBLink() : new Label();
		
		if (field2 instanceof Nameable && !(field2 instanceof JCheckBox))
			
			label2.setCaption(((Nameable)field2).getCaption());
		
		label2.setLabelFor(field2);
		
		label2.setPreferredSize(new Dimension(getLabelWidth(), label1.getPreferredSize().height));
		
		label2.setHorizontalAlignment(SwingConstants.RIGHT);

		add(label2, cons2);
		
		cons2.fill = field2 instanceof JCheckBox ? GridBagConstraints.BOTH : GridBagConstraints.NONE;
		
		cons2.anchor = field2 instanceof JCheckBox ? GridBagConstraints.WEST : GridBagConstraints.NORTHWEST;
		
		cons2.weightx = 1;
		
		cons2.gridwidth = GridBagConstraints.REMAINDER;

		add(field2, cons2);

		
	}

}