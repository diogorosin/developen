package developen.common.framework.widget;


import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class MeuDefaultTreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {

	private static final long serialVersionUID = -2333573171130288766L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

//		if(value == valorEspecial){

		//	setIcon(new ImageIcon(getClass().getResource("iconeEspecial.png")));
		
			setIcon(null);
			
			setLeafIcon(null);
			
			setOpenIcon(null);
			
			setClosedIcon(null);

	//	}

		return this;

	}

}
