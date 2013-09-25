package developen.client.commercial.widget;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.commercial.search.CfopSearch;
import developen.client.commercial.search.PisCofinsSearch;
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
import developen.common.commercial.i18n.PisCofinsTag;
import developen.common.commercial.mvc.Cfop;
import developen.common.commercial.mvc.PisCofins;
import developen.common.commercial.mvc.PisCofinsRulePK;
import developen.common.commercial.mvc.Rule;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;


public class DBPisCofinsRulePKField extends JComponent{


	private static final long serialVersionUID = 7773010263790372486L;

	private PisCofinsRulePKView view;

	private PisCofinsRulePKController controller;


	public DBPisCofinsRulePKField(PisCofinsRulePK model){


		controller = new PisCofinsRulePKController();

		view = new PisCofinsRulePKView(controller);

		controller.addView(view);

		controller.setModel(model);

		setLayout(new BorderLayout());

		add(view, BorderLayout.CENTER);


	}


	public DBTextField getPisCofinsField() {

		return view.getPisCofinsField();

	}


	public DBTextField getCfopField() {

		return view.getCfopField();

	}


	public DBTextField getRuleField() {

		return view.getRuleField();

	}


	public void requestFocus(){


		if (getPisCofinsField().isEnabled())

			getPisCofinsField().requestFocus();

		else


			if (getCfopField().isEnabled())

				getCfopField().requestFocus();

			else

				if (getRuleField().isEnabled())

					getRuleField().requestFocus();


	}


	private class PisCofinsRulePKController extends Controller{


		public static final String PIS_COFINS_PROPERTY = "PisCofins";

		public static final String CFOP_PROPERTY = "Cfop";

		public static final String RULE_PROPERTY = "Rule";


		public PisCofinsRulePK getModel(){

			return (PisCofinsRulePK) super.getModel();

		}


		public void setModel(PisCofinsRulePK model){

			super.setModel(model);

		}


		public void changePisCofinsProperty(PisCofins newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new PisCofinsTag());

			setModelProperty(PisCofinsRulePKController.PIS_COFINS_PROPERTY, newValue);


		}


		public void changeCfopProperty(Cfop newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new CfopTag());

			setModelProperty(PisCofinsRulePKController.CFOP_PROPERTY, newValue);


		}


		public void changeRuleProperty(Rule newValue) throws Exception{


			if (newValue==null)

				throw new NotNullException(new FiscalRuleTag());

			setModelProperty(PisCofinsRulePKController.RULE_PROPERTY, newValue);


		}


	}


	private class PisCofinsRulePKView extends DBRowPanel implements View, CheckListener{


		private static final long serialVersionUID = -2351957514647930615L;

		private PisCofinsRulePKController controller;

		private DBSearchField pisCofinsField;

		private DBSearchField cfopField;

		private DBSearchField ruleField;


		public PisCofinsRulePKView(PisCofinsRulePKController controller){


			super();

			setController(controller);

			add(getPisCofinsField());

			add(getCfopField());

			add(getRuleField());


		}


		public PisCofinsRulePKController getController() {

			return controller;

		}


		public void setController(PisCofinsRulePKController controller) {

			this.controller = controller;

		}


		public void onCheck(CheckEvent event) throws Exception {


			if (event.getCheckable() == getPisCofinsField())

				try{

					getController().changePisCofinsProperty((PisCofins) getPisCofinsField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getPisCofinsField().getSearch().openSearchViewWithoutReset(getDesktop());

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


		public DBSearchField getPisCofinsField() {


			if (pisCofinsField==null){

				PisCofinsSearch search = new PisCofinsSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changePisCofinsProperty((PisCofins) event.getSelectedRows().get(0));

					}

				});

				pisCofinsField = new DBSearchField(new PisCofinsTag(), PisCofinsRulePKController.PIS_COFINS_PROPERTY);

				pisCofinsField.setCondition(new NeverEnabledCondition());

				pisCofinsField.addCheckListener(this);

				pisCofinsField.setFixedValue(true);

				pisCofinsField.setPrimaryKey(true);

				pisCofinsField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

				pisCofinsField.setSearch(search);

				getController().addView(pisCofinsField);

			}

			return pisCofinsField;


		}


		public DBSearchField getCfopField() {


			if (cfopField==null){

				CfopSearch search = new CfopSearch();

				search.addSearchListener(new SearchAdapter() {

					public void onSearchConfirmed(SearchEvent event) throws Exception {

						getController().changeCfopProperty((Cfop) event.getSelectedRows().get(0));

					}

				});

				cfopField = new DBSearchField(new CfopTag(), PisCofinsRulePKController.CFOP_PROPERTY);

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

				ruleField = new DBSearchField(new FiscalRuleTag(), PisCofinsRulePKController.RULE_PROPERTY);

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