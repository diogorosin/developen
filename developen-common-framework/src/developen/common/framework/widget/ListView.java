package developen.common.framework.widget;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JList;
import javax.swing.ListModel;

public class ListView<E> extends JList<E> {
	

	private static final long serialVersionUID = 4417893814348025570L;
	

	public ListView(){
		
		
		super();
		
		init();
		
		
	}

	
	public ListView(ListModel<E> dataModel){
		
	
		super(dataModel);
		
		init();
		
		
	}

	
	public void init(){

		
		addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent event) {
				
				if (event.getKeyCode() == KeyEvent.VK_ENTER)
					
					transferFocus();
					
			}
			
		});

		
	}
	
	
}