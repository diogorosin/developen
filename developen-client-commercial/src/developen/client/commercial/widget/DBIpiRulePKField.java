package developen.client.commercial.widget;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.commercial.search.CfopSearch;
import developen.client.commercial.search.IpiSearch;
import developen.client.commercial.search.RuleSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.CfopTag;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.commercial.i18n.IpiTag;
import developen.common.commercial.mvc.Cfop;
import developen.common.commercial.mvc.Ipi;
import developen.common.commercial.mvc.IpiRulePK;
import developen.common.commercial.mvc.Rule;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;


public class DBIpiRulePKField extends JComponent{


	private static final long serialVersionUID = -6009120201640056143L;

	private IpiRulePKView view;

	private IpiRulePKController controller;


	public DBIpiRulePKField(IpiRulePK model){


		controller = new IpiRulePKController();

		view = new IpiRulePKView(controller);

		controller.addView(view);

		controller.setModel(model);

		setLayout(new BorderLayout());

		add(view, BorderLayout.CENTER);


	}


	public DBTextField getIpiField() {

		return view.getIpiField();

	}


	public DBTextField getCfopField() {

		return view.getCfopField();

	}


	public DBTextField getRuleField() {

		return view.getRuleField();

	}


	public void requestFocus(){


		if (getIpiField().isEnabled())

			getIpiField().requestFocus();

		else


			if (getCfopField().isEnabled())

				getCfopField().requestFocus();

			else

				if (getRuleField().isEnabled())

					getRuleField().requestFocus();


	}


	private class IpiRulePKController extends Controller{


		public static final String IPI_PROPERTY = "Ipi";

		public static final String CFOP_PROPERTY = "Cfop";

		public static final String RULE_PROPERTY = "Rule";


		public IpiRulePK getModel(){

			return (IpiRulePK) super.getModel();

		}


		public void setModel(IpiRulePK model){

			super.setModel(model);

		}


		public void changeIpiProperty(Ipi newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new IpiTag());

			setModelProperty(IpiRulePKController.IPI_PROPERTY, newValue);


		}


		public void changeCfopProperty(Cfop newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new CfopTag());

			setModelProperty(IpiRulePKController.CFOP_PROPERTY, newValue);


		}


		public void changeRuleProperty(Rule newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new FiscalRuleTag());

			setModelProperty(IpiRulePKController.RULE_PROPERTY, newValue);


		}


	}


	private class IpiRulePKView extends DBRowPanel implements View, CheckListener{


		private static final long serialVersionUID = -6800871769407844131L;

		private IpiRulePKController controller;

		private DBSearchField ipiField;

		private DBSearchField cfopField;

		private DBSearchField ruleField;


		public IpiRulePKView(IpiRulePKController controller){


			super();

			setController(controller);

			add(getIpiField());

			add(getCfopField());

			add(getRuleField());


		}


		public IpiRulePKController getController() {

			return controller;

		}


		public void setController(IpiRulePKController controller) {

			this.controller = controller;

		}


		public void onCheck(CheckEvent event) throws Exception {


			if (event.getCheckable() == getIpiField())

				try{

					getController().changeIpiProperty((Ipi) getIpiField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getIpiField().getSearch().openSearchViewWithoutReset(getDesktop());

				}

			else

				if (event.getCheckable() == getCfopField())

					try{

						getController().changeCfopProperty((Cfop) getCfopField().getSearch().findBy());

					} catch (ManyRecordsFoundException e) {

						getCfopField().getSearch().openSearchViewWithoutReset(getDesktop());

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


		public DBSearchField getIpiField() {


			if (ipiField==null){

				IpiSearch search = new IpiSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changeIpiProperty((Ipi) event.getSelectedRows().get(0));

					}

				});

				ipiField = new DBSearchField(new IpiTag(), IpiRulePKController.IPI_PROPERTY);

				ipiField.setCondition(new NeverEnabledCondition());

				ipiField.addCheckListener(this);

				ipiField.setFixedValue(true);

				ipiField.setPrimaryKey(true);

				ipiField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

				ipiField.setSearch(search);

				getController().addView(ipiField);

			}

			return ipiField;


		}


		public DBSearchField getCfopField() {


			if (cfopField==null){

				CfopSearch search = new CfopSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changeCfopProperty((Cfop) event.getSelectedRows().get(0));

					}

				});

				cfopField = new DBSearchField(new CfopTag(), IpiRulePKController.CFOP_PROPERTY);

				cfopField.addCheckListener(this);

				cfopField.setPrimaryKey(true);

				cfopField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

				cfopField.setSearch(search);

				getController().addView(cfopField);

			}

			return cfopField;


		}


		public DBSearchField getRuleField() {


			if (ruleField==null){

				RuleSearch search = new RuleSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changeRuleProperty((Rule) event.getSelectedRows().get(0));

					}

				});

				ruleField = new DBSearchField(new FiscalRuleTag(), IpiRulePKController.RULE_PROPERTY);

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