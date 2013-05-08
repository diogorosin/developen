package developen.client.application.widget;

import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import developen.client.application.mvc.SystemPersonController;
import developen.client.framework.widget.DBTree;

public class SystemPersonDBTree extends DBTree {


	private static final long serialVersionUID = 88423201705827296L;
	
	private SystemPersonCheckTreeManager checkManager;
	

	public SystemPersonDBTree(Object[] hierarchy, String propertyName, SystemPersonController controller) {

		
		super(hierarchy, propertyName);
		
		setCheckManager(new SystemPersonCheckTreeManager(this, controller));
		

	}

	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		super.modelPropertyChanged(evt);

		if (evt.getPropertyName().equals(getPropertyName())){

			try {

				List<Object> list = getSelectionTransformer().transform((List<Object>)evt.getNewValue());
				
				getCheckManager().getSelectionModel().clearSelection();

				for (Object object : list) {

					String objectClassName = (String) object;

					for (DefaultMutableTreeNode node : getNodes()){

						if (node.getUserObject().getClass().getName().equals(objectClassName)){

							TreePath path = new TreePath(node.getPath());
							
							getCheckManager().getSelectionModel().addSelectionPath(path);

						}

					}

				}

				treeDidChange();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		
	}

	
	public SystemPersonCheckTreeManager getCheckManager() {

		return checkManager;

	}

	
	public void setCheckManager(SystemPersonCheckTreeManager checkManager) {

		this.checkManager = checkManager;

	}

	
}
