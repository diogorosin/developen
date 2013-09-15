package developen.client.framework.widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import developen.common.framework.widget.Link;


public class DBLink extends Link {


	private static final long serialVersionUID = 5629681998655249452L;

	
	public DBLink(){

		
		setFont(new Font(getFont().getName(), Font.PLAIN, getFont().getSize()));
		
		setForeground(Color.BLUE);
		
		this.addMouseListener(new MouseAdapter() {	


		    Font original;

		    @SuppressWarnings("unchecked")
			public void mouseEntered(MouseEvent e) {

		    	
		    	original = e.getComponent().getFont();
		        
				Map<TextAttribute, Integer> attributes = (Map<TextAttribute, Integer>) original.getAttributes();
		        
		        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		        
		        e.getComponent().setFont(original.deriveFont(attributes));

		        
		    }


		    public void mouseExited(MouseEvent e) {
		    	
		        e.getComponent().setFont(original);
		        
		    }
		    

			public void mouseClicked(MouseEvent arg0) {

				if (isEnabled())
					
					if ((DBLink.this.getLabelFor() instanceof DBSearchableField) && 
							
							((DBSearchableField)DBLink.this.getLabelFor()).getSearch() != null)
						
						((DBSearchableField)DBLink.this.getLabelFor()).getSearch().openSearchView(((DBSearchableField)DBLink.this.getLabelFor()).getDesktopPane());

			}
			

		});

		
	}
	

}