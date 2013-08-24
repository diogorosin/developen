package developen.client.commercial.mvc;

import java.awt.Component;
import java.beans.PropertyChangeEvent;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.commercial.search.MeasureUnitSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.FromTag;
import developen.common.commercial.i18n.ToTag;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;

public class MeasureUnitConversionPKView extends DBRowPanel implements View, CheckListener{


	private static final long serialVersionUID = -6800871769407844131L;

	private MeasureUnitConversionPKController controller;

	private DBTextField fromField;
	
	private DBTextField toField;
	
	
	public MeasureUnitConversionPKView(MeasureUnitConversionPKController controller){

		
		super(100);
		
		setController(controller);
		
		add(getFromField());
		
		add(getToField());
		

	}


	public MeasureUnitConversionPKController getController() {

		return controller;

	}

	
	public void setController(MeasureUnitConversionPKController controller) {

		this.controller = controller;

	}

	
	public void onCheck(CheckEvent event) throws Exception {

		
		if (event.getCheckable() == getFromField())

			try{

				getController().changeFromProperty((MeasureUnit) getFromField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getFromField().getSearch().openSearchViewWithoutReset(getDesktop());

			}

		else

			if (event.getCheckable() == getToField())

				try{

					getController().changeToProperty((MeasureUnit) getToField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getToField().getSearch().openSearchViewWithoutReset(getDesktop());

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

	
	public DBTextField getFromField() {

		
		if (fromField==null){

			MeasureUnitSearch search = new MeasureUnitSearch();
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeFromProperty((MeasureUnit) event.getSelectedRows().get(0));
					
				}
				
			});

			fromField = new DBTextField(new FromTag(), MeasureUnitConversionPKController.FROM_PROPERTY);
			
			fromField.setCondition(new NeverEnabledCondition());
			
			fromField.addCheckListener(this);
			
			fromField.setFixedValue(true);
			
			fromField.setPrimaryKey(true);
			
			fromField.setColumns(20);
			
			fromField.setSearch(search);
			
			getController().addView(fromField);

		}
		
		return fromField;
		

	}
	

	public DBTextField getToField() {

		
		if (toField==null){

			MeasureUnitSearch search = new MeasureUnitSearch();
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeToProperty((MeasureUnit) event.getSelectedRows().get(0));
					
				}
				
			});

			toField = new DBTextField(new ToTag(), MeasureUnitConversionPKController.TO_PROPERTY);
			
			toField.addCheckListener(this);
			
			toField.setPrimaryKey(true);
			
			toField.setColumns(20);
			
			toField.setSearch(search);
			
			getController().addView(toField);

		}
		
		return toField;
		

	}


	public void modelPropertyChanged(PropertyChangeEvent evt) {}


}