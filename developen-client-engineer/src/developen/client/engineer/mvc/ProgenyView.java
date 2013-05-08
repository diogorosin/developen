package developen.client.engineer.mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.engineer.search.ProgenySearch;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBComboBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowLayout;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.engineer.i18n.ActiveTag;
import developen.common.engineer.i18n.BasicTag;
import developen.common.engineer.i18n.DenominationTag;
import developen.common.engineer.i18n.IdentifierTag;
import developen.common.engineer.i18n.PriceTag;
import developen.common.engineer.i18n.ProgenyTag;
import developen.common.engineer.i18n.UnitMeasureForUseOrSaleTag;
import developen.common.engineer.mvc.Progeny;
import developen.common.engineer.mvc.UnitMeasure;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedLayout;
import developen.common.framework.widget.TabbedPane;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;

public class ProgenyView extends EntryView {


	private static final long serialVersionUID = 6129699206038480588L;

	public static int WIDTH = 700;

	public static int HEIGHT = 550;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBCheckBox activeField;

	protected Search identifierSearch;

	private TabbedPane tabbedPane;

	private DBRowLayout basicTab;

	private DBNumberField priceField;
	
	private DBComboBox unitMeasureComboBox;
	

	public ProgenyView(ProgenyController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT));

	}


	public ExtendedLayout getNorthLayout(){


		ExtendedLayout l = super.getNorthLayout();

		DBRowLayout northPanel = new DBRowLayout();

		northPanel.add(getIdentifierField());

		northPanel.add(getDenominationField());

		northPanel.add(getActiveField());

		l.add(northPanel);

		return l;


	}


	public ExtendedLayout getCenterLayout(){


		ExtendedLayout l = super.getCenterLayout();

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


	public DBRowLayout getBasicTab(){


		if (basicTab == null){

			basicTab = new DBRowLayout(180);

			basicTab.setName(new BasicTag().toString());
			
			basicTab.add(getPriceField());
			
			basicTab.add(getUnitMeasureComboBox());

		}

		return basicTab;


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

				if (event.getCheckable() == getPriceField())

					getController().changePriceProperty(Double.valueOf(getPriceField().getValue().toString()));


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), ProgenyController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setColumns(6);

			getController().addView(identifierField);

		}

		return identifierField;


	}

	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), ProgenyController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setColumns(30);

			getController().addView(denominationField);

		}

		return denominationField;


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

	
	public DBComboBox getUnitMeasureComboBox() {


		if (unitMeasureComboBox==null){

			try {

				Session session = DPA.getSessionFactory().createSession();

				List<Object> list = session.list(UnitMeasure.class);

				unitMeasureComboBox = new DBComboBox(

						new UnitMeasureForUseOrSaleTag()

						, list.toArray()

						, ProgenyController.UNIT_MEASURE_PROPERTY);

				session.close();

			} catch (Exception e) {

				Messenger.show(e);

			}

			unitMeasureComboBox.setPreferredSize(new Dimension(190,24));

			unitMeasureComboBox.setCondition(new EditingOrIncludingCondition());

			unitMeasureComboBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					getController().changeUnitMeasureProperty((UnitMeasure)((DBComboBox)e.getSource()).getSelectedItem());

				}

			});

			getController().addView(unitMeasureComboBox);

		}

		return unitMeasureComboBox;


	}


	public Search getIdentifierSearch(){


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


	public Tag getInternalFrameTitle() {

		return new ProgenyTag();

	}


	public ProgenyController getController() {

		return (ProgenyController) super.getController();

	}


}