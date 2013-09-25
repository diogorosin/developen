package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.commercial.search.CofinsCstSearch;
import developen.client.commercial.search.PisCstSearch;
import developen.client.commercial.widget.DBPisCofinsRulePKField;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.common.commercial.i18n.BasicTag;
import developen.common.commercial.i18n.CofinsCstTag;
import developen.common.commercial.i18n.CofinsTag;
import developen.common.commercial.i18n.CumulativeTag;
import developen.common.commercial.i18n.NonCumulativeTag;
import developen.common.commercial.i18n.PisCstTag;
import developen.common.commercial.i18n.PisTag;
import developen.common.commercial.i18n.RulesTag;
import developen.common.commercial.i18n.TributeSituationTag;
import developen.common.commercial.mvc.CofinsCst;
import developen.common.commercial.mvc.PisCst;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;

public class PisCofinsRuleView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = 6673670832970046339L;

	private DBPisCofinsRulePKField identifierField;

	private TabbedPane tabbedPane;

	private DBRowPanel basicTab;

	private DBSearchField pisCstField;

	private DBSearchField cofinsCstField;

	private DBNumberField pisCumulativeField;

	private DBNumberField pisNonCumulativeField;

	private DBNumberField cofinsCumulativeField;

	private DBNumberField cofinsNonCumulativeField;


	public PisCofinsRuleController getController(){

		return (PisCofinsRuleController) super.getController();

	}


	public PisCofinsRuleView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(650, 500));

	}


	public ExtendedPanel getNorthPanel(){


		ExtendedPanel l = super.getNorthPanel();

		l.add(getIdentifierField());

		return l;


	}


	public ExtendedPanel getCenterPanel() {


		ExtendedPanel l = super.getCenterPanel();

		l.add(getTabbedPane());

		return l;


	}


	public TabbedPane getTabbedPane(){


		if (tabbedPane == null){

			tabbedPane = new TabbedPane();

			tabbedPane.add(getBasicTab());

			tabbedPane.setFocusable(false);

		}

		return tabbedPane;


	}


	public DBRowPanel getBasicTab(){


		if (basicTab == null){

			basicTab = new DBRowPanel();

			basicTab.setName(new BasicTag().toString());

			basicTab.addSeparator(new TributeSituationTag());

			basicTab.add(getPisCstField());

			basicTab.add(getCofinsCstField());

			basicTab.addSeparator(new PisTag());

			basicTab.add(getPisCumulativeField());

			basicTab.add(getPisNonCumulativeField());

			basicTab.addSeparator(new CofinsTag());

			basicTab.add(getCofinsCumulativeField());

			basicTab.add(getCofinsNonCumulativeField());

		}

		return basicTab;


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public Tag getInternalFrameTitle() {

		return new RulesTag();

	}


	public DBPisCofinsRulePKField getIdentifierField() {


		if (identifierField==null){

			identifierField = new DBPisCofinsRulePKField(getController().getModel().getIdentifier());

			getController().addView(identifierField.getPisCofinsField());

			getController().addView(identifierField.getCfopField());

			getController().addView(identifierField.getRuleField());

		}

		return identifierField;


	}


	public DBSearchField getPisCstField() {


		if (pisCstField==null){

			PisCstSearch search = new PisCstSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changePisCstProperty((PisCst) event.getSelectedRows().get(0));

				}

			});

			pisCstField = new DBSearchField(new PisCstTag(), PisCofinsRuleController.PIS_CST_PROPERTY);

			pisCstField.setCondition(new EditingOrIncludingListEditorCondition());

			pisCstField.addCheckListener(this);

			pisCstField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

			pisCstField.setSearch(search);

			getController().addView(pisCstField);

		}

		return pisCstField;


	}


	public DBSearchField getCofinsCstField() {


		if (cofinsCstField==null){

			CofinsCstSearch search = new CofinsCstSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCofinsCstProperty((CofinsCst) event.getSelectedRows().get(0));

				}

			});

			cofinsCstField = new DBSearchField(new CofinsCstTag(), PisCofinsRuleController.COFINS_CST_PROPERTY);

			cofinsCstField.setCondition(new EditingOrIncludingListEditorCondition());

			cofinsCstField.addCheckListener(this);

			cofinsCstField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

			cofinsCstField.setSearch(search);

			getController().addView(cofinsCstField);

		}

		return cofinsCstField;


	}


	public DBNumberField getPisCumulativeField() {


		if (pisCumulativeField==null){

			pisCumulativeField = new DBNumberField(new CumulativeTag(), 

					FormatFactory.createNumberFormat(10, 4),

					PisCofinsRuleController.PIS_CUMULATIVE_PROPERTY);

			pisCumulativeField.setHorizontalAlignment(SwingConstants.RIGHT);

			pisCumulativeField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			pisCumulativeField.addCheckListener(this);

			pisCumulativeField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(pisCumulativeField);

		}

		return pisCumulativeField;


	}


	public DBNumberField getPisNonCumulativeField() {


		if (pisNonCumulativeField==null){

			pisNonCumulativeField = new DBNumberField(new NonCumulativeTag(), 

					FormatFactory.createNumberFormat(10, 4),

					PisCofinsRuleController.PIS_NON_CUMULATIVE_PROPERTY);

			pisNonCumulativeField.setHorizontalAlignment(SwingConstants.RIGHT);

			pisNonCumulativeField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			pisNonCumulativeField.addCheckListener(this);

			pisNonCumulativeField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(pisNonCumulativeField);

		}

		return pisNonCumulativeField;


	}


	public DBNumberField getCofinsCumulativeField() {


		if (cofinsCumulativeField==null){

			cofinsCumulativeField = new DBNumberField(new CumulativeTag(), 

					FormatFactory.createNumberFormat(10, 4),

					PisCofinsRuleController.COFINS_CUMULATIVE_PROPERTY);

			cofinsCumulativeField.setHorizontalAlignment(SwingConstants.RIGHT);

			cofinsCumulativeField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			cofinsCumulativeField.addCheckListener(this);

			cofinsCumulativeField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(cofinsCumulativeField);

		}

		return cofinsCumulativeField;


	}


	public DBNumberField getCofinsNonCumulativeField() {


		if (cofinsNonCumulativeField==null){

			cofinsNonCumulativeField = new DBNumberField(new NonCumulativeTag(), 

					FormatFactory.createNumberFormat(10, 4),

					PisCofinsRuleController.COFINS_NON_CUMULATIVE_PROPERTY);

			cofinsNonCumulativeField.setHorizontalAlignment(SwingConstants.RIGHT);

			cofinsNonCumulativeField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			cofinsNonCumulativeField.addCheckListener(this);

			cofinsNonCumulativeField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(cofinsNonCumulativeField);

		}

		return cofinsNonCumulativeField;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getPisCstField())

			try{

				getController().changePisCstProperty((PisCst) getPisCstField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getPisCstField().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		else

			if (event.getCheckable() == getCofinsCstField())

				try{

					getController().changeCofinsCstProperty((CofinsCst) getCofinsCstField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getCofinsCstField().getSearch().openSearchViewWithoutReset(getDesktopPane());

				}

			else

				if (event.getCheckable()==getPisCumulativeField())

					getController().changePisCumulativeProperty(Double.valueOf(getPisCumulativeField().getValue().toString()));


				else

					if (event.getCheckable()==getPisNonCumulativeField())

						getController().changePisNonCumulativeProperty(Double.valueOf(getPisNonCumulativeField().getValue().toString()));

					else

						if (event.getCheckable()==getCofinsCumulativeField())

							getController().changeCofinsCumulativeProperty(Double.valueOf(getCofinsCumulativeField().getValue().toString()));


						else

							if (event.getCheckable()==getCofinsNonCumulativeField())

								getController().changeCofinsNonCumulativeProperty(Double.valueOf(getCofinsNonCumulativeField().getValue().toString()));


	}


}