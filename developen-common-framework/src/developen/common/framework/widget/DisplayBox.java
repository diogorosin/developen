package developen.common.framework.widget;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import developen.common.framework.utils.Tag;

public class DisplayBox extends JButton implements Nameable {

	
	private static final long serialVersionUID = 5928828781385976808L;
	
	private Tag caption;
	
	
	public DisplayBox(Tag caption){
		
		
		this.caption = caption;
		
		init();
		
		
	}
	
	
	protected void init(){
	
		
		setPreferredSize(new Dimension(100,26));
		
		setHorizontalAlignment(SwingConstants.LEFT);	
		
		
	}
	
	
	public void setCaption(Tag caption) {
	
		this.caption = caption;
		
	}

	
	public Tag getCaption() {

		return caption;
		
	}
	
	
}