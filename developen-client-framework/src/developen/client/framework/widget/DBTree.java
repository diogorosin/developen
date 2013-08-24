package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import developen.client.framework.mvc.SelectionTransformer;
import developen.common.framework.widget.Tree;


public class DBTree extends Tree implements DBComponent {


	private static final long serialVersionUID = 6948187132585650055L;
	
	private SelectionTransformer selectionTransformer;
	
	private List<DefaultMutableTreeNode> nodes;
	
	private String propertyName;
	
	private boolean fixedValue;

	private Condition condition;
	

	public DBTree(Object[] hierarchy, String propertyName) {

		
		super(hierarchy);
		
		setPropertyName(propertyName);

		
	}

	
	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}
	

	public String getPropertyName() {

		return propertyName;

	}
	

	public void modelPropertyChanged(PropertyChangeEvent event) {

		setEnabled(getCondition().analyse(event, this));
		
	}

	
	public SelectionTransformer getSelectionTransformer() {

		
		if (selectionTransformer == null){
			
			selectionTransformer = new SelectionTransformer() {
				
				public List<Object> transform(List<Object> selection) {

					return selection;

				}
				
			};
			
		}
		
		return selectionTransformer;
		

	}

	
	public void setSelectionTransformer(SelectionTransformer resultTransformer) {

		this.selectionTransformer = resultTransformer;

	}
	

	public List<DefaultMutableTreeNode> getNodes() {

		
		if (nodes == null){

			nodes = new ArrayList<DefaultMutableTreeNode>();

			DefaultMutableTreeNode level1 = (DefaultMutableTreeNode) getModel().getRoot();

			if (!level1.isLeaf()){

				for (int a = 0; a < level1.getChildCount(); a++) {

					DefaultMutableTreeNode level2 = (DefaultMutableTreeNode) level1.getChildAt(a);

					if (!level2.isLeaf()){

						for (int b = 0; b < level2.getChildCount(); b++) {

							DefaultMutableTreeNode level3 = (DefaultMutableTreeNode) level2.getChildAt(b);

							if (!level3.isLeaf()){

								for (int c = 0; c < level3.getChildCount(); c++) {

									DefaultMutableTreeNode level4 = (DefaultMutableTreeNode) level3.getChildAt(c);

									if (!level4.isLeaf()){

										for (int d = 0; d < level4.getChildCount(); d++) {

											DefaultMutableTreeNode level5 = (DefaultMutableTreeNode) level4.getChildAt(d);

											if (!level5.isLeaf()){

												for (int e = 0; e < level5.getChildCount(); e++) {

													DefaultMutableTreeNode level6 = (DefaultMutableTreeNode) level5.getChildAt(e);

													if (!level6.isLeaf()){

														for (int f = 0; f < level6.getChildCount(); f++) {

															DefaultMutableTreeNode level7 = (DefaultMutableTreeNode) level6.getChildAt(f);

															if (!level7.isLeaf()){

																for (int g = 0; g < level7.getChildCount(); g++) {

																	DefaultMutableTreeNode level8 = (DefaultMutableTreeNode) level7.getChildAt(g);

																	if (!level8.isLeaf()){

																		for (int h = 0; h < level8.getChildCount(); h++) {

																			DefaultMutableTreeNode level9 = (DefaultMutableTreeNode) level8.getChildAt(h);

																			if (!level9.isLeaf()){

																				for (int i = 0; i < level9.getChildCount(); i++) {

																					DefaultMutableTreeNode level10 = (DefaultMutableTreeNode) level9.getChildAt(i);

																					if (!level10.isLeaf()){


																					
																					} else {

																						nodes.add(level10);		

																					}

																				}
																			
																			} else {

																				nodes.add(level9);		

																			}

																		}

																	} else {

																		nodes.add(level8);

																	}

																}

															} else {

																nodes.add(level7);		

															}

														}
														
													} else {

														nodes.add(level6);		

													}

												}

											} else {

												nodes.add(level5);		

											}

										}

									} else {

										nodes.add(level4);		

									}

								}

							} else {

								nodes.add(level3);		

							}

						}

					} else {

						nodes.add(level2);

					}

				}


			} else {

				nodes.add(level1);

			}

		}
		
		return nodes;
		

	}

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new EditingOrIncludingCondition();
		
		return condition;
		

	}

	
	public void setCondition(Condition condition){

		this.condition = condition;

	}


	public boolean isFixedValue() {
		
		return fixedValue;
		
	}


	public void setFixedValue(boolean fixedValue) {
		
		this.fixedValue = fixedValue;
		
	}
	

}