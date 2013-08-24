package developen.client.application.widget;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import developen.client.application.mvc.SystemPersonController;
import developen.common.commercial.mvc.SystemAction;
import developen.common.commercial.mvc.SystemPersonSystemAction;
import developen.common.commercial.mvc.SystemPersonSystemActionPK;
import developen.common.framework.widget.Action;
import developen.common.framework.widget.CheckTreeManager;

public class SystemPersonCheckTreeManager extends CheckTreeManager {

	
	private SystemPersonController controller;

	
	public SystemPersonCheckTreeManager(JTree tree, SystemPersonController controller) {

		
		super(tree);
		
		this.controller = controller;
		

	}

	
	public void mouseClicked(MouseEvent me){ 

		
		TreePath path = tree.getPathForLocation(me.getX(), me.getY());

		if (path==null)
			
			return; 

		if (me.getX()>tree.getPathBounds(path).x+hotspot)
			
			return;

		boolean selected = selectionModel.isPathSelected(path, true);
		
		selectionModel.removeTreeSelectionListener(this); 

		try{

			if(selected)
				
				selectionModel.removeSelectionPath(path);
			
			else
				
				selectionModel.addSelectionPath(path);

		} finally{

			selectionModel.addTreeSelectionListener(this);
			
			tree.treeDidChange();

		} 

		TreePath[] checkedPaths = getSelectionModel().getSelectionPaths();
		
		ArrayList<SystemPersonSystemAction> personActions = new ArrayList<SystemPersonSystemAction>();

		for (TreePath treePath : checkedPaths)
			
			for (SystemPersonSystemAction personAction : getPersonActionsFromPath(treePath))
				
				personActions.add(personAction);

		try {
			
			getController().changeActionsProperty(personActions);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		

	}

	
	public SystemPersonController getController() {

		return controller;

	}

	
	public void setController(SystemPersonController controller) {

		this.controller = controller;

	} 

	
	public List<SystemPersonSystemAction> getPersonActionsFromPath(TreePath path){

		
		List<SystemPersonSystemAction> newList = new ArrayList<SystemPersonSystemAction>();

		DefaultMutableTreeNode node1 = (DefaultMutableTreeNode) path.getLastPathComponent();

		if (!node1.isLeaf()){

			for (int a = 0; a < node1.getChildCount(); a++) {

				DefaultMutableTreeNode node2 = (DefaultMutableTreeNode) node1.getChildAt(a);

				if (!node2.isLeaf()){		

					for (int b = 0; b < node2.getChildCount(); b++){

						DefaultMutableTreeNode node3 = (DefaultMutableTreeNode) node2.getChildAt(b);

						if (!node3.isLeaf()){		

							for (int c = 0; c < node3.getChildCount(); c++){

								DefaultMutableTreeNode node4 = (DefaultMutableTreeNode) node3.getChildAt(c);

								if (!node4.isLeaf()){								

									for (int d = 0; d < node4.getChildCount(); d++){

										DefaultMutableTreeNode node5 = (DefaultMutableTreeNode) node4.getChildAt(d);

										if (!node5.isLeaf()){

											for (int e = 0; e < node5.getChildCount(); e++){		

												DefaultMutableTreeNode node6 = (DefaultMutableTreeNode) node5.getChildAt(e);		

												if (!node6.isLeaf()){

													for (int f = 0; f < node6.getChildCount(); f++){		

														DefaultMutableTreeNode node7 = (DefaultMutableTreeNode) node6.getChildAt(f);		

														if (!node7.isLeaf()){

															for (int g = 0; g < node7.getChildCount(); g++){		

																DefaultMutableTreeNode node8 = (DefaultMutableTreeNode) node7.getChildAt(g);		

																if (!node8.isLeaf()){

																	for (int h = 0; h < node8.getChildCount(); h++){		

																		DefaultMutableTreeNode node9 = (DefaultMutableTreeNode) node8.getChildAt(h);		

																		if (!node9.isLeaf()){

																			for (int i = 0; i < node9.getChildCount(); i++){		

																				DefaultMutableTreeNode node10 = (DefaultMutableTreeNode) node9.getChildAt(i);		

																				if (!node10.isLeaf()){


																				} else {
																					
																					if (node10.getUserObject() instanceof Action)
																						
																						newList.add(
																								
																								new SystemPersonSystemAction(
																										
																										new SystemPersonSystemActionPK(getController().getModel(),
																												
																												new SystemAction(node10.getUserObject().getClass().getName()))));
																					
																				}

																			}
																			
																		} else {
																			
																			if (node9.getUserObject() instanceof Action)
																				
																				newList.add(
																						
																						new SystemPersonSystemAction(
																								
																								new SystemPersonSystemActionPK(getController().getModel(),
																										
																										new SystemAction(node9.getUserObject().getClass().getName()))));
																			
																		}

																	}
																	
																} else {
																	
																	if (node8.getUserObject() instanceof Action)
																		
																		newList.add(
																				
																				new SystemPersonSystemAction(
																						
																						new SystemPersonSystemActionPK(getController().getModel(),
																								
																								new SystemAction(node8.getUserObject().getClass().getName()))));
																	
																}

															}
															
														} else {
															
															if (node7.getUserObject() instanceof Action)
																
																newList.add(
																		
																		new SystemPersonSystemAction(
																				
																				new SystemPersonSystemActionPK(getController().getModel(),
																						
																						new SystemAction(node7.getUserObject().getClass().getName()))));
															
														}

													}

												} else {
													
													if (node6.getUserObject() instanceof Action)
														
														newList.add(
																
																new SystemPersonSystemAction(
																		
																		new SystemPersonSystemActionPK(getController().getModel(),
																				
																				new SystemAction(node6.getUserObject().getClass().getName()))));
													
												}

											}

										} else {

											if (node5.getUserObject() instanceof Action)
												
												newList.add(
														
														new SystemPersonSystemAction(
																
																new SystemPersonSystemActionPK(getController().getModel(),
																		
																		new SystemAction(node5.getUserObject().getClass().getName()))));

										}

									}

								} else {

									if (node4.getUserObject() instanceof Action)
										
										newList.add(
												
												new SystemPersonSystemAction(
														
														new SystemPersonSystemActionPK(getController().getModel(),
																
																new SystemAction(node4.getUserObject().getClass().getName()))));

								}

							} 

						} else {

							if (node3.getUserObject() instanceof Action)
								
								newList.add(
										
										new SystemPersonSystemAction(
												
												new SystemPersonSystemActionPK(getController().getModel(),
														
														new SystemAction(node3.getUserObject().getClass().getName()))));

						}

					}

				} else {

					if (node2.getUserObject() instanceof Action)
						
						newList.add(
								
								new SystemPersonSystemAction(
										
										new SystemPersonSystemActionPK(getController().getModel(),
												
												new SystemAction(node2.getUserObject().getClass().getName()))));

				}

			}			

		} else {

			if (node1.getUserObject() instanceof Action)
				
				newList.add(
						
						new SystemPersonSystemAction(
								
								new SystemPersonSystemActionPK(getController().getModel(),
										
										new SystemAction(node1.getUserObject().getClass().getName()))));

		}

		return newList;

		
	}

	
}