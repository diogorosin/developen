package developen.client.commercial.mvc;

import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import developen.client.commercial.search.IcmsCsosnSearch;
import developen.client.commercial.search.IcmsCstSearch;
import developen.client.commercial.search.IcmsRuleSearch;
import developen.client.commercial.widget.DBIcmsRulePKField;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.client.framework.widget.DBSlider;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.common.commercial.i18n.AliquotCreditReusableTag;
import developen.common.commercial.i18n.AliquotReducedTag;
import developen.common.commercial.i18n.AliquotTag;
import developen.common.commercial.i18n.BasicTag;
import developen.common.commercial.i18n.CfopTag;
import developen.common.commercial.i18n.IcmsCsosnTag;
import developen.common.commercial.i18n.IcmsCstTag;
import developen.common.commercial.i18n.IcmsSTTag;
import developen.common.commercial.i18n.IcmsTag;
import developen.common.commercial.i18n.MacroTag;
import developen.common.commercial.i18n.MarckupTag;
import developen.common.commercial.i18n.ReductionTag;
import developen.common.commercial.i18n.RulesTag;
import developen.common.commercial.i18n.StaffTag;
import developen.common.commercial.i18n.TributeSituationTag;
import developen.common.commercial.mvc.IcmsCsosn;
import developen.common.commercial.mvc.IcmsCst;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;

public class IcmsRuleView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = -4306601465035148551L;

	private DBIcmsRulePKField identifierField;
	
	protected Search identifierSearch;

	private TabbedPane tabbedPane;

	private DBRowPanel basicTab;

	private DBSearchField cstField;

	private DBSearchField csosnField;

	private DBNumberField icmsAliquotField;

	private DBNumberField icmsReductionField;

	private DBNumberField icmsAliquotReducedField;

	private DBNumberField icmsAliquotCreditReusableField;

	private DBNumberField icmsSTMarckupField;

	private DBNumberField icmsSTReductionField;

	private DBNumberField icmsSTStaffField;

	private DBSlider cfopGroupField;


	public IcmsRuleController getController(){

		return (IcmsRuleController) super.getController();

	}


	public IcmsRuleView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(750, 650));

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

			basicTab = new DBRowPanel(200);

			basicTab.setName(new BasicTag().toString());

			basicTab.addSeparator(new TributeSituationTag());

			basicTab.add(getCstField());

			basicTab.add(getCsosnField());

			basicTab.addSeparator(new IcmsTag());

			basicTab.add(getIcmsAliquotField());

			basicTab.add(getIcmsReductionField());

			basicTab.add(getIcmsAliquotReducedField());

			basicTab.add(getIcmsAliquotCreditReusableField());

			basicTab.addSeparator(new IcmsSTTag());

			basicTab.add(getIcmsSTMarckupField());

			basicTab.add(getIcmsSTReductionField());

			basicTab.add(getIcmsSTStaffField());

			basicTab.addSeparator(new MacroTag());

			basicTab.add(getCfopGroupField());

		}

		return basicTab;


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public Tag getInternalFrameTitle() {

		return new RulesTag();

	}


	public DBIcmsRulePKField getIdentifierField() {


		if (identifierField==null){

			identifierField = new DBIcmsRulePKField(getController().getModel().getIdentifier());

			getController().addView(identifierField.getIcmsField());

			getController().addView(identifierField.getFromField());

			getController().addView(identifierField.getToField());

			getController().addView(identifierField.getRuleField());

		}

		return identifierField;


	}


	public DBSearchField getCstField() {


		if (cstField==null){

			IcmsCstSearch search = new IcmsCstSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCstProperty((IcmsCst) event.getSelectedRows().get(0));

				}

			});

			cstField = new DBSearchField(new IcmsCstTag(), IcmsRuleController.CST_PROPERTY);

			cstField.setCondition(new EditingOrIncludingListEditorCondition());

			cstField.addCheckListener(this);

			cstField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

			cstField.setSearch(search);

			getController().addView(cstField);

		}

		return cstField;


	}


	public DBSearchField getCsosnField() {


		if (csosnField==null){

			IcmsCsosnSearch search = new IcmsCsosnSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCsosnProperty((IcmsCsosn) event.getSelectedRows().get(0));

				}

			});

			csosnField = new DBSearchField(new IcmsCsosnTag(), IcmsRuleController.CSOSN_PROPERTY);

			csosnField.setCondition(new EditingOrIncludingListEditorCondition());

			csosnField.addCheckListener(this);

			csosnField.setPreferredSize(new Dimension(400, UIConstants.DEFAULT_FIELD_HEIGHT));

			csosnField.setSearch(search);

			getController().addView(csosnField);

		}

		return csosnField;


	}


	public DBNumberField getIcmsAliquotField() {


		if (icmsAliquotField==null){

			icmsAliquotField = new DBNumberField(new AliquotTag(), 

					FormatFactory.createNumberFormat(3, 2),

					IcmsRuleController.ICMS_ALIQUOT_PROPERTY);

			icmsAliquotField.setHorizontalAlignment(SwingConstants.RIGHT);

			icmsAliquotField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			icmsAliquotField.addCheckListener(this);

			icmsAliquotField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsAliquotField);

		}

		return icmsAliquotField;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getCstField())

			try{

				getController().changeCstProperty((IcmsCst) getCstField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getCstField().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		else

			if (event.getCheckable() == getCsosnField())

				try{

					getController().changeCsosnProperty((IcmsCsosn) getCsosnField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getCsosnField().getSearch().openSearchViewWithoutReset(getDesktopPane());

				}

			else

				if (event.getCheckable()==getIcmsAliquotField())

					getController().changeIcmsAliquotProperty(Double.valueOf(getIcmsAliquotField().getValue().toString()));


				else

					if (event.getCheckable()==getIcmsReductionField())

						getController().changeIcmsReductionProperty(Double.valueOf(getIcmsReductionField().getValue().toString()));

					else

						if (event.getCheckable()==getIcmsAliquotReducedField())

							getController().changeIcmsAliquotReducedProperty(Double.valueOf(getIcmsAliquotReducedField().getValue().toString()));

						else

							if (event.getCheckable()==getIcmsAliquotCreditReusableField())

								getController().changeIcmsAliquotCreditReusableProperty(Double.valueOf(getIcmsAliquotCreditReusableField().getValue().toString()));

							else

								if (event.getCheckable()==getIcmsSTMarckupField())

									getController().changeIcmsSTMarckupProperty(Double.valueOf(getIcmsSTMarckupField().getValue().toString()));

								else

									if (event.getCheckable()==getIcmsSTReductionField())

										getController().changeIcmsSTReductionProperty(Double.valueOf(getIcmsSTReductionField().getValue().toString()));

									else

										if (event.getCheckable()==getIcmsSTStaffField())

											getController().changeIcmsSTStaffProperty(Double.valueOf(getIcmsSTStaffField().getValue().toString()));


	}


	public DBNumberField getIcmsReductionField() {


		if (icmsReductionField==null){

			icmsReductionField = new DBNumberField(new ReductionTag(), 

					FormatFactory.createNumberFormat(3, 4),

					IcmsRuleController.ICMS_REDUCTION_PROPERTY);

			icmsReductionField.setHorizontalAlignment(SwingConstants.RIGHT);

			icmsReductionField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			icmsReductionField.addCheckListener(this);

			icmsReductionField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsReductionField);

		}

		return icmsReductionField;


	}


	public DBNumberField getIcmsAliquotReducedField() {


		if (icmsAliquotReducedField==null){

			icmsAliquotReducedField = new DBNumberField(new AliquotReducedTag(), 

					FormatFactory.createNumberFormat(3, 2),

					IcmsRuleController.ICMS_ALIQUOT_REDUCED_PROPERTY);

			icmsAliquotReducedField.setHorizontalAlignment(SwingConstants.RIGHT);

			icmsAliquotReducedField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			icmsAliquotReducedField.addCheckListener(this);

			icmsAliquotReducedField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsAliquotReducedField);

		}

		return icmsAliquotReducedField;


	}


	public DBNumberField getIcmsAliquotCreditReusableField() {


		if (icmsAliquotCreditReusableField==null){

			icmsAliquotCreditReusableField = new DBNumberField(new AliquotCreditReusableTag(), 

					FormatFactory.createNumberFormat(3, 2),

					IcmsRuleController.ICMS_ALIQUOT_CREDIT_REUSABLE_PROPERTY);

			icmsAliquotCreditReusableField.setHorizontalAlignment(SwingConstants.RIGHT);

			icmsAliquotCreditReusableField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			icmsAliquotCreditReusableField.addCheckListener(this);

			icmsAliquotCreditReusableField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsAliquotCreditReusableField);

		}

		return icmsAliquotCreditReusableField;


	}


	public DBNumberField getIcmsSTMarckupField() {


		if (icmsSTMarckupField==null){

			icmsSTMarckupField = new DBNumberField(new MarckupTag(), 

					FormatFactory.createNumberFormat(3, 2),

					IcmsRuleController.ICMS_ST_MARCKUP_PROPERTY);

			icmsSTMarckupField.setHorizontalAlignment(SwingConstants.RIGHT);

			icmsSTMarckupField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			icmsSTMarckupField.addCheckListener(this);

			icmsSTMarckupField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsSTMarckupField);

		}

		return icmsSTMarckupField;


	}


	public DBNumberField getIcmsSTReductionField() {


		if (icmsSTReductionField==null){

			icmsSTReductionField = new DBNumberField(new ReductionTag(), 

					FormatFactory.createNumberFormat(3, 4),

					IcmsRuleController.ICMS_ST_REDUCTION_PROPERTY);

			icmsSTReductionField.setHorizontalAlignment(SwingConstants.RIGHT);

			icmsSTReductionField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			icmsSTReductionField.addCheckListener(this);

			icmsSTReductionField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsSTReductionField);

		}

		return icmsSTReductionField;


	}


	public DBNumberField getIcmsSTStaffField() {


		if (icmsSTStaffField==null){

			icmsSTStaffField = new DBNumberField(new StaffTag(), 

					FormatFactory.createNumberFormat(10, 4),

					IcmsRuleController.ICMS_ST_STAFF_PROPERTY);

			icmsSTStaffField.setHorizontalAlignment(SwingConstants.RIGHT);

			icmsSTStaffField.setPreferredSize(new Dimension(100, UIConstants.DEFAULT_FIELD_HEIGHT));

			icmsSTStaffField.addCheckListener(this);

			icmsSTStaffField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsSTStaffField);

		}

		return icmsSTStaffField;


	}


	public DBSlider getCfopGroupField(){


		if (cfopGroupField==null){

			cfopGroupField = new DBSlider(new CfopTag(), JSlider.HORIZONTAL, 1, 4, 1, IcmsRuleController.CFOP_GROUP_PROPERTY); 

			cfopGroupField.setCondition(new EditingOrIncludingListEditorCondition());

			cfopGroupField.setPreferredSize(new Dimension(100,32));

			Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();

			labelTable.put(new Integer(1), new JLabel("1"));

			labelTable.put(new Integer(2), new JLabel("2"));

			labelTable.put(new Integer(3), new JLabel("3"));

			labelTable.put(new Integer(4), new JLabel("4"));

			cfopGroupField.setLabelTable( labelTable );

			cfopGroupField.setPaintLabels(true);

			cfopGroupField.setPaintTrack(true);

			cfopGroupField.addChangeListener(new ChangeListener() {

				public void stateChanged(ChangeEvent e) {

					JSlider source = (JSlider)e.getSource();

					if (!source.getValueIsAdjusting()) {

						try {

							getController().changeCfopGroupProperty(new Integer(source.getValue()).longValue());

						} catch (Exception e1) {

							e1.printStackTrace();

						}

					}

				}

			});

			getController().addView(cfopGroupField);

		}

		return cfopGroupField;


	}


	public Search getSearch() {


		if (identifierSearch==null){

			identifierSearch = new IcmsRuleSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

//					getController().changeIdentifierProperty(((IcmsRule)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


}