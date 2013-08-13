package developen.client.engineer.mvc;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.engineer.search.CsosnSearch;
import developen.client.engineer.search.CstSearch;
import developen.client.engineer.widget.DBIcmsRulePKField;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.common.engineer.i18n.AliquotCreditReusableTag;
import developen.common.engineer.i18n.AliquotReducedTag;
import developen.common.engineer.i18n.AliquotTag;
import developen.common.engineer.i18n.BasicTag;
import developen.common.engineer.i18n.CfopTag;
import developen.common.engineer.i18n.CsosnTag;
import developen.common.engineer.i18n.CstTag;
import developen.common.engineer.i18n.GroupTag;
import developen.common.engineer.i18n.IcmsSTTag;
import developen.common.engineer.i18n.IcmsTag;
import developen.common.engineer.i18n.MarckupTag;
import developen.common.engineer.i18n.ReductionTag;
import developen.common.engineer.i18n.RulesTag;
import developen.common.engineer.i18n.StaffTag;
import developen.common.engineer.i18n.TributeSituationTag;
import developen.common.engineer.mvc.Csosn;
import developen.common.engineer.mvc.Cst;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;

public class IcmsRuleView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = -4306601465035148551L;

	private DBIcmsRulePKField identifierField;

	private TabbedPane tabbedPane;

	private DBRowPanel basicTab;

	private DBTextField cstField;

	private DBTextField csosnField;

	private DBNumberField icmsAliquotField;

	private DBNumberField icmsReductionField;

	private DBNumberField icmsAliquotReducedField;

	private DBNumberField icmsAliquotCreditReusableField;

	private DBNumberField icmsSTMarckupField;

	private DBNumberField icmsSTReductionField;

	private DBNumberField icmsSTStaffField;

	private DBIdentifierField cfopGroupField;


	public IcmsRuleController getController(){

		return (IcmsRuleController) super.getController();

	}


	public IcmsRuleView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(700, 620));

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

			basicTab.addSeparator(new CfopTag());

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

			getController().addView(identifierField.getView().getIcmsField());

			getController().addView(identifierField.getView().getFromField());

			getController().addView(identifierField.getView().getToField());

		}

		return identifierField;


	}


	public DBTextField getCstField() {


		if (cstField==null){

			CstSearch search = new CstSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCstProperty((Cst) event.getSelectedRows().get(0));

				}

			});

			cstField = new DBTextField(new CstTag(), IcmsRuleController.CST_PROPERTY);

			cstField.setCondition(new EditingOrIncludingListEditorCondition());

			cstField.addCheckListener(this);

			cstField.setPreferredSize(new Dimension(400, 24));

			cstField.setSearch(search);

			getController().addView(cstField);

		}

		return cstField;


	}


	public DBTextField getCsosnField() {


		if (csosnField==null){

			CsosnSearch search = new CsosnSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCsosnProperty((Csosn) event.getSelectedRows().get(0));

				}

			});

			csosnField = new DBTextField(new CsosnTag(), IcmsRuleController.CSOSN_PROPERTY);

			csosnField.setCondition(new EditingOrIncludingListEditorCondition());

			csosnField.addCheckListener(this);

			csosnField.setPreferredSize(new Dimension(400, 24));

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

			icmsAliquotField.setPreferredSize(new Dimension(100, 24));

			icmsAliquotField.addCheckListener(this);

			icmsAliquotField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsAliquotField);

		}

		return icmsAliquotField;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getCstField())

			try{

				getController().changeCstProperty((Cst) getCstField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getCstField().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		else

			if (event.getCheckable() == getCsosnField())

				try{

					getController().changeCsosnProperty((Csosn) getCsosnField().getSearch().findBy());

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

										else

											if (event.getCheckable()==getCfopGroupField())

												getController().changeCfopGroupProperty(getCfopGroupField().getText().isEmpty() 

														? new Long(0) 

												: Long.valueOf(getCfopGroupField().getText()));

	}


	public DBNumberField getIcmsReductionField() {


		if (icmsReductionField==null){

			icmsReductionField = new DBNumberField(new ReductionTag(), 

					FormatFactory.createNumberFormat(3, 4),

					IcmsRuleController.ICMS_REDUCTION_PROPERTY);

			icmsReductionField.setHorizontalAlignment(SwingConstants.RIGHT);

			icmsReductionField.setPreferredSize(new Dimension(100, 24));

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

			icmsAliquotReducedField.setPreferredSize(new Dimension(100, 24));

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

			icmsAliquotCreditReusableField.setPreferredSize(new Dimension(100, 24));

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

			icmsSTMarckupField.setPreferredSize(new Dimension(100, 24));

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

			icmsSTReductionField.setPreferredSize(new Dimension(100, 24));

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

			icmsSTStaffField.setPreferredSize(new Dimension(100, 24));

			icmsSTStaffField.addCheckListener(this);

			icmsSTStaffField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(icmsSTStaffField);

		}

		return icmsSTStaffField;


	}


	public DBIdentifierField getCfopGroupField(){


		if (cfopGroupField==null){

			cfopGroupField = new DBIdentifierField(new GroupTag(), IcmsRuleController.CFOP_GROUP_PROPERTY); 

			cfopGroupField.setCondition(new EditingOrIncludingListEditorCondition());

			cfopGroupField.addCheckListener(this);

			cfopGroupField.setPreferredSize(new Dimension(100,24));

			getController().addView(cfopGroupField);

		}

		return cfopGroupField;


	}


}