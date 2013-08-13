package developen.client.engineer.mvc;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.engineer.search.IcmsSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.client.subject.search.RuleSearch;
import developen.client.subject.search.StateSearch;
import developen.common.engineer.i18n.FromTag;
import developen.common.engineer.i18n.IcmsIcmsSTTag;
import developen.common.engineer.i18n.ToTag;
import developen.common.engineer.mvc.Icms;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.subject.i18n.TributaryRuleTag;
import developen.common.subject.mvc.Rule;
import developen.common.subject.mvc.State;

public class IcmsRulePKView extends DBRowPanel implements View, CheckListener{


	private static final long serialVersionUID = -6800871769407844131L;

	private IcmsRulePKController controller;

	private DBTextField icmsField;

	private DBTextField fromField;

	private DBTextField toField;

	private DBTextField ruleField;


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


	public DBTextField getIcmsField() {


		if (icmsField==null){

			IcmsSearch search = new IcmsSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIcmsProperty((Icms) event.getSelectedRows().get(0));

				}

			});

			icmsField = new DBTextField(new IcmsIcmsSTTag(), IcmsRulePKController.ICMS_PROPERTY);

			icmsField.setCondition(new NeverEnabledCondition());

			icmsField.addCheckListener(this);

			icmsField.setFixedValue(true);

			icmsField.setPrimaryKey(true);

			icmsField.setPreferredSize(new Dimension(400, 24));

			icmsField.setSearch(search);

			getController().addView(icmsField);

		}

		return icmsField;


	}


	public DBTextField getFromField() {


		if (fromField==null){

			StateSearch search = new StateSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeFromProperty((State) event.getSelectedRows().get(0));

				}

			});

			fromField = new DBTextField(new FromTag(), IcmsRulePKController.FROM_PROPERTY);

			fromField.addCheckListener(this);

			fromField.setPrimaryKey(true);

			fromField.setPreferredSize(new Dimension(400, 24));

			fromField.setSearch(search);

			getController().addView(fromField);

		}

		return fromField;


	}


	public DBTextField getToField() {


		if (toField==null){

			StateSearch search = new StateSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeToProperty((State) event.getSelectedRows().get(0));

				}

			});

			toField = new DBTextField(new ToTag(), IcmsRulePKController.TO_PROPERTY);

			toField.addCheckListener(this);

			toField.setPrimaryKey(true);

			toField.setPreferredSize(new Dimension(400, 24));

			toField.setSearch(search);

			getController().addView(toField);

		}

		return toField;


	}


	public DBTextField getRuleField() {


		if (ruleField==null){

			RuleSearch search = new RuleSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeRuleProperty((Rule) event.getSelectedRows().get(0));

				}

			});

			ruleField = new DBTextField(new TributaryRuleTag(), IcmsRulePKController.RULE_PROPERTY);

			ruleField.addCheckListener(this);

			ruleField.setPrimaryKey(true);

			ruleField.setPreferredSize(new Dimension(400, 24));

			ruleField.setSearch(search);

			getController().addView(ruleField);

		}

		return ruleField;


	}

	
	public void modelPropertyChanged(PropertyChangeEvent evt) {}


}