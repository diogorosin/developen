package developen.client.commercial.mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.commercial.search.IcmsSearch;
import developen.client.commercial.search.PisCofinsSearch;
import developen.client.commercial.search.ProgenySearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBComboBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.commercial.i18n.ActiveTag;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IcmsIcmsSTTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.PisCofinsTag;
import developen.common.commercial.i18n.PriceTag;
import developen.common.commercial.i18n.ProgenyTag;
import developen.common.commercial.i18n.ShortDenominationTag;
import developen.common.commercial.i18n.TypeTag;
import developen.common.commercial.mvc.Icms;
import developen.common.commercial.mvc.PisCofins;
import developen.common.commercial.mvc.Progeny;
import developen.common.commercial.mvc.ProgenyType;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;

public class ProgenyView extends EntryView {


	private static final long serialVersionUID = 6129699206038480588L;

	public static int WIDTH = 800;

	public static int HEIGHT = 600;

	protected Search identifierSearch;	

	
	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBTextField shortDenominationField;

	private DBCheckBox activeField;
	
	private DBComboBox progenyTypeComboBox;

	private DBNumberField priceField;
	
	private DBTextField icmsField;
	
	private DBTextField pisCofinsField;
	
	
	private TabbedPane mainTabbedPane;


	public ProgenyView(ProgenyController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT));

	}


	public ProgenyController getController() {

		return (ProgenyController) super.getController();

	}


	public ExtendedPanel getNorthPanel(){


		ExtendedPanel l = super.getNorthPanel();

		DBRowPanel northPanel = new DBRowPanel(150);

		northPanel.add(getIdentifierField());

		northPanel.add(getDenominationField());

		northPanel.add(getShortDenominationField());

		northPanel.add(getActiveField());

		l.add(northPanel);

		return l;


	}


	public ExtendedPanel getCenterPanel(){


		ExtendedPanel l = super.getCenterPanel();

		l.add(getMainTabbedPane());

		return l;


	}

	
	public TabbedPane getMainTabbedPane(){


		if (mainTabbedPane == null){

			mainTabbedPane = new TabbedPane();

			mainTabbedPane.setFocusable(false);

		}

		return mainTabbedPane;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getIdentifierField())

			getController().changeIdentifierProperty(getIdentifierField().getText().isEmpty() 

					? new Long(0) 

			: Long.valueOf(getIdentifierField().getText()));

		else

			if (event.getCheckable() == getDenominationField())

				getController().changeDenominationProperty(getDenominationField().getText());

			else

				if (event.getCheckable() == getShortDenominationField())

					getController().changeShortDenominationProperty(getShortDenominationField().getText());

				else

					if (event.getCheckable() == getPriceField())

						getController().changePriceProperty(Double.valueOf(getPriceField().getValue().toString()));

					else

						if (event.getCheckable() == getIcmsField())

							try{

								getController().changeIcmsProperty((Icms) getIcmsField().getSearch().findBy());

							} catch (ManyRecordsFoundException e) {

								getIcmsField().getSearch().openSearchViewWithoutReset(getDesktopPane());

							}


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), ProgenyController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setPreferredSize(new Dimension(100,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public Search getSearch(){


		if (identifierSearch==null){

			identifierSearch = new ProgenySearch(true);

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Progeny)event.getSelectedRows().get(0)).getIdentifier());   

				}

			});

		}

		return identifierSearch;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), ProgenyController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400,24));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBTextField getShortDenominationField() {


		if (shortDenominationField == null){

			shortDenominationField = new DBTextField(new ShortDenominationTag(), ProgenyController.SHORT_DENOMINATION_PROPERTY);

			shortDenominationField.addCheckListener(this);

			shortDenominationField.setPreferredSize(new Dimension(300,24));

			getController().addView(shortDenominationField);

		}

		return shortDenominationField;


	}


	public DBCheckBox getActiveField() {


		if (activeField == null){

			activeField = new DBCheckBox(new ActiveTag(), ProgenyController.ACTIVE_PROPERTY);

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


	public DBNumberField getPriceField() {


		if (priceField==null){

			priceField = new DBNumberField(new PriceTag(), 

					FormatFactory.createNumberFormat(10, 2),

					ProgenyController.PRICE_PROPERTY);

			priceField.setHorizontalAlignment(SwingConstants.RIGHT);

			priceField.setColumns(10);

			priceField.addCheckListener(this);

			priceField.setCondition(new EditingOrIncludingCondition());

			getController().addView(priceField);

		}

		return priceField;


	}


	public DBComboBox getProgenyTypeComboBox() {


		if (progenyTypeComboBox==null){

			try {

				Session session = DPA.getSessionFactory().createSession();

				List<Object> list = session.list(ProgenyType.class);

				progenyTypeComboBox = new DBComboBox(

						new TypeTag()

						, list.toArray()

						, ProgenyController.PROGENY_TYPE_PROPERTY);

				session.close();

			} catch (Exception e) {

				Messenger.show(e);

			}

			progenyTypeComboBox.setPreferredSize(new Dimension(220,24));

			progenyTypeComboBox.setCondition(new EditingOrIncludingCondition());

			progenyTypeComboBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					getController().changeProgenyTypeProperty((ProgenyType)((DBComboBox)e.getSource()).getSelectedItem());

				}

			});

			getController().addView(progenyTypeComboBox);

		}

		return progenyTypeComboBox;


	}


	public DBTextField getIcmsField() {


		if (icmsField == null){

			IcmsSearch icmsSearch = new IcmsSearch();

			icmsSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIcmsProperty((Icms) event.getSelectedRows().get(0));

				}

			});


			icmsField = new DBTextField(new IcmsIcmsSTTag(), ProgenyController.ICMS_PROPERTY);

			icmsField.addCheckListener(this);

			icmsField.setSearch(icmsSearch);

			icmsField.setPreferredSize(new Dimension(400,24));

			getController().addView(icmsField);

		}

		return icmsField;


	}


	public DBTextField getPisCofinsField() {


		if (pisCofinsField == null){

			PisCofinsSearch pisCofinsSearch = new PisCofinsSearch();

			pisCofinsSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changePisCofinsProperty((PisCofins) event.getSelectedRows().get(0));

				}

			});


			pisCofinsField = new DBTextField(new PisCofinsTag(), ProgenyController.PIS_COFINS_PROPERTY);

			pisCofinsField.addCheckListener(this);

			pisCofinsField.setSearch(pisCofinsSearch);

			pisCofinsField.setPreferredSize(new Dimension(400,24));

			getController().addView(pisCofinsField);

		}

		return pisCofinsField;


	}

	
	public Tag getInternalFrameTitle() {

		return new ProgenyTag();

	}


}