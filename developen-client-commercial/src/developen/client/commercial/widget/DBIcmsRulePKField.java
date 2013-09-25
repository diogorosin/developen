package developen.client.commercial.widget;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.commercial.search.IcmsSearch;
import developen.client.commercial.search.RuleSearch;
import developen.client.commercial.search.StateSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.commercial.i18n.FromTag;
import developen.common.commercial.i18n.IcmsIcmsSTTag;
import developen.common.commercial.i18n.IcmsTag;
import developen.common.commercial.i18n.ToTag;
import developen.common.commercial.mvc.Icms;
import developen.common.commercial.mvc.IcmsRulePK;
import developen.common.commercial.mvc.Rule;
import developen.common.commercial.mvc.State;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;


public class DBIcmsRulePKField extends JComponent{


	private static final long serialVersionUID = -6009120201640056143L;

	private IcmsRulePKView view;

	private IcmsRulePKController controller;


	public DBIcmsRulePKField(IcmsRulePK model){


		controller = new IcmsRulePKController();

		view = new IcmsRulePKView(controller);

		controller.addView(view);

		controller.setModel(model);

		setLayout(new BorderLayout());

		add(view, BorderLayout.CENTER);


	}


	public DBTextField getIcmsField() {

		return view.getIcmsField();

	}


	public DBTextField getFromField() {

		return view.getFromField();

	}


	public DBTextField getToField() {

		return view.getToField();

	}


	public DBTextField getRuleField() {

		return view.getRuleField();

	}

	
	public void requestFocus(){


		if (getIcmsField().isEnabled())

			getIcmsField().requestFocus();

		else


			if (getFromField().isEnabled())

				getFromField().requestFocus();

			else

				if (getToField().isEnabled())

					getToField().requestFocus();


	}


	private class IcmsRulePKController extends Controller{


		public static final String ICMS_PROPERTY = "Icms";
		
		public static final String FROM_PROPERTY = "From";

		public static final String TO_PROPERTY = "To";
		
		public static final String RULE_PROPERTY = "Rule";


		public IcmsRulePK getModel(){

			return (IcmsRulePK) super.getModel();

		}


		public void setModel(IcmsRulePK model){

			super.setModel(model);

		}


		public void changeIcmsProperty(Icms newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new IcmsTag());

			setModelProperty(IcmsRulePKController.ICMS_PROPERTY, newValue);


		}


		public void changeFromProperty(State newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new FromTag());

			setModelProperty(IcmsRulePKController.FROM_PROPERTY, newValue);


		}


		public void changeToProperty(State newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new ToTag());

			setModelProperty(IcmsRulePKController.TO_PROPERTY, newValue);


		}


		public void changeRuleProperty(Rule newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new FiscalRuleTag());

			setModelProperty(IcmsRulePKController.RULE_PROPERTY, newValue);


		}

		
	}

	
	private class IcmsRulePKView extends DBRowPanel implements View, CheckListener{


		private static final long serialVersionUID = -6800871769407844131L;

		private IcmsRulePKController controller;

		private DBSearchField icmsField;

		private DBSearchField fromField;

		private DBSearchField toField;

		private DBSearchField ruleField;


		public IcmsRulePKView(IcmsRulePKController controller){


			super(200);

			setController(controller);

			add(getIcmsField());

			add(getFromField());

			add(getToField());

			add(getRuleField());


		}


		public IcmsRulePKController getController() {

			return controller;

		}


		public void setController(IcmsRulePKController controller) {

			this.controller = controller;

		}


		public void onCheck(CheckEvent event) throws Exception {


			if (event.getCheckable() == getIcmsField())

				try{

					getController().changeIcmsProperty((Icms) getIcmsField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getIcmsField().getSearch().openSearchViewWithoutReset(getDesktop());

				}

			else

				if (event.getCheckable() == getFromField())

					try{

						getController().changeFromProperty((State) getFromField().getSearch().findBy());

					} catch (ManyRecordsFoundException e) {

						getFromField().getSearch().openSearchViewWithoutReset(getDesktop());

					}

				else

					if (event.getCheckable() == getToField())

						try{

							getController().changeToProperty((State) getToField().getSearch().findBy());

						} catch (ManyRecordsFoundException e) {

							getToField().getSearch().openSearchViewWithoutReset(getDesktop());

						}

					else

						if (event.getCheckable() == getRuleField())

							try{

								getController().changeRuleProperty((Rule) getRuleField().getSearch().findBy());

							} catch (ManyRecordsFoundException e) {

								getRuleField().getSearch().openSearchViewWithoutReset(getDesktop());

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


		public DBSearchField getIcmsField() {


			if (icmsField==null){

				IcmsSearch search = new IcmsSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changeIcmsProperty((Icms) event.getSelectedRows().get(0));

					}

				});

				icmsField = new DBSearchField(new IcmsIcmsSTTag(), IcmsRulePKController.ICMS_PROPERTY);

				icmsField.setCondition(new NeverEnabledCondition());

				icmsField.addCheckListener(this);

				icmsField.setFixedValue(true);

				icmsField.setPrimaryKey(true);

				icmsField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

				icmsField.setSearch(search);

				getController().addView(icmsField);

			}

			return icmsField;


		}


		public DBSearchField getFromField() {


			if (fromField==null){

				StateSearch search = new StateSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changeFromProperty((State) event.getSelectedRows().get(0));

					}

				});

				fromField = new DBSearchField(new FromTag(), IcmsRulePKController.FROM_PROPERTY);

				fromField.addCheckListener(this);

				fromField.setPrimaryKey(true);

				fromField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

				fromField.setSearch(search);

				getController().addView(fromField);

			}

			return fromField;


		}


		public DBSearchField getToField() {


			if (toField==null){

				StateSearch search = new StateSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changeToProperty((State) event.getSelectedRows().get(0));

					}

				});

				toField = new DBSearchField(new ToTag(), IcmsRulePKController.TO_PROPERTY);

				toField.addCheckListener(this);

				toField.setPrimaryKey(true);

				toField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

				toField.setSearch(search);

				getController().addView(toField);

			}

			return toField;


		}


		public DBSearchField getRuleField() {


			if (ruleField==null){

				RuleSearch search = new RuleSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changeRuleProperty((Rule) event.getSelectedRows().get(0));

					}

				});

				ruleField = new DBSearchField(new FiscalRuleTag(), IcmsRulePKController.RULE_PROPERTY);

				ruleField.addCheckListener(this);

				ruleField.setPrimaryKey(true);

				ruleField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

				ruleField.setSearch(search);

				getController().addView(ruleField);

			}

			return ruleField;


		}

		
		public void modelPropertyChanged(PropertyChangeEvent evt) {}


	}

	
}