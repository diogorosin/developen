package developen.client.commercial.mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import developen.client.commercial.search.FiscalDocumentTypeSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBSearchField;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.ActiveTag;
import developen.common.commercial.i18n.BasicTag;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.FinanceTag;
import developen.common.commercial.i18n.FiscalDocumentTag;
import developen.common.commercial.i18n.IcmsIcmsSTTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.IntegrationsTag;
import developen.common.commercial.i18n.IpiTag;
import developen.common.commercial.i18n.IssqnTag;
import developen.common.commercial.i18n.MacroTag;
import developen.common.commercial.i18n.ModelTag;
import developen.common.commercial.i18n.PisCofinsTag;
import developen.common.commercial.i18n.StockTag;
import developen.common.commercial.i18n.TributesTag;
import developen.common.commercial.mvc.FiscalDocumentType;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;

public abstract class MacroView extends EntryView {


	private static final long serialVersionUID = -7944822569204090579L;

	public static int WIDTH = 700;

	public static int HEIGHT = 660;

	protected DBRowPanel headerPanel;

	protected Search identifierSearch;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBCheckBox activeField;

	private DBCheckBox icmsField;

	private DBCheckBox ipiField;

	private DBCheckBox pisCofinsField;

	private DBCheckBox issField;

	private DBCheckBox stockField;

	private DBCheckBox financeField;

	private TabbedPane tabbedPane;

	private DBRowPanel basicTab;

	private DBSearchField fiscalDocumentTypeField;


	public MacroView(MacroController controller) {

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

			headerPanel.add(getActiveField());

		}

		return headerPanel;


	}


	public ExtendedPanel getCenterPanel(){


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


		if (basicTab==null){

			basicTab = new DBRowPanel();

			basicTab.addSeparator(new FiscalDocumentTag());

			basicTab.add(getFiscalDocumentTypeField());

			basicTab.addSeparator(new TributesTag());

			basicTab.add(getIcmsField());

			basicTab.add(getIpiField());

			basicTab.add(getPisCofinsField());

			basicTab.add(getIssField());

			basicTab.addSeparator(new IntegrationsTag());

			basicTab.add(getStockField());

			basicTab.add(getFinanceField());

			basicTab.setName(new BasicTag().toString());

		}

		return basicTab;


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

				if (event.getCheckable() == getFiscalDocumentTypeField())

					try{

						getController().changeFiscalDocumentTypeProperty((FiscalDocumentType) getFiscalDocumentTypeField().getSearch().findBy());

					} catch (ManyRecordsFoundException e) {

						getFiscalDocumentTypeField().getSearch().openSearchViewWithoutReset(getDesktopPane());

					}


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), MacroController.IDENTIFIER_PROPERTY);

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setPreferredSize(new Dimension(75,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), MacroController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBCheckBox getActiveField() {


		if (activeField == null){

			activeField = new DBCheckBox(new ActiveTag(), MacroController.ACTIVE_PROPERTY);

			activeField.setSelected(false);

			activeField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeActiveProperty(activeField.isSelected());

				}

			});

			getController().addView(activeField);

		}

		return activeField;


	}


	public DBCheckBox getIcmsField() {


		if (icmsField == null){

			icmsField = new DBCheckBox(new IcmsIcmsSTTag(), MacroController.ICMS_PROPERTY);

			icmsField.setSelected(false);

			icmsField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeIcmsProperty(icmsField.isSelected());

				}

			});

			getController().addView(icmsField);

		}

		return icmsField;


	}


	public DBCheckBox getIpiField() {


		if (ipiField == null){

			ipiField = new DBCheckBox(new IpiTag(), MacroController.IPI_PROPERTY);

			ipiField.setSelected(false);

			ipiField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeIpiProperty(ipiField.isSelected());

				}

			});

			getController().addView(ipiField);

		}

		return ipiField;


	}


	public DBCheckBox getPisCofinsField() {


		if (pisCofinsField == null){

			pisCofinsField = new DBCheckBox(new PisCofinsTag(), MacroController.PIS_COFINS_PROPERTY);

			pisCofinsField.setSelected(false);

			pisCofinsField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changePisCofinsProperty(pisCofinsField.isSelected());

				}

			});

			getController().addView(pisCofinsField);

		}

		return pisCofinsField;


	}


	public DBCheckBox getIssField() {


		if (issField == null){

			issField = new DBCheckBox(new IssqnTag(), MacroController.ISS_PROPERTY);

			issField.setSelected(false);

			issField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeIssProperty(issField.isSelected());

				}

			});

			getController().addView(issField);

		}

		return issField;


	}


	public DBCheckBox getStockField() {


		if (stockField == null){

			stockField = new DBCheckBox(new StockTag(), MacroController.STOCK_PROPERTY);

			stockField.setSelected(false);

			stockField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeStockProperty(stockField.isSelected());

				}

			});

			getController().addView(stockField);

		}

		return stockField;


	}


	public DBCheckBox getFinanceField() {


		if (financeField == null){

			financeField = new DBCheckBox(new FinanceTag(), MacroController.FINANCE_PROPERTY);

			financeField.setSelected(false);

			financeField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeFinanceProperty(financeField.isSelected());

				}

			});

			getController().addView(financeField);

		}

		return financeField;


	}


	public DBSearchField getFiscalDocumentTypeField() {


		if (fiscalDocumentTypeField == null){

			FiscalDocumentTypeSearch fiscalDocumentTypeSearch = new FiscalDocumentTypeSearch();

			fiscalDocumentTypeSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeFiscalDocumentTypeProperty((FiscalDocumentType) event.getSelectedRows().get(0));

				}

			});

			fiscalDocumentTypeField = new DBSearchField(new ModelTag(), MacroController.FISCAL_DOCUMENT_TYPE_PROPERTY);

			fiscalDocumentTypeField.addCheckListener(this);

			fiscalDocumentTypeField.setSearch(fiscalDocumentTypeSearch);

			fiscalDocumentTypeField.setPreferredSize(new Dimension(400,UIConstants.DEFAULT_FIELD_HEIGHT));

			getController().addView(fiscalDocumentTypeField);

		}

		return fiscalDocumentTypeField;


	}


	public Tag getInternalFrameTitle() {

		return new MacroTag();

	}


	public MacroController getController() {

		return (MacroController) super.getController();

	}

}