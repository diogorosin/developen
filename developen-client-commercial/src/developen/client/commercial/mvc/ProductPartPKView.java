package developen.client.commercial.mvc;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.commercial.search.ProductSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.PartTag;
import developen.common.commercial.i18n.ProductTag;
import developen.common.commercial.mvc.Product;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;

public class ProductPartPKView extends DBRowPanel implements View, CheckListener{


	private static final long serialVersionUID = -6800871769407844131L;

	private ProductPartPKController controller;

	private DBSearchField productField;
	
	private DBSearchField partField;
	
	
	public ProductPartPKView(ProductPartPKController controller){

		
		super(100);
		
		setController(controller);
		
		add(getProductField());
		
		add(getPartField());
		

	}


	public ProductPartPKController getController() {

		return controller;

	}

	
	public void setController(ProductPartPKController controller) {

		this.controller = controller;

	}

	
	public void onCheck(CheckEvent event) throws Exception {

		
		if (event.getCheckable() == getProductField())

			try{

				getController().changeProductProperty((Product) getProductField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getProductField().getSearch().openSearchViewWithoutReset(getDesktop());

			}

		else

			if (event.getCheckable() == getPartField())

				try{

					getController().changePartProperty((Product) getPartField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getPartField().getSearch().openSearchViewWithoutReset(getDesktop());

				}


	}
	

	public JDesktopPane getDesktop(){

		
		Component c = this;

		while (c.getParent() != null) {

			c = c.getParent();

			if (c instanceof JInternalFrame)

				break;

		}

		return c instanceof JInternalFrame ? ((JInternalFrame)c).getDesktopPane() : null;
		

	}

	
	public DBSearchField getProductField() {

		
		if (productField==null){

			ProductSearch search = new ProductSearch(true);
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeProductProperty((Product) event.getSelectedRows().get(0));
					
				}
				
			});

			productField = new DBSearchField(new ProductTag(), ProductPartPKController.PRODUCT_PROPERTY);
			
			productField.setCondition(new NeverEnabledCondition());
			
			productField.addCheckListener(this);
			
			productField.setPrimaryKey(true);
			
			productField.setFixedValue(true);
			
			productField.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));
			
			productField.setSearch(search);
			
			getController().addView(productField);

		}
		
		return productField;
		

	}
	

	public DBSearchField getPartField() {

		
		if (partField==null){

			ProductSearch search = new ProductSearch(true);
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changePartProperty((Product) event.getSelectedRows().get(0));
					
				}
				
			});

			partField = new DBSearchField(new PartTag(), ProductPartPKController.PART_PROPERTY);
			
			partField.addCheckListener(this);
			
			partField.setPrimaryKey(true);
			
			partField.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));
			
			partField.setSearch(search);
			
			getController().addView(partField);

		}
		
		return partField;
		

	}


	public void modelPropertyChanged(PropertyChangeEvent evt) {}


}