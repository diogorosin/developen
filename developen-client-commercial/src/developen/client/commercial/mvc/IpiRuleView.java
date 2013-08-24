package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.commercial.search.IpiCstSearch;
import developen.client.commercial.widget.DBIpiRulePKField;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.common.commercial.i18n.AliquotTag;
import developen.common.commercial.i18n.BasicTag;
import developen.common.commercial.i18n.IpiCstTag;
import developen.common.commercial.i18n.IpiTag;
import developen.common.commercial.i18n.RulesTag;
import developen.common.commercial.i18n.StaffTag;
import developen.common.commercial.i18n.TributeSituationTag;
import developen.common.commercial.mvc.IpiCst;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;

public class IpiRuleView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = -1296658874235394101L;

	private DBIpiRulePKField identifierField;

	private TabbedPane tabbedPane;

	private DBRowPanel basicTab;

	private DBTextField cstField;

	private DBNumberField ipiAliquotField;

	private DBNumberField ipiStaffField;


	public IpiRuleController getController(){

		return (IpiRuleController) super.getController();

	}


	public IpiRuleView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(650, 400));

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

			basicTab.add(getCstField());

			basicTab.addSeparator(new IpiTag());

			basicTab.add(getIpiAliquotField());

			basicTab.add(getIpiStaffField());

		}

		return basicTab;


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public Tag getInternalFrameTitle() {

		return new RulesTag();

	}


	public DBIpiRulePKField getIdentifierField() {


		if (identifierField==null){

			identifierField = new DBIpiRulePKField(getController().getModel().getIdentifier());

			getController().addView(identifierField.getIpiField());

			getController().addView(identifierField.getCfopField());

			getController().addView(identifierField.getRuleField());

		}

		return identifierField;


	}


	public DBTextField getCstField() {


		if (cstField==null){

			IpiCstSearch search = new IpiCstSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCstProperty((IpiCst) event.getSelectedRows().get(0));

				}

			});

			cstField = new DBTextField(new IpiCstTag(), IpiRuleController.CST_PROPERTY);

			cstField.setCondition(new EditingOrIncludingListEditorCondition());

			cstField.addCheckListener(this);

			cstField.setPreferredSize(new Dimension(400, 24));

			cstField.setSearch(search);

			getController().addView(cstField);

		}

		return cstField;


	}


	public DBNumberField getIpiAliquotField() {


		if (ipiAliquotField==null){

			ipiAliquotField = new DBNumberField(new AliquotTag(), 

					FormatFactory.createNumberFormat(3, 2),

					IpiRuleController.IPI_ALIQUOT_PROPERTY);

			ipiAliquotField.setHorizontalAlignment(SwingConstants.RIGHT);

			ipiAliquotField.setPreferredSize(new Dimension(100, 24));

			ipiAliquotField.addCheckListener(this);

			ipiAliquotField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(ipiAliquotField);

		}

		return ipiAliquotField;


	}


	public DBNumberField getIpiStaffField() {


		if (ipiStaffField==null){

			ipiStaffField = new DBNumberField(new StaffTag(), 

					FormatFactory.createNumberFormat(10, 4),

					IpiRuleController.IPI_STAFF_PROPERTY);

			ipiStaffField.setHorizontalAlignment(SwingConstants.RIGHT);

			ipiStaffField.setPreferredSize(new Dimension(100, 24));

			ipiStaffField.addCheckListener(this);

			ipiStaffField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(ipiStaffField);

		}

		return ipiStaffField;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getCstField())

			try{

				getController().changeCstProperty((IpiCst) getCstField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getCstField().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		else

			if (event.getCheckable()==getIpiAliquotField())

				getController().changeIpiAliquotProperty(Double.valueOf(getIpiAliquotField().getValue().toString()));


			else

				if (event.getCheckable()==getIpiStaffField())

					getController().changeIpiStaffProperty(Double.valueOf(getIpiStaffField().getValue().toString()));


	}


}