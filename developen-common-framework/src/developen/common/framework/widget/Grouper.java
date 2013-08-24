package developen.common.framework.widget;

import java.awt.FlowLayout;

import javax.swing.JComponent;

import developen.common.framework.utils.Tag;

public class Grouper extends JComponent implements Nameable{


	private static final long serialVersionUID = -4099532452078671910L;

	private JComponent component1;
	
	private JComponent component2;

	private Tag caption;
	

	public Grouper(JComponent component1, JComponent component2){

		
		if (component1 instanceof Nameable)
			
			setCaption(((Nameable) component1).getCaption());
		
		setComponent1(component1);
		
		setComponent2(component2);

		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		add(getComponent1());
		
		add(getComponent2());


	}


	public Grouper(JComponent component1, JComponent component2, Tag caption){

		
		setComponent1(component1);
		
		setComponent1(component2);
		
		setCaption(caption);

		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		add(getComponent1());
		
		add(getComponent2());


	}

	
	public JComponent getComponentAtFirst() {

		return getComponent1();

	}


	public void requestFocus(){


		if (getComponentAtFirst().isFocusable())

			getComponentAtFirst().requestFocus();


	}


	public void setCaption(Tag fieldName) {

		this.caption = fieldName;

	}


	public Tag getCaption() {

		return caption;

	}

	
	public JComponent getComponent1() {
		
		return component1;
		
	}


	public void setComponent1(JComponent component1) {
		
		this.component1 = component1;
		
	}


	public JComponent getComponent2() {
		
		return component2;
		
	}


	public void setComponent2(JComponent component2) {
		
		this.component2 = component2;
		
	}


}