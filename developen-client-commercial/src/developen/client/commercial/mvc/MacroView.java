package developen.client.commercial.mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.MacroTag;
import developen.common.engineer.i18n.FinanceTag;
import developen.common.engineer.i18n.IcmsTag;
import developen.common.engineer.i18n.IntegrationsTag;
import developen.common.engineer.i18n.IpiTag;
import developen.common.engineer.i18n.IssTag;
import developen.common.engineer.i18n.PisCofinsTag;
import developen.common.engineer.i18n.StockTag;
import developen.common.engineer.i18n.TributesIntegrationsTag;
import developen.common.engineer.i18n.TributesTag;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.subject.i18n.ActiveTag;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;

public abstract class MacroView extends EntryView {


	private static final long serialVersionUID = -7944822569204090579L;

	public static int WIDTH = 700;

	public static int HEIGHT = 650;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBCheckBox activeField;
	
	private DBCheckBox icmsField;
	
	private DBCheckBox ipiField;
	
	private DBCheckBox pisCofinsField;
	
	private DBCheckBox issField;
	
	private DBCheckBox stockField;
	
	private DBCheckBox financeField;
	
	protected DBRowPanel headerPanel;

	protected Search identifierSearch;
	
	private TabbedPane tabbedPane;

	private DBRowPanel tributesIntegrationsTab;
	

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
			
			tabbedPane.add(getTributesIntegrationsTab());
			
			tabbedPane.setFocusable(false);

		}
		
		return tabbedPane;
		

	}
	
	
	public DBRowPanel getTributesIntegrationsTab(){


		if (tributesIntegrationsTab==null){

			tributesIntegrationsTab = new DBRowPanel(0);

			tributesIntegrationsTab.addSeparator(new TributesTag());
			
			tributesIntegrationsTab.add(getIcmsField());
			
			tributesIntegrationsTab.add(getIpiField());
			
			tributesIntegrationsTab.add(getPisCofinsField());
			
			tributesIntegrationsTab.add(getIssField());

			tributesIntegrationsTab.addSeparator(new IntegrationsTag());
			
			tributesIntegrationsTab.add(getStockField());
			
			tributesIntegrationsTab.add(getFinanceField());
			
			tributesIntegrationsTab.setName(new TributesIntegrationsTag().toString());
			
		}

		return tributesIntegrationsTab;


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


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), MacroController.IDENTIFIER_PROPERTY);

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.setPreferredSize(new Dimension(75,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), MacroController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400,24));

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

			icmsField = new DBCheckBox(new IcmsTag(), MacroController.ICMS_PROPERTY);

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

			issField = new DBCheckBox(new IssTag(), MacroController.ISS_PROPERTY);

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
	

	public Tag getInternalFrameTitle() {

		return new MacroTag();

	}

	
	public MacroController getController() {

		return (MacroController) super.getController();

	}

	
	public abstract Search getIdentifierSearch();


}