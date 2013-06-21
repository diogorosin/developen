package developen.client.framework.mvc;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import developen.client.framework.action.CancelAction;
import developen.client.framework.action.SelectAction;
import developen.client.framework.i18n.RefreshTag;
import developen.client.framework.i18n.SearchTag;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.SearchState;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Button;
import developen.common.framework.widget.ButtonPanel;
import developen.common.framework.widget.ButtonPanelAligment;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.InternalFrame;
import developen.common.framework.widget.SearchField;

public abstract class SearchView extends InternalFrame implements CheckListener, SystemPersonListener {


	private static final long serialVersionUID = -8901109365984063505L;

	private JScrollPane scrollPane;

	private ExtendedPanel northLayout;
	
	private ExtendedPanel centerLayout;
	
	private ButtonPanel buttonLayout;
	
	private SearchField searchField;

	private JLabel refreshLabel;
	
	private Button selectButton;
	
	private Button cancelButton;

	private SelectAction selectAction;
	
	private CancelAction cancelAction;
	

	public SearchView(SearchController controller) {

		super(controller);

	}

	
	public void init() {
	
		
		setIconifiable(false);

		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

		addInternalFrameListener(new InternalFrameAdapter() {
			
			public void internalFrameClosing(InternalFrameEvent arg0) {
				
				try {
					
					getController().cancel();
					
				} catch (Exception exception) {
					
					Messenger.show(exception);
					
				}
				
			}
			
		});

		getActionMap().put(SearchAction.CANCEL, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					getController().cancel();
					
				} catch (Exception exception) {
					
					Messenger.show(exception);
					
				}	
				
			}
			
		});

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), SearchAction.CANCEL);

		getActionMap().put(SearchAction.REFRESH, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					getScrollPane().getVerticalScrollBar().setValue(0);
					
					getController().reset();
					
				} catch (Exception exception) {
					
					Messenger.show(exception);
					
				}	
				
			}
			
		});

		getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				
				KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), 
				
				SearchAction.REFRESH);

		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(getNorthLayout(), BorderLayout.NORTH);
		
		getContentPane().add(getCenterLayout(), BorderLayout.CENTER);
		
		getContentPane().add(getButtonLayout(), BorderLayout.SOUTH);
		

	}

	
	public Tag getInternalFrameTitle(){

		return new SearchTag();

	}

	
	public ImageIcon getInternalFrameIcon() {

		return new SearchTag().getSmallIcon();

	}

	
	public SearchController getController(){

		return (SearchController) super.getController();

	}

	
	public void setController(SearchController controller){

		super.setController(controller);

	}


	protected abstract JComponent getResultComponent();
	

	protected abstract int[] getSelectedRows();
	

	public void onCheck(CheckEvent event) throws Exception {

		
		if (event.getCheckable() == getSearchField())
			
			getController().changeSearchProperty(getSearchField().getText());
		

	}
	

	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		if (evt.getPropertyName().equals("ModelState")){

			SearchState newValue = (SearchState) evt.getNewValue();

			if ((newValue.equals(SearchState.CANCELED)) 
					
					|| (newValue.equals(SearchState.SELECTED))){

				getScrollPane().getVerticalScrollBar().setValue(0);
				
				setSearchFieldVisible(false);
				
				setVisible(false);
				
				getDesktopPane().remove(this);
				
				dispose();

			} else 

				if (newValue.equals(SearchState.BROWSING))
					
					if (getResultComponent() != null){
						
						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								
								getResultComponent().requestFocus();
								
							}
							
						});
						
					}

		} else 

			if (evt.getPropertyName().equals(SearchController.SEARCH_PROPERTY)){
				
				getSearchField().setText((String) evt.getNewValue());
				
				setSearchFieldVisible(false);

			}

		
	}


	public ExtendedPanel getNorthLayout() {

		
		if (northLayout == null){
			
			northLayout = new ExtendedPanel(new Insets(5, 5, 0, 5));
			
			northLayout.add(getSearchField());
			
			northLayout.setVisible(false);
			
		}
		
		return northLayout;
		

	}

	
	public SearchField getSearchField(){

		
		if (searchField==null){
			
			searchField = new SearchField(null);
			
			searchField.addCheckListener(this);
			
		}
		
		return searchField;
		

	}

	
	public void setSearchFieldVisible(boolean visible){

		
		getNorthLayout().setVisible(visible);
		
		if (visible && getSearchField().isFocusable())
			
			getSearchField().requestFocus();
		

	}

	
	public boolean isSearchFieldVisible(){

		return getNorthLayout().isVisible();

	}

	
	public ExtendedPanel getCenterLayout() {

		
		if (centerLayout == null){
			
			centerLayout = new ExtendedPanel();
			
			centerLayout.add(getScrollPane());
			
		}
		
		return centerLayout;
		

	}

	protected ButtonPanel getButtonLayout() {

		
		if (buttonLayout == null){
			
			buttonLayout = new ButtonPanel();
			
			buttonLayout.add(getSelectButton(), ButtonPanelAligment.RIGHT);
			
			buttonLayout.add(getCancelButton(), ButtonPanelAligment.RIGHT);
			
		}
		
		return buttonLayout;
		

	}

	protected JScrollPane getScrollPane() {
		

		if (scrollPane == null) {
			
			scrollPane = new JScrollPane(getResultComponent());
			
			scrollPane.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, getRefreshButton());
			
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
		}
		
		return scrollPane;
		

	}

	
	protected Button getSelectButton() {

		
		if (selectButton == null)
			
			selectButton = new Button(getSelectAction());
		
		return selectButton;
		

	}

	
	protected Button getCancelButton() {

		
		if (cancelButton == null)
			
			cancelButton = new Button(getCancelAction());
		
		return cancelButton;
		

	}
	

	protected JLabel getRefreshButton() {

		
		if (refreshLabel == null){
			
			refreshLabel = new JLabel(new RefreshTag().getSmallIcon());
			
			refreshLabel.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {

					try {
						
						getScrollPane().getVerticalScrollBar().setValue(0);
						
						getController().reset();
						
					} catch (Exception exception) {
						
						Messenger.show(exception);
						
					}

				}
				
			});

		}
		
		return refreshLabel;

		
	}

	
	public CancelAction getCancelAction(){

		
		if (cancelAction == null){
			
			cancelAction = new CancelAction() {
				
				private static final long serialVersionUID = 6263648975218065018L;

				public void actionPerformed(ActionEvent e) {
					
					try {
						
						getController().cancel();
						
					} catch (Exception exception) {
						
						Messenger.show(exception);
						
					}
					
				}
				
			};	
			
			getController().addView(cancelAction);
			
		}
		
		return cancelAction;

		
	}

	public SelectAction getSelectAction() {

		
		if (selectAction == null){
			
			selectAction = new SelectAction() {
				
				private static final long serialVersionUID = 77810448785584808L;
				
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						getController().select(getSelectedRows());
						
					} catch (Exception exception) {
						
						Messenger.show(exception);
						
					}
					
				}
				
				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {
					
					if (e.getPropertyName().equals(SearchController.RESULTED_ROWS_PROPERTY))
						
						selectAction.setEnabled(((List<Object>)e.getNewValue()).size() > 0);
					
				}
				
			};
			
			getController().addView(selectAction);
			
		}
		
		return selectAction;
		

	}

	
	public void onLogout(SystemPersonEvent event) throws Exception{
		
		getController().cancel();

	}
	
	
	public void onLogin(SystemPersonEvent event){
		
		

	}

	
}