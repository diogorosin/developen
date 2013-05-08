package developen.common.framework.widget;

import java.awt.Color;
import java.awt.Cursor;

import developen.common.framework.utils.Tag;

public class Link extends Label {


	private static final long serialVersionUID = 3752838443493396085L;

	
	public Link(){
		
		super();
		
	}
	
	
	public Link(Tag text){
		
		super(text);
		
	}
	

	public void init(){
		
		
		super.init();
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		setForeground(new Color(0,0,0));
		
		
	}
	

}