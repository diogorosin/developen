package developen.client.commercial.mvc;

import java.awt.Dimension;

import developen.client.commercial.search.CnaeSearch;
import developen.client.commercial.search.CompanySearch;
import developen.client.commercial.widget.DBCnpjField;
import developen.client.commercial.widget.DBIeField;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.CnaeTag;
import developen.common.commercial.i18n.CompanyNameTag;
import developen.common.commercial.i18n.CompanyTag;
import developen.common.commercial.i18n.FancyNameTag;
import developen.common.commercial.i18n.FiscalTag;
import developen.common.commercial.mvc.Cnae;
import developen.common.commercial.mvc.Company;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;

public class CompanyView extends SubjectView {


	private static final long serialVersionUID = -6308377525148332267L;

	private DBCnpjField cnpjField;

	private DBIeField ieField;

	private DBTextField fancyNameField;

	private DBTextField cnaeField;


	public CompanyView(CompanyController controller){

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT+50));

	}


	public Tag getInternalFrameTitle() {

		return new CompanyTag();

	}


	public void setController(CompanyController controller) {

		super.setController(controller);

	}


	public CompanyController getController() {

		return (CompanyController) super.getController();

	}


	public DBRowPanel getHeaderPanel(){


		if (headerPanel == null){

			headerPanel = new DBRowPanel(100);

			headerPanel.add(getIdentifierField());

			headerPanel.add(getCnpjField());

			headerPanel.add(getIeField());

			headerPanel.add(getDenominationField());

			headerPanel.add(getFancyNameField());

			headerPanel.add(getFieldActive());

		}

		return headerPanel;

	}


	public DBRowPanel getPreferencesTab(){

		
		DBRowPanel r = super.getPreferencesTab();
		
		r.addSeparator(new FiscalTag());
		
		r.add(getCnaeField());

		return r;
		

	}


	public DBIeField getIeField(){


		if (ieField == null) {

			ieField = new DBIeField(getController().getModel().getIe());

			getController().addView(ieField.getNumberField());

			getController().addView(ieField);

		}

		return ieField;


	}


	public DBCnpjField getCnpjField() {


		if (cnpjField==null){

			cnpjField = new DBCnpjField(getController().getModel().getCnpj());

			getController().addView(cnpjField.getNumberField());

			getController().addView(cnpjField);

		}

		return cnpjField;


	}


	public DBTextField getDenominationField() {


		DBTextField f = super.getDenominationField();

		f.setCaption(new CompanyNameTag());

		return f;


	}


	public DBTextField getFancyNameField() {


		if (fancyNameField == null){

			fancyNameField = new DBTextField(new FancyNameTag(), CompanyController.FANCY_NAME_PROPERTY);

			fancyNameField.addCheckListener(this);

			fancyNameField.setPreferredSize(new Dimension(400,24));

			getController().addView(fancyNameField);

		}

		return fancyNameField;


	}


	public DBTextField getCnaeField() {


		if (cnaeField == null){

			CnaeSearch cnaeSearch = new CnaeSearch();

			cnaeSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeCnaeProperty((Cnae) event.getSelectedRows().get(0));

				}

			});


			cnaeField = new DBTextField(new CnaeTag(), CompanyController.CNAE_PROPERTY);

			cnaeField.addCheckListener(this);

			cnaeField.setSearch(cnaeSearch);

			cnaeField.setPreferredSize(new Dimension(400,24));

			getController().addView(cnaeField);

		}

		return cnaeField;


	}


	public void onCheck(CheckEvent event) throws Exception {


		super.onCheck(event);

		if (event.getCheckable() == getFancyNameField())

			getController().changeFancyNameProperty(getFancyNameField().getText());

		else 

			if (event.getCheckable() == getCnaeField())

				try{

					getController().changeCnaeProperty((Cnae) getCnaeField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getCnaeField().getSearch().openSearchViewWithoutReset(getDesktopPane());

				}


	}


	public Search getSearch(){


		if (identifierSearch == null){

			identifierSearch = new CompanySearch(true);

			identifierSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Company)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


}