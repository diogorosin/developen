package developen.common.framework.widget;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Tree extends JTree {

	
	private static final long serialVersionUID = 3482394001774779842L;

	
	public Tree(Object[] hierarchy){
		
		super(processHierarchy(hierarchy));
		
	}
	
	
	public static DefaultMutableTreeNode processHierarchy(Object[] hierarchy){
		
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);
		
		DefaultMutableTreeNode child;
		
		for (int i=1; i < hierarchy.length; i++){
			
			Object nodeSpecifier = hierarchy[i];
			
			if (nodeSpecifier instanceof Object[])
				
				child = processHierarchy((Object[])nodeSpecifier);
			
			else
				
				child = new DefaultMutableTreeNode(nodeSpecifier);
				
			node.add(child);
			
		}
		
		return node;
		
		
	}
	

}