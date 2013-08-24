package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.JComponent;

import developen.client.commercial.search.ProductLineSearch;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.LineTag;
import developen.common.commercial.i18n.ShortDenominationTag;
import developen.common.commercial.mvc.ProductLine;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;

public class ProductLineView extends EntryView {


	private static final long serialVersionUID = -5394176398244939089L;

	public static int WIDTH = 700;

	public static int HEIGHT = 300;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;
	
	private DBTextField shortDenominationField;

	protected Search identifierSearch;


	public ProductLineController getController() {

		return (ProductLineController) super.getController();

	}


	public ProductLineView(ProductLineController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH, HEIGHT));

	}


	public ExtendedPanel getCenterPanel(){


		ExtendedPanel l = super.getCenterPanel();

		DBRowPanel northPanel = new DBRowPanel(150);

		northPanel.add(getIdentifierField());

		northPanel.add(getDenominationField());
		
		northPanel.add(getShortDenominationField());

		l.add(northPanel);

		return l;


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

		
	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), ProductLineController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setPreferredSize(new Dimension(75,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), ProductLineController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400, 24));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBTextField getShortDenominationField() {


		if (shortDenominationField == null){

			shortDenominationField = new DBTextField(new ShortDenominationTag(), ProductLineController.SHORT_DENOMINATION_PROPERTY);

			shortDenominationField.addCheckListener(this);

			shortDenominationField.setPreferredSize(new Dimension(200, 24));

			getController().addView(shortDenominationField);

		}

		return shortDenominationField;


	}


	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new ProductLineSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((ProductLine)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


	public Tag getInternalFrameTitle() {

		return new LineTag();

	}


}