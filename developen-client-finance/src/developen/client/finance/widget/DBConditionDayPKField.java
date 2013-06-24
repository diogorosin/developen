package developen.client.finance.widget;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.finance.search.ConditionSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.finance.i18n.ConditionTag;
import developen.common.finance.i18n.DaysTag;
import developen.common.finance.mvc.Condition;
import developen.common.finance.mvc.ConditionDayPK;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;


public class DBConditionDayPKField extends JComponent{


	private static final long serialVersionUID = -6009120201640056143L;
	
	private ConditionDayPKView view;
	
	private ConditionDayPKController controller;
	

	public DBConditionDayPKField(ConditionDayPK model){

		
		controller = new ConditionDayPKController();
		
		view = new ConditionDayPKView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);

		setLayout(new BorderLayout());
		
		add(view, BorderLayout.CENTER);
		

	}


	public DBTextField getConditionField() {

		return view.getConditionField();
		
	}

	
	public DBIdentifierField getDayField() {

		return view.getDayField();
		
	}

	
	public void requestFocus(){

		
		if (view.getConditionField().isEnabled())
			
			view.getConditionField().requestFocus();
		
		else
			
			if (view.getDayField().isEnabled())
				
				view.getDayField().requestFocus();

		
	}

	
	class ConditionDayPKController extends Controller{


		public static final String CONDITION_PROPERTY = "Condition";

		public static final String DAY_PROPERTY = "Day";


		public ConditionDayPK getModel(){

			return (ConditionDayPK) super.getModel();

		}


		public void setModel(ConditionDayPK model){

			super.setModel(model);

		}


		public void changeConditionProperty(Condition newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new ConditionTag());

			setModelProperty(ConditionDayPKController.CONDITION_PROPERTY, newValue);


		}


		public void changeDayProperty(Long newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new DaysTag());

			setModelProperty(ConditionDayPKController.DAY_PROPERTY, newValue);


		}


	}


	class ConditionDayPKView extends DBRowPanel implements View, CheckListener{


		private static final long serialVersionUID = -6800871769407844131L;

		private ConditionDayPKController controller;

		private DBTextField conditionField;

		private DBIdentifierField dayField;


		public ConditionDayPKView(ConditionDayPKController controller){


			super(100);

			setController(controller);

			add(getConditionField());

			add(getDayField());


		}


		public ConditionDayPKController getController() {

			return controller;

		}


		public void setController(ConditionDayPKController controller) {

			this.controller = controller;

		}


		public void onCheck(CheckEvent event) throws Exception {


			if (event.getCheckable() == getConditionField())

				try{

					getController().changeConditionProperty((Condition)getConditionField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getConditionField().getSearch().openSearchViewWithoutReset(getDesktop());

				}

			else

				if (event.getCheckable() == getDayField())

					getController().changeDayProperty(

							getDayField().getText().isEmpty()

							? new Long(0)

							: Long.valueOf(getDayField().getText())

							);


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


		public DBTextField getConditionField() {


			if (conditionField==null){

				ConditionSearch search = new ConditionSearch(new Boolean(true));
	
				search.addSearchListener(new SearchAdapter() {
	
					public void onSearchConfirmed(SearchEvent event) throws Exception {
	
						getController().changeConditionProperty((Condition) event.getSelectedRows().get(0));
	
					}
	
				});

				conditionField = new DBTextField(new ConditionTag(), ConditionDayPKController.CONDITION_PROPERTY);

				conditionField.setCondition(new NeverEnabledCondition());

				conditionField.addCheckListener(this);

				conditionField.setFixedValue(true);

				conditionField.setPrimaryKey(true);

				conditionField.setPreferredSize(new Dimension(300,24));

				conditionField.setSearch(search);

				getController().addView(conditionField);

			}

			return conditionField;


		}


		public DBIdentifierField getDayField() {


			if (dayField==null){

				dayField = new DBIdentifierField(new DaysTag(), ConditionDayPKController.DAY_PROPERTY);

				dayField.addCheckListener(this);

				dayField.setPrimaryKey(true);

				dayField.setPreferredSize(new Dimension(75,24));

				getController().addView(dayField);

			}

			return dayField;


		}


		public void modelPropertyChanged(PropertyChangeEvent evt) {}


	}

	
}