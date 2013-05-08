package developen.client.framework.widget;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import developen.common.framework.widget.Link;


public class DBLink extends Link {


	private static final long serialVersionUID = 5629681998655249452L;

	
	public DBLink(){

		
		setFont(new Font(getFont().getName(), Font.BOLD, getFont().getSize()));
		
		this.addMouseListener(new MouseAdapter() {	

			public void mouseClicked(MouseEvent arg0) {

				if (isEnabled())
					
					if ((DBLink.this.getLabelFor() instanceof DBField) && 
							
							((DBField)DBLink.this.getLabelFor()).getSearch() != null)
						
						((DBField)DBLink.this.getLabelFor()).getSearch().openSearchView(((DBField)DBLink.this.getLabelFor()).getDesktopPane());

			}

		});

		
	}
	

}