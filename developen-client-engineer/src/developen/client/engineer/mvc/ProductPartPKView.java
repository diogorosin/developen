package developen.client.engineer.mvc;

import java.awt.Component;
import java.beans.PropertyChangeEvent;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.engineer.search.ProductSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowLayout;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.engineer.i18n.PartTag;
import developen.common.engineer.i18n.ProductTag;
import developen.common.engineer.mvc.Product;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;

public class ProductPartPKView extends DBRowLayout implements View, CheckListener{


	private static final long serialVersionUID = -6800871769407844131L;

	private ProductPartPKController controller;

	private DBTextField productField;
	
	private DBTextField partField;
	
	
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

	
	public DBTextField getProductField() {

		
		if (productField==null){

			ProductSearch search = new ProductSearch(true);
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeProductProperty((Product) event.getSelectedRows().get(0));
					
				}
				
			});

			productField = new DBTextField(new ProductTag(), ProductPartPKController.PRODUCT_PROPERTY);
			
			productField.setCondition(new NeverEnabledCondition());
			
			productField.addCheckListener(this);
			
			productField.setPrimaryKey(true);
			
			productField.setFixedValue(true);
			
			productField.setColumns(30);
			
			productField.setSearch(search);
			
			getController().addView(productField);

		}
		
		return productField;
		

	}
	

	public DBTextField getPartField() {

		
		if (partField==null){

			ProductSearch search = new ProductSearch(true);
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changePartProperty((Product) event.getSelectedRows().get(0));
					
				}
				
			});

			partField = new DBTextField(new PartTag(), ProductPartPKController.PART_PROPERTY);
			
			partField.addCheckListener(this);
			
			partField.setPrimaryKey(true);
			
			partField.setColumns(30);
			
			partField.setSearch(search);
			
			getController().addView(partField);

		}
		
		return partField;
		

	}


	public void modelPropertyChanged(PropertyChangeEvent evt) {}


}