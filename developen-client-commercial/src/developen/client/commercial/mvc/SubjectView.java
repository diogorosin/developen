package developen.client.commercial.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import developen.client.commercial.search.RuleSearch;
import developen.client.commercial.widget.DBAddressField;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.ActiveTag;
import developen.common.commercial.i18n.AdressContactTag;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.NameTag;
import developen.common.commercial.i18n.PreferencesTag;
import developen.common.commercial.i18n.SubjectTag;
import developen.common.commercial.i18n.TributationTag;
import developen.common.commercial.mvc.Rule;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;

public abstract class SubjectView extends EntryView {


	private static final long serialVersionUID = 6480251642431702568L;

	public static int WIDTH = 700;

	public static int HEIGHT = 550;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBCheckBox fieldActive;

	private DBTextField ruleField;

	protected DBRowPanel headerPanel;

	private TabbedPane tabbedPane;

	private JPanel addressTab;

	private DBRowPanel preferencesTab;

	private DBAddressField addressField;

	protected Search identifierSearch;


	public SubjectView(SubjectController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT));

	}


	public ExtendedPanel getNorthPanel(){


		ExtendedPanel l = super.getNorthPanel();

		l.add(getHeaderPanel());

		return l;


	}


	public DBRowPanel getHeaderPanel(){


		if (headerPanel == null){

			headerPanel = new DBRowPanel();

			headerPanel.add(getIdentifierField());

			headerPanel.add(getDenominationField());

			headerPanel.add(getFieldActive());

		}

		return headerPanel;


	}


	public ExtendedPanel getCenterPanel(){


		ExtendedPanel l = super.getCenterPanel();

		l.add(getTabbedPane());

		return l;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getIdentifierField())

			getController().changeIdentifierProperty(

					getIdentifierField().getText().isEmpty()

					? new Long(0)

					: Long.valueOf(getIdentifierField().getText()));

		else

			if (event.getCheckable() == getDenominationField())

				getController().changeDenominationProperty(getDenominationField().getText());

			else
				
				if (event.getCheckable() == getRuleField())

					try{

						getController().changeRuleProperty((Rule) getRuleField().getSearch().findBy());

					} catch (ManyRecordsFoundException e) {

						getRuleField().getSearch().openSearchViewWithoutReset(getDesktopPane());

					}


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), SubjectController.IDENTIFIER_PROPERTY);

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setSearch(getSearch());

			identifierField.setPreferredSize(new Dimension(75,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new NameTag(), SubjectController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400,24));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBCheckBox getFieldActive() {


		if (fieldActive == null){

			fieldActive = new DBCheckBox(new ActiveTag(), SubjectController.ACTIVE_PROPERTY);

			fieldActive.setSelected(false);

			fieldActive.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeActiveProperty(fieldActive.isSelected());

				}

			});

			getController().addView(fieldActive);

		}

		return fieldActive;


	}


	public DBTextField getRuleField() {


		if (ruleField == null){

			RuleSearch ruleSearch = new RuleSearch();

			ruleSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeRuleProperty((Rule) event.getSelectedRows().get(0));

				}

			});

			ruleField = new DBTextField(new FiscalRuleTag(), SubjectController.RULE_PROPERTY);

			ruleField.addCheckListener(this);

			ruleField.setSearch(ruleSearch);

			ruleField.setPreferredSize(new Dimension(400,24));

			getController().addView(ruleField);

		}

		return ruleField;


	}


	public TabbedPane getTabbedPane(){


		if (tabbedPane == null){

			tabbedPane = new TabbedPane();

			tabbedPane.add(getAddressTab());

			tabbedPane.add(getPreferencesTab());

			tabbedPane.setFocusable(false);

		}

		return tabbedPane;


	}


	public JPanel getAddressTab(){


		if (addressTab == null){

			addressTab = new JPanel(new BorderLayout());

			addressTab.add(getAddressField(), BorderLayout.CENTER);

			addressTab.setName(new AdressContactTag().toString());

		}

		return addressTab;


	}


	public DBRowPanel getPreferencesTab(){


		if (preferencesTab == null){

			preferencesTab = new DBRowPanel();

			preferencesTab.setName(new PreferencesTag().toString());

			preferencesTab.addSeparator(new TributationTag());

			preferencesTab.add(getRuleField());

		}

		return preferencesTab;


	}


	public DBAddressField getAddressField() {


		if (addressField==null){

			addressField = new DBAddressField(getController().getModel().getAddress());

			getController().addView(addressField.getPlayAreaField());

			getController().addView(addressField.getNumberField());

			getController().addView(addressField.getDistrictField());

			getController().addView(addressField.getComplementField());

			getController().addView(addressField.getPostalCodeField());

			getController().addView(addressField.getCityField());

			getController().addView(addressField.getPhoneField());

			getController().addView(addressField.getEmailField());

			getController().addView(addressField.getWebSiteField());

		}

		return addressField;


	}


	public Tag getInternalFrameTitle() {

		return new SubjectTag();

	}


	public SubjectController getController() {

		return (SubjectController) super.getController();

	}


}