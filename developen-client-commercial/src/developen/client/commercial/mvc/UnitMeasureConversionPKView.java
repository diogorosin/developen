package developen.client.commercial.mvc;

import java.awt.Component;
import java.beans.PropertyChangeEvent;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.commercial.search.UnitMeasureSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.FromTag;
import developen.common.commercial.i18n.ToTag;
import developen.common.commercial.mvc.UnitMeasure;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;

public class UnitMeasureConversionPKView extends DBRowPanel implements View, CheckListener{


	private static final long serialVersionUID = -6800871769407844131L;

	private UnitMeasureConversionPKController controller;

	private DBTextField fromUnitMeasureField;
	
	private DBTextField toUnitMeasureField;
	
	
	public UnitMeasureConversionPKView(UnitMeasureConversionPKController controller){

		
		super(100);
		
		setController(controller);
		
		add(getFromUnitMeasureField());
		
		add(getToUnitMeasureField());
		

	}


	public UnitMeasureConversionPKController getController() {

		return controller;

	}

	
	public void setController(UnitMeasureConversionPKController controller) {

		this.controller = controller;

	}

	
	public void onCheck(CheckEvent event) throws Exception {

		
		if (event.getCheckable() == getFromUnitMeasureField())

			try{

				getController().changeFromUnitMeasureProperty((UnitMeasure) getFromUnitMeasureField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getFromUnitMeasureField().getSearch().openSearchViewWithoutReset(getDesktop());

			}

		else

			if (event.getCheckable() == getToUnitMeasureField())

				try{

					getController().changeToUnitMeasureProperty((UnitMeasure) getToUnitMeasureField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getToUnitMeasureField().getSearch().openSearchViewWithoutReset(getDesktop());

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

	
	public DBTextField getFromUnitMeasureField() {

		
		if (fromUnitMeasureField==null){

			UnitMeasureSearch search = new UnitMeasureSearch();
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeFromUnitMeasureProperty((UnitMeasure) event.getSelectedRows().get(0));
					
				}
				
			});

			fromUnitMeasureField = new DBTextField(new FromTag(), UnitMeasureConversionPKController.FROM_UNITMEASURE_PROPERTY);
			
			fromUnitMeasureField.setCondition(new NeverEnabledCondition());
			
			fromUnitMeasureField.addCheckListener(this);
			
			fromUnitMeasureField.setFixedValue(true);
			
			fromUnitMeasureField.setPrimaryKey(true);
			
			fromUnitMeasureField.setColumns(20);
			
			fromUnitMeasureField.setSearch(search);
			
			getController().addView(fromUnitMeasureField);

		}
		
		return fromUnitMeasureField;
		

	}
	

	public DBTextField getToUnitMeasureField() {

		
		if (toUnitMeasureField==null){

			UnitMeasureSearch search = new UnitMeasureSearch();
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeToUnitMeasureProperty((UnitMeasure) event.getSelectedRows().get(0));
					
				}
				
			});

			toUnitMeasureField = new DBTextField(new ToTag(), UnitMeasureConversionPKController.TO_UNITMEASURE_PROPERTY);
			
			toUnitMeasureField.addCheckListener(this);
			
			toUnitMeasureField.setPrimaryKey(true);
			
			toUnitMeasureField.setColumns(20);
			
			toUnitMeasureField.setSearch(search);
			
			getController().addView(toUnitMeasureField);

		}
		
		return toUnitMeasureField;
		

	}


	public void modelPropertyChanged(PropertyChangeEvent evt) {}


}