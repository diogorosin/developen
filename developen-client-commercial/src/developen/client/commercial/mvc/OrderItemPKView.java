package developen.client.commercial.mvc;

import java.awt.Component;
import java.beans.PropertyChangeEvent;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.commercial.search.OrderSearch;
import developen.client.engineer.search.ProgenySearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.OrderTag;
import developen.common.commercial.mvc.Order;
import developen.common.engineer.i18n.ProgenyTag;
import developen.common.engineer.mvc.Progeny;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;

public class OrderItemPKView extends DBRowPanel implements View, CheckListener{


	private static final long serialVersionUID = -6800871769407844131L;

	private OrderItemPKController controller;

	private DBTextField orderField;
	
	private DBTextField progenyField;
	
	
	public OrderItemPKView(OrderItemPKController controller){

		
		super(120);
		
		setController(controller);
		
		add(getOrderField());
		
		add(getProgenyField());
		

	}


	public OrderItemPKController getController() {

		return controller;

	}

	
	public void setController(OrderItemPKController controller) {

		this.controller = controller;

	}

	
	public void onCheck(CheckEvent event) throws Exception {

		
		if (event.getCheckable() == getOrderField())

			try{

				getController().changeOrderProperty((Order) getOrderField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getOrderField().getSearch().openSearchViewWithoutReset(getDesktop());

			}

		else

			if (event.getCheckable() == getProgenyField())

				try{

					getController().changeProgenyProperty((Progeny) getProgenyField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getProgenyField().getSearch().openSearchViewWithoutReset(getDesktop());

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

	
	public DBTextField getOrderField() {

		
		if (orderField==null){

			OrderSearch search = new OrderSearch();
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeOrderProperty((Order) event.getSelectedRows().get(0));
					
				}
				
			});

			orderField = new DBTextField(new OrderTag(), OrderItemPKController.ORDER_PROPERTY);
			
			orderField.setCondition(new NeverEnabledCondition());
			
			orderField.addCheckListener(this);
			
			orderField.setPrimaryKey(true);
			
			orderField.setFixedValue(true);
			
			orderField.setColumns(6);
			
			orderField.setSearch(search);
			
			getController().addView(orderField);

		}
		
		return orderField;
		

	}
	

	public DBTextField getProgenyField() {

		
		if (progenyField==null){

			ProgenySearch search = new ProgenySearch(true);
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeProgenyProperty((Progeny) event.getSelectedRows().get(0));
					
				}
				
			});

			progenyField = new DBTextField(new ProgenyTag(), OrderItemPKController.PROGENY_PROPERTY);
			
			progenyField.addCheckListener(this);
			
			progenyField.setPrimaryKey(true);
			
			progenyField.setColumns(30);
			
			progenyField.setSearch(search);
			
			getController().addView(progenyField);

		}
		
		return progenyField;
		

	}


	public void modelPropertyChanged(PropertyChangeEvent evt) {}


}