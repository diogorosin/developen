package developen.client.subject.mvc;

import java.awt.Dimension;

import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.subject.search.CnaeSearch;
import developen.client.subject.search.CompanySearch;
import developen.client.subject.widget.DBCnpjField;
import developen.client.subject.widget.DBIeField;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.TabbedPane;
import developen.common.subject.i18n.CnaeTag;
import developen.common.subject.i18n.CompanyNameTag;
import developen.common.subject.i18n.CompanyTag;
import developen.common.subject.i18n.FancyNameTag;
import developen.common.subject.i18n.FiscalTag;
import developen.common.subject.mvc.Cnae;
import developen.common.subject.mvc.Company;

public class CompanyView extends SubjectView {


	private static final long serialVersionUID = -6308377525148332267L;

	private DBCnpjField cnpjField;

	private DBIeField ieField;

	private DBTextField fancyNameField;

	private DBTextField cnaeField;

	private DBRowPanel fiscalTab;


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


	public TabbedPane getTabbedPane(){


		TabbedPane t = super.getTabbedPane();

		t.add(getFiscalTab());

		return t;


	}


	public DBRowPanel getFiscalTab(){


		if (fiscalTab==null){

			fiscalTab = new DBRowPanel(100);

			fiscalTab.add(getCnaeField());

			fiscalTab.setName(new FiscalTag().toString());

		}

		return fiscalTab;


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


	public Search getIdentifierSearch(){


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